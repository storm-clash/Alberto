package com.Alberto.demo.entities;


import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "truck")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Truck extends Base{


    @Column(nullable = false,unique = true,length = 15)
    private String matricula;
    @Column(nullable = false)
    private double capacidad;
    private int ruedas;
    @Nonnull
    private double kilometraje;
    @Enumerated(EnumType.STRING)
    @Column(name="fuel")
    private Fuel type;
    private boolean utilizado;



    @OneToMany(mappedBy = "truck",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Collection<Trucks_Driver> trucksDrivers = new ArrayList<>();
}
