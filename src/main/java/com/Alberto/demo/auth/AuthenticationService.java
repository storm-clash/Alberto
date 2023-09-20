package com.Alberto.demo.auth;

import com.Alberto.demo.config.JwtService;
import com.Alberto.demo.tfa.TwoFactorAuthenticationService;
import com.Alberto.demo.user.Role;
import com.Alberto.demo.user.User;
import com.Alberto.demo.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final TwoFactorAuthenticationService tfaService;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()

                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .mfaEnabled(request.isMfaEnabled())
                .build();
        //if mfa enabled --> Generate secret

        if(request.isMfaEnabled()){
            user.setSecret(tfaService.generateNewSecret());
        }
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .secretImageUrl(tfaService.generateQrCodeImageUrl(user.getSecret()))
                .token(jwtToken)
                .mfaEnabled(user.isMfaEnabled())
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        if(user.isMfaEnabled()){
            return AuthenticationResponse.builder()
                    .token("")
                    .mfaEnabled(true)
                    .build();
        }
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .mfaEnabled(false)
                .build();
    }

    public AuthenticationResponse verifyCode(VerificationRequest verificationRequest) {
        User user = repository.findByEmail(verificationRequest.getEmail())
                .orElseThrow(()->new EntityNotFoundException(
                        String.format("No user found with %S", verificationRequest.getEmail())
                ));
        if(tfaService.isOtpNotValid(user.getSecret(), verificationRequest.getCode())){
            throw new BadCredentialsException("Code is not correct");
        }
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .mfaEnabled(user.isMfaEnabled())
                .build();
    }
}
