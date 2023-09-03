package com.Alberto.demo.services;

import com.Alberto.demo.DTOs.DriverDTO;
import com.Alberto.demo.DTOs.Truck_DriverDTO;
import com.Alberto.demo.entities.Driver;
import com.Alberto.demo.entities.Trucks_Driver;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DriveService extends BaseService<Driver,Long>{

    List<Driver> search(String filtro) throws Exception;

    //--------------------------PAGINACION-----------------------
    Page<Driver> search(String filtro, Pageable pageable) throws Exception;

    public DriverDTO convertToDTO(Driver driver);

    public Truck_DriverDTO convertToDTO(Trucks_Driver trucks_driver);

    public Driver convertDTOtoEntity(DriverDTO driverDTO);

    public DriverDTO update(Long id, DriverDTO entity) throws Exception;

    public DriverDTO save(DriverDTO entity) throws Exception;

    public List<DriverDTO> findAll()throws Exception;

    public Truck_DriverDTO assign(Long driver_id, Long truck_id)throws Exception;
}
