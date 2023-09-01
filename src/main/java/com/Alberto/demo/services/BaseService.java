package com.Alberto.demo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.Alberto.demo.entities.Base;
import java.io.Serializable;
import java.util.List;

public interface BaseService<E extends Base,ID extends Serializable> {//Para que sea GENERICA

    public List<E> findAll()throws Exception;

    //PAGINACION
    public Page<E> findAll(Pageable pageable) throws Exception;

//------------------------------------------------------------------------

    public E findById(ID id)throws Exception;

    public E save(E entity) throws Exception;

    public E update(ID id, E entity) throws Exception;

    public boolean delete(ID id) throws Exception;

}

