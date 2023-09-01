package com.Alberto.demo.services;


import com.Alberto.demo.DTOs.DriverDTO;
import com.Alberto.demo.DTOs.TruckDTO;
import com.Alberto.demo.entities.Driver;
import com.Alberto.demo.entities.Truck;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TruckService extends BaseService<Truck,Long>{

    List<Truck> search(String filtro, double filtro2) throws Exception;

    //--------------------------PAGINACION-----------------------
    Page<Truck> search(String filtro,double filtro2 ,Pageable pageable) throws Exception;

    public TruckDTO convertToDTO(Truck truck);

    public Truck convertDTOtoEntity(TruckDTO truckDTO);

    public TruckDTO update(Long id, TruckDTO entity) throws Exception;

    public TruckDTO save(TruckDTO entity) throws Exception;



}
