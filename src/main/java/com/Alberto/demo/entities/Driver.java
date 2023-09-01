package com.Alberto.demo.entities;


import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToMany
    @JoinTable(
            name = "truck_driver",
            joinColumns = @JoinColumn(name = "driver_id"),
            inverseJoinColumns = @JoinColumn(name = "truck_id"))
    Set<Truck> trucks;

    @OneToMany(mappedBy = "driver")
    Set<Trucks_Driver> trucksDrivers;
}
