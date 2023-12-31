package com.Alberto.demo.repository;

import com.Alberto.demo.entities.Driver;
import com.Alberto.demo.entities.Fuel;
import com.Alberto.demo.entities.Truck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TruckRepository extends BaseRepository<Truck,Long> {



    List<Truck> findByMatriculaContaining(String matricula);
    //------------------------------PAGINACION-------------------------------------
    Page<Truck> findByMatriculaContaining(String matricula,Pageable pageable);


    @Query(value = "SELECT * FROM truck WHERE truck.matricula LIKE %:filtro%",nativeQuery = true)
    List<Truck> seach(@Param("filtro") String filtro);


}
