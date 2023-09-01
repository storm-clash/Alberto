package com.Alberto.demo.DTOs;


import com.Alberto.demo.entities.Fuel;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TruckDTO {
    private Long id;
    private String matricula;
    private double capacidad;
    private int ruedas;
    private double kilometraje;
    private Fuel type;
    private boolean utilizado;

}
