package com.Alberto.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Truck_DriverDTO {


    private String name;
    private String matricula;
    private LocalDate fecha_inicio;
    private LocalDate fecha_termino;

    public Truck_DriverDTO(String name, String matricula, LocalDate fecha_inicio) {
        this.name = name;
        this.matricula = matricula;
        this.fecha_inicio = fecha_inicio;
    }




}
