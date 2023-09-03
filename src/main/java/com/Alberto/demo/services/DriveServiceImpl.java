package com.Alberto.demo.services;

import com.Alberto.demo.DTOs.DriverDTO;
import com.Alberto.demo.DTOs.Truck_DriverDTO;
import com.Alberto.demo.entities.Driver;
import com.Alberto.demo.entities.Fuel;
import com.Alberto.demo.entities.Truck;
import com.Alberto.demo.entities.Trucks_Driver;
import com.Alberto.demo.repository.BaseRepository;
import com.Alberto.demo.repository.DriverRepository;
import com.Alberto.demo.repository.TruckRepository;
import com.Alberto.demo.repository.Truck_DriverRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DriveServiceImpl extends BaseServiceImpl<Driver,Long> implements DriveService{

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private Truck_DriverRepository truckDriverRepository;


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
            DriverDTO driverDTO = modelMapper.map(driver,DriverDTO.class);
            return driverDTO;

    }

    @Override
    public Truck_DriverDTO convertToDTO(Trucks_Driver trucks_driver) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Truck_DriverDTO truck_driverDTO = modelMapper.map(trucks_driver,Truck_DriverDTO.class);
        return truck_driverDTO;
    }

    @Override
    public Driver convertDTOtoEntity(DriverDTO driverDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Driver driver = modelMapper.map(driverDTO,Driver.class);
        return driver;
    }

    public List<DriverDTO> findAll() throws Exception {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        try{

            List<Driver> entities=driverRepository.findAll();
            return entities.stream().map(this::convertToDTO).collect(Collectors.toList());

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Transactional
    @Override
    public Truck_DriverDTO assign(Long driver_id, Long truck_id) throws Exception {
       Optional<Driver> driver = driverRepository.findById(driver_id);
       Optional<Truck> truck = truckRepository.findById(truck_id);
         if(driver.isEmpty()){
             throw new IllegalArgumentException("There is no such driver");
         } else if (truck.get().isUtilizado()) {
             throw new IllegalArgumentException("The Truck is current in use");
         }


        Trucks_Driver trucksDriver = new Trucks_Driver(driver.get(),truck.get(),LocalDate.now());

         Truck_DriverDTO truck_driverDTO = new Truck_DriverDTO(driver.get().getName(),truck.get().getMatricula(),LocalDate.now());

         truck.get().setUtilizado(true);

         truckDriverRepository.save(trucksDriver);

        return truck_driverDTO;

    }
    @Transactional
    @Override
    public boolean terminate_Use(Long driver_id) throws Exception {
        try {
            List<Trucks_Driver> trucks_driver = truckDriverRepository.findByDriverId(driver_id);
            if (trucks_driver.isEmpty()) {
                throw new IllegalArgumentException("There is no such driver");
            }
            for (Trucks_Driver list : trucks_driver) {
                if (list.getFecha_termino() == null) {
                    list.setFecha_termino(LocalDate.now());
                    truckDriverRepository.save(list);
                    Optional<Truck> truck = truckRepository.findById(list.getTruck().getId());
                    truck.get().setUtilizado(false);
                    return true;

                }
            }
            throw new IllegalArgumentException("The driver has no trucks assigned at this time");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }


    }

    @Override
    public boolean verify_Use(Long driver_id, Date filtro) throws Exception {
        List<Trucks_Driver> trucks_driver = truckDriverRepository.findDriver(driver_id,filtro);
        if(trucks_driver.isEmpty()){
            throw new IllegalArgumentException("There is no such driver using a truck that day");
        } else{
            return true;
        }

    }
    @Transactional
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
    @Transactional
    @Override
    public DriverDTO update(Long id, DriverDTO entity) throws Exception {
        try{
            //Optional<E> se emplea cuando existe la posibilidad de q no encuentre el objeto
            Optional<Driver> entityOptional = driverRepository.findById(id);

            if(entityOptional.isEmpty()){
                throw new IllegalArgumentException("There is no such truck");
            }
            Driver driver = convertDTOtoEntity(entity);


            if(driver.getAge()<=18){
                throw new IllegalArgumentException("Age must be over 18");
            } else if (!driver.getType().equals(entityOptional.get().getType())) {
                throw new IllegalArgumentException("Sex should remain the same, can't update");
                
            } else if (driver.getWage()<=(12000*0.21)) {
                throw new IllegalArgumentException("Wage should be greater than the 21% of 12000");
            }

            return convertToDTO(driverRepository.save(driver));
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }




}
