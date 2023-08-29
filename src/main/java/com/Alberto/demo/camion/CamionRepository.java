package com.Alberto.demo.camion;

import com.Alberto.demo.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CamionRepository extends CrudRepository<Camion,Integer> {
    Long countById(Integer id);

   @Query(value = "SELECT * FROM camion WHERE camion.utilizado=?1",
            nativeQuery = true)
    List<Camion> findByUtilizado(boolean mat);

    @Query(value = "SELCT * FROM camion WHERE camion.matricula=?1 OR camion.combustible=?2 OR camion.kilometraje=?3 OR " +
            "camion.matricula=?2 OR camion.combustible=?1 OR camion.kilometraje=?2 OR camion.matricula=?3 OR camion.combustible=?3 OR camion.kilometraje=?1", nativeQuery = true)
    List<Camion> findBusquesda(String matricula, String combustible, double kilometraje);

    @Query(value = "SELECT * FROM camion  WHERE CONCAT(camion.id,camion.matricula,camion.capacidad,camion.kilometraje,camion.ruedas,camion.combustible) LIKE %?1%",nativeQuery = true)
    List<Camion>findConcatenar(String valor);
}
