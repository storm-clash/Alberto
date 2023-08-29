package com.Alberto.demo.user;

import com.fasterxml.jackson.databind.KeyDeserializer;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,length = 20)
    private String pass;
    @Column(nullable = false,unique = true,length = 20, name = "user")
    private String username;
    @Column(nullable = false,length = 45)
    private String email;
    private boolean activado;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivado() {
        return activado;
    }

    public void setActivado(boolean activado) {
        this.activado = activado;
    }

    public User(Integer id, String pass, String username, String email, boolean activado) {
        this.id = id;
        this.pass = pass;
        this.username = username;
        this.email = email;
        this.activado = activado;
    }

    public User(){}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", pass='" + pass + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", activado=" + activado +
                '}';
    }
}
