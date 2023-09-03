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
    private String plate;
    private LocalDate begin;
    private LocalDate end;

    public Truck_DriverDTO(String name, String plate, LocalDate begin) {
        this.name = name;
        this.plate = plate;
        this.begin = begin;
    }
}
