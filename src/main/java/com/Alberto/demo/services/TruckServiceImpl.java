package com.Alberto.demo.services;

import com.Alberto.demo.DTOs.DriverDTO;
import com.Alberto.demo.DTOs.TruckDTO;
import com.Alberto.demo.entities.Driver;
import com.Alberto.demo.entities.Truck;
import com.Alberto.demo.repository.BaseRepository;
import com.Alberto.demo.repository.TruckRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public TruckDTO convertToDTO(Truck truck) throws Exception {
        TruckDTO truckDTO = new TruckDTO();
        truckDTO = modelMapper.map(truck,TruckDTO.class);
        return truckDTO;
    }

    @Override
    public Truck convertDTOtoEntity(TruckDTO truckDTO) throws Exception {
        Truck truck = new Truck();
        truck = modelMapper.map(truckDTO,Truck.class);
        return truck;
    }
}
