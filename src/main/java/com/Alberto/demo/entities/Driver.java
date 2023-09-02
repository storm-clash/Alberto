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
@Table(name = "driver")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Driver extends Base {


    @Column(nullable = false,length = 45)
    private String name;
    @Column(nullable = false,length = 45)
    private String lastname;
    @Nonnull
    private int age;
    @Nonnull
    private double wage;
    @Nonnull
    @Enumerated(EnumType.STRING)
    @Column(name="sex")
    private Sex type;



    @OneToMany(mappedBy = "driver",cascade = {CascadeType.ALL,CascadeType.PERSIST})
    private Collection<Trucks_Driver> trucksDrivers = new ArrayList<>();


    public void addTrucksDriver(Trucks_Driver trucks_driver){
        this.trucksDrivers.add(trucks_driver);
    }
}
