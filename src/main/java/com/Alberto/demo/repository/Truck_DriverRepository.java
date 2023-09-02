package com.Alberto.demo.repository;

import com.Alberto.demo.entities.Truck_Driver_Key;
import com.Alberto.demo.entities.Trucks_Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Truck_DriverRepository extends JpaRepository<Trucks_Driver, Truck_Driver_Key> {
}
