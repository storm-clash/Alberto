package com.Alberto.demo.repository;

import com.Alberto.demo.entities.Base;
import com.Alberto.demo.entities.Driver;
import com.Alberto.demo.entities.Truck;
import com.Alberto.demo.entities.Trucks_Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.time.LocalDate;

@NoRepositoryBean
public interface BaseRepository<E extends Base,ID extends Serializable> extends JpaRepository<E,ID> {


}
