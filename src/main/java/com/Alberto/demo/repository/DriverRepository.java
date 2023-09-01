package com.Alberto.demo.repository;

import com.Alberto.demo.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends BaseRepository<Driver,Long> {

    List<Driver> findByNameContainingOrLastnameContaining(String name, String lastname);
    //------------------------------PAGINACION-------------------------------------
    Page<Driver> findByNameContainingOrLastnameContaining(String name, String lastname, Pageable pageable);
}
