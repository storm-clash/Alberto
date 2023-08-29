package com.Alberto.demo.chofer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoferRepository extends CrudRepository<Chofer,Integer> {
    Long countById(Integer id);


}
