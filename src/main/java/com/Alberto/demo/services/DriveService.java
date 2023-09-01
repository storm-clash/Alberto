package com.Alberto.demo.services;

import com.Alberto.demo.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DriveService extends BaseService<Driver,Long>{

    List<Driver> search(String filtro) throws Exception;

    //--------------------------PAGINACION-----------------------
    Page<Driver> search(String filtro, Pageable pageable) throws Exception;
}
