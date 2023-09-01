package com.Alberto.demo.services;


import com.Alberto.demo.entities.Truck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TruckService extends BaseService<Truck,Long>{

    List<Truck> search(String filtro) throws Exception;

    //--------------------------PAGINACION-----------------------
    Page<Truck> search(String filtro, Pageable pageable) throws Exception;

}