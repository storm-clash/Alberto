package com.Alberto.demo.repository;

import com.Alberto.demo.entities.Truck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TruckRepository extends BaseRepository<Truck,Long> {

    List<Truck> findByMatriculaContainingOrKilometrajeContaining(String matricula, double kilometraje);
    //------------------------------PAGINACION-------------------------------------
    Page<Truck> findByMatriculaContainingOrKilometrajeContaining(String matricula, double kilometraje, Pageable pageable);
}
