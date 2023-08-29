package com.Alberto.demo.manytomany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsignarCamionChoferRepo extends JpaRepository<AsignarCaminionesAChofer,Integer> {



@Query(value = "SELECT camion_has_chofer.id,camion.matricula,chofer.nombre,camion_has_chofer.fecha_inicio FROM camion_has_chofer INNER JOIN" +
        "camion ON camion_has_chofer.camion_id=camion.id INNER JOIN chofer ON camion_has_chofer.chofer_id=chofer.id", nativeQuery = true)
    List<AsignarCaminionesAChofer>findAllquery();

@Query(value = "SELECT * FROM camion_has_chofer WHERE camion_has_chofer.fecha_termino IS NULL",nativeQuery = true)
    List<AsignarCaminionesAChofer>findAllByFecha_Termino();

@Query(value = "SELECT * FROM camion_has_chofer WHERE camion_has_chofer.camion_id=?1",nativeQuery = true)
    Optional<AsignarCaminionesAChofer>findbyCamion(Integer id);
    @Query(value = "SELECT * FROM camion_has_chofer WHERE camion_has_chofer.chofer_id=?1",nativeQuery = true)
    List<AsignarCaminionesAChofer>findbyChofer(Integer id);

}
