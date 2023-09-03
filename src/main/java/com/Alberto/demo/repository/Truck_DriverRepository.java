package com.Alberto.demo.repository;

import com.Alberto.demo.entities.Truck_Driver_Key;
import com.Alberto.demo.entities.Trucks_Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface Truck_DriverRepository extends JpaRepository<Trucks_Driver, Truck_Driver_Key> {

     List<Trucks_Driver> findByDriverId(long id);
     @Query(value = "SELECT * FROM Trucks_driver WHERE trucks_driver.driver_id=?1 AND trucks_driver.fecha_inicio=?2",nativeQuery = true)
     List<Trucks_Driver> findDriver(Long id, Date filtro);


}
