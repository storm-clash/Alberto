package com.Alberto.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    @ManyToOne(cascade ={CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.DETACH})
    @MapsId("driverId")
    @JoinColumn(name = "driver_id")
    @JsonBackReference
    private Driver driver;

    @ManyToOne(cascade ={CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.DETACH})
    @MapsId("truckId")
    @JoinColumn(name = "truck_id")
    @JsonBackReference
    private Truck truck;

    private LocalDate fecha_inicio;
    private LocalDate fecha_termino;


    public Trucks_Driver(Driver driver, Truck truck, LocalDate fecha_inicio, LocalDate fecha_termino) {
        this.driver = driver;
        this.truck = truck;
        this.fecha_inicio = fecha_inicio;
        this.fecha_termino = fecha_termino;
    }

    public Trucks_Driver(Driver driver, Truck truck, LocalDate fecha_inicio) {
        this.driver = driver;
        this.truck = truck;
        this.fecha_inicio = fecha_inicio;
    }

    public Truck_Driver_Key getId() {
        return id;
    }

    public void setId(Truck_Driver_Key id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_termino() {
        return fecha_termino;
    }

    public void setFecha_termino(LocalDate fecha_termino) {
        this.fecha_termino = fecha_termino;
    }
}
