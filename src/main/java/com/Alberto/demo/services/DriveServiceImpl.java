package com.Alberto.demo.services;

import com.Alberto.demo.entities.Driver;
import com.Alberto.demo.repository.BaseRepository;
import com.Alberto.demo.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriveServiceImpl extends BaseServiceImpl<Driver,Long> implements DriveService{

    @Autowired
    private DriverRepository driverRepository;

    public DriveServiceImpl(BaseRepository<Driver, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public List<Driver> search(String filtro) throws Exception {
        try{
            List<Driver> drivers = driverRepository.findByNameContainingOrLastnameContaining(filtro,filtro);
            return drivers;

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Driver> search(String filtro, Pageable pageable) throws Exception {
        try {
            Page<Driver> drivers = driverRepository.findByNameContainingOrLastnameContaining(filtro,filtro,pageable);
            return drivers;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
