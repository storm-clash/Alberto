package com.Alberto.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trucks_driver")
public class Trucks_Driver {

    @EmbeddedId
    Truck_Driver_Key id = new Truck_Driver_Key();

    @ManyToOne
    @MapsId("driverId")
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne
    @MapsId("truckId")
    @JoinColumn(name = "truck_id")
    private Truck truck;

    private LocalDate fecha_inicio;
    private LocalDate fecha_termino;
}
