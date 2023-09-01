package com.Alberto.demo.services;

import com.Alberto.demo.DTOs.DriverDTO;
import com.Alberto.demo.entities.Driver;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DriveService extends BaseService<Driver,Long>{

    List<Driver> search(String filtro) throws Exception;

    //--------------------------PAGINACION-----------------------
    Page<Driver> search(String filtro, Pageable pageable) throws Exception;

    public DriverDTO convertToDTO(Driver driver);

    public Driver convertDTOtoEntity(DriverDTO driverDTO);

    public DriverDTO update(Long id, DriverDTO entity) throws Exception;

    public DriverDTO save(DriverDTO entity) throws Exception;

    public List<DriverDTO> findAll()throws Exception;
}
