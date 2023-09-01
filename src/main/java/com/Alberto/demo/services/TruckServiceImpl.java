package com.Alberto.demo.services;

import com.Alberto.demo.entities.Truck;
import com.Alberto.demo.repository.BaseRepository;
import com.Alberto.demo.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TruckServiceImpl extends BaseServiceImpl<Truck,Long> implements TruckService{

    @Autowired
    private TruckRepository truckRepository;


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
}
