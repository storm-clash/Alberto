package com.Alberto.demo.repository;

import com.Alberto.demo.entities.Truck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TruckRepository extends BaseRepository<Truck,Long> {

    List<Truck> findByMatriculaContainingOrFuelContaining(String matricula, String fuel);
    //------------------------------PAGINACION-------------------------------------
    Page<Truck> findByMatriculaContainingOrFuelContaining(String matricula, String fuel, Pageable pageable);
}
