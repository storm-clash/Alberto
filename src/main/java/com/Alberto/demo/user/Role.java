package com.Alberto.demo.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.Alberto.demo.user.Permission.*;

@RequiredArgsConstructor
public enum Role {
    USER(Collections.EMPTY_SET),

    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_CREATE,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    MANAGER_READ,
                    MANAGER_CREATE,
                    MANAGER_UPDATE,
                    MANAGER_DELETE
            )
    ),

    MANAGER(
            Set.of(
                    MANAGER_READ,
                    MANAGER_CREATE,
                    MANAGER_UPDATE,
                    MANAGER_DELETE
            )
    )
    ;

    @Getter
    private final Set<Permission> permission;

    public List<SimpleGrantedAuthority> getAuthorities() {
      var authorities = getPermission()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());

      authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
      return authorities;
    }
}
