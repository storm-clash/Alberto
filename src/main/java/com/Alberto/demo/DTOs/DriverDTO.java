package com.Alberto.demo.DTOs;

import com.Alberto.demo.entities.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DriverDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private int edad;
    private double salario;
    private Sex type;
}
