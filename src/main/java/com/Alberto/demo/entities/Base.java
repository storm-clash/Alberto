package com.Alberto.demo.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@MappedSuperclass// se utiliza para poner las propiedades que sean comunes a todas las clases
//Las clases extenderian de esta Base y borro esos datos en dichas clases
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Base  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
