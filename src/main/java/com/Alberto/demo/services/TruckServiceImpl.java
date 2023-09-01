package com.Alberto.demo.services;

import com.Alberto.demo.DTOs.DriverDTO;
import com.Alberto.demo.DTOs.TruckDTO;
import com.Alberto.demo.entities.Driver;
import com.Alberto.demo.entities.Truck;
import com.Alberto.demo.repository.BaseRepository;
import com.Alberto.demo.repository.TruckRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TruckServiceImpl extends BaseServiceImpl<Truck,Long> implements TruckService{

    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private ModelMapper modelMapper;


    public TruckServiceImpl(BaseRepository<Truck, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public List<Truck> search(String filtro, double filtro2) throws Exception {
        try{
            List<Truck> trucks = truckRepository.findByMatriculaContainingOrKilometrajeContaining(filtro,filtro2);
            return trucks;

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Truck> search(String filtro, double filtro2, Pageable pageable) throws Exception {
        try {
            Page<Truck> trucks = truckRepository.findByMatriculaContainingOrKilometrajeContaining(filtro,filtro2,pageable);
            return trucks;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public TruckDTO convertToDTO(Truck truck) {
        TruckDTO truckDTO = modelMapper.map(truck,TruckDTO.class);
        return truckDTO;
    }

    @Override
    public Truck convertDTOtoEntity(TruckDTO truckDTO){
        Truck truck = modelMapper.map(truckDTO,Truck.class);
        return truck;
    }
    public List<TruckDTO> findAll() throws Exception {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        try{

            List<Truck> entities=truckRepository.findAll();
            return entities.stream().map(this::convertToDTO).collect(Collectors.toList());

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public TruckDTO save(TruckDTO entity) throws Exception {
        try{

            Truck truck = convertDTOtoEntity(entity);


            if(truck.getKilometraje()<=0){
                throw new IllegalArgumentException("mileage must be over 0");
            }  else if (truck.getCapacidad()<0) {
                throw new IllegalArgumentException("capacity should be greater than 0");
            }
            Truck truckCreated = truckRepository.save(truck);
            return convertToDTO(truckCreated);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public TruckDTO update(Long id, TruckDTO entity) throws Exception {
        try{
            //Optional<E> se emplea cuando existe la posibilidad de q no encuentre el objeto
            Optional<Truck> entityOptional = truckRepository.findById(id);
            Truck truck = convertDTOtoEntity(entity);


            if(truck.getKilometraje()<entityOptional.get().getKilometraje()){
                throw new IllegalArgumentException("mileage must be higher than current");
            } else if (!truck.getType().equals(entityOptional.get().getType())) {
                throw new IllegalArgumentException("Fuel should remain the same, can't update");

            }

            return convertToDTO(truckRepository.save(truck));
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
