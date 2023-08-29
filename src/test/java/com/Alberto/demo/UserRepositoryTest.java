package com.Alberto.demo;

import com.Alberto.demo.user.User;
import com.Alberto.demo.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Iterator;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
public class UserRepositoryTest {

    @Autowired private UserRepository userRepository;


    @Test
    public void testAddNew(){
        User usuario = new User();
        usuario.setPass("sera");
        usuario.setUsername("storm");
        usuario.setEmail("storm@gmail.com");
        usuario.setActivado(false);

        User savedUser= userRepository.save(usuario);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);

    }
    @Test
    public void testListAll(){
        Iterable<User> users = userRepository.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for(User user: users){
            System.out.println(user);
        }

    }

    @Test
    public void testUpdate(){
        Integer userId = 2;
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        user.setPass("12345");
        userRepository.save(user);

        User updatedUser = userRepository.findById(userId).get();
        Assertions.assertThat(updatedUser.getPass()).isEqualTo("12345");
    }

    @Test
    public void testGet(){
     Integer userId=2;
     Optional<User> optionalUser = userRepository.findById(userId);


     Assertions.assertThat(optionalUser).isPresent();
     System.out.println(optionalUser.get());

    }
    @Test
    public void testDelete(){
        Integer userId=2;
        userRepository.deleteById(userId);

        Optional<User> optionalUser = userRepository.findById(userId);


        Assertions.assertThat(optionalUser).isNotPresent();

    }

}
