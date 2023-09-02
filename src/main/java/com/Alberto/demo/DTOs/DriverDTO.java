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
public class DriverDTO{
    private Long id;
    private String name;
    private String lastname;
    private int age;
    private double wage;
    private Sex type;
}
