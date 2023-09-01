package com.Alberto.demo.services;

import com.Alberto.demo.DTOs.DriverDTO;
import com.Alberto.demo.entities.Driver;
import com.Alberto.demo.repository.BaseRepository;
import com.Alberto.demo.repository.DriverRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DriveServiceImpl extends BaseServiceImpl<Driver,Long> implements DriveService{

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private ModelMapper modelMapper;

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

    @Override
    public DriverDTO convertToDTO(Driver driver)  {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
            DriverDTO driverDTO = new DriverDTO();
            driverDTO = modelMapper.map(driver, DriverDTO.class);
            return driverDTO;

    }

    @Override
    public Driver convertDTOtoEntity(DriverDTO driverDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Driver driver = new Driver();
        driver = modelMapper.map(driverDTO,Driver.class);
        return driver;
    }

    public List<DriverDTO> findAll() throws Exception {
        try{

            List<Driver> entities=driverRepository.findAll();
            return entities.stream().map(this::convertToDTO).collect(Collectors.toList());

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public DriverDTO save(DriverDTO entity) throws Exception {
        try{

            Driver driver = convertDTOtoEntity(entity);


            if(driver.getAge()<=18){
                throw new IllegalArgumentException("Age must be over 18");
            }  else if (driver.getWage()<=(12000*0.21)) {
                throw new IllegalArgumentException("Wage should be greater than the 21% of 12000");
            }
            Driver driverCreated = driverRepository.save(driver);
            return convertToDTO(driverCreated);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public DriverDTO update(Long id, DriverDTO entity) throws Exception {
        try{
            //Optional<E> se emplea cuando existe la posibilidad de q no encuentre el objeto
            Optional<Driver> entityOptional = driverRepository.findById(id);
            Driver driver = convertDTOtoEntity(entity);


            if(driver.getAge()<=18){
                throw new IllegalArgumentException("Age must be over 18");
            } else if (!driver.getType().equals(entityOptional.get().getType())) {
                throw new IllegalArgumentException("Sex should remain the same, can't update");
                
            } else if (driver.getWage()>=(12000*0.21)) {
                throw new IllegalArgumentException("Wage should be greater than the 21% of 12000");
            }

            return convertToDTO(driverRepository.save(driver));
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }




}
