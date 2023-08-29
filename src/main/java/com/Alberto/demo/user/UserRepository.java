package com.Alberto.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    public Long countById(Integer id);

    @Query("SELECT s FROM User s WHERE s.email =?1")
    Optional<User> finUserByEmail(String email);

}
