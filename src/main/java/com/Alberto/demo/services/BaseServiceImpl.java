package com.Alberto.demo.services;

import com.Alberto.demo.DTOs.DriverDTO;
import com.Alberto.demo.DTOs.TruckDTO;
import com.Alberto.demo.entities.Driver;
import com.Alberto.demo.entities.Truck;
import com.Alberto.demo.repository.BaseRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.Alberto.demo.entities.Base;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<E extends Base,ID extends Serializable> implements BaseService<E,ID> {

    protected BaseRepository<E,ID> baseRepository;

    @Autowired
    private ModelMapper modelMapper;


    public BaseServiceImpl(BaseRepository<E, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }
   /* @Override
    @Transactional

    public List<E> findAll() throws Exception {
        try{
            List<E> entities=baseRepository.findAll();
            return entities;

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }*/
    @Override
    @Transactional
    public Page<E> findAll(Pageable pageable) throws Exception{
        try{
            Page<E> entities=baseRepository.findAll(pageable);
            return entities;

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }



    }

    @Override
    @Transactional
    public E findById(ID id) throws Exception {
        try{
            Optional<E> entityOptional= baseRepository.findById(id);
            return entityOptional.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());

        }
    }

   /* @Override
    @Transactional
    public E save(E entity) throws Exception {
        try{
            entity=baseRepository.save(entity);
            return entity;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E update(ID id, E entity) throws Exception {
        try{
            //Optional<E> se emplea cuando existe la posibilidad de q no encuentre el objeto
            Optional<E> entityOptional = baseRepository.findById(id);
            E entityUpdate = entityOptional.get();
            entityUpdate = baseRepository.save(entity);
            return entityUpdate;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }*/

    @Override
    @Transactional
    public boolean delete(ID id) throws Exception {
        try{
            if(baseRepository.existsById(id)){
                baseRepository.deleteById(id);
                return true;
            }else {
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }





}
