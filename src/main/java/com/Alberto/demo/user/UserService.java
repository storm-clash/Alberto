package com.Alberto.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> listAll(){
        return (List<User>)userRepository.findAll();
    }

    public void salvar( User user) throws EmailNotFoundException{

        //userRepository.save(user);
       Optional<User>userEmail = userRepository.finUserByEmail(user.getEmail());
       if(userEmail.isPresent()){
           throw new EmailNotFoundException("Este correo se encuentra siendo usado por otro usuario");
       }
       userRepository.save(user);
    }

    public User get(Integer id) throws UserNotFoundException{

        Optional<User> result = userRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("No se Encontro al usuario especificado"+id);

    }
    public void delete(Integer id) throws UserNotFoundException{
        Long count= userRepository.countById(id);
        if(count==null || count==0){
            throw new UserNotFoundException("No se Encontro al usuario especificado"+id);
        }
        userRepository.deleteById(id);
    }


   /* @Bean
    public void AddNewUser(){
        User usuario = new User();
        //usuario.setId();
        usuario.setPass("diana*");
        usuario.setUser("diana");
        usuario.setEmail("diana@gmail.com");

        User savedUser= userRepository.save(usuario);
    }*/
}
