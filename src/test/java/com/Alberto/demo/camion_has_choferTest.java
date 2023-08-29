package com.Alberto.demo;

import com.Alberto.demo.camion.Camion;
import com.Alberto.demo.chofer.Chofer;
import com.Alberto.demo.manytomany.AsignarCaminionesAChofer;
import com.Alberto.demo.manytomany.AsignarCamionChoferRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class camion_has_choferTest {

    @Autowired
   private AsignarCamionChoferRepo asignarCamionChoferRepo;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public  void anadirCamion(){
        Camion camion=testEntityManager.find(Camion.class,1);
        Chofer chofer=testEntityManager.find(Chofer.class,1);

        AsignarCaminionesAChofer tabla=new AsignarCaminionesAChofer(camion,chofer, LocalDate.of(2000, Month.AUGUST,2),LocalDate.of(2000,Month.DECEMBER,5));
        asignarCamionChoferRepo.save(tabla);

    }
    @Test
    public void actualizar(){
        AsignarCaminionesAChofer cc=asignarCamionChoferRepo.findById(1).get();
        cc.setFecha_inicio(LocalDate.of(2023,Month.SEPTEMBER,26));
        cc.setChofer(new Chofer(2));
    }

    @Test
    public void eliminar(){
        asignarCamionChoferRepo.deleteById(1);

    }

    @Test
    public void Listar() {

        List<AsignarCaminionesAChofer> list = asignarCamionChoferRepo.findAll();

        for (AsignarCaminionesAChofer lista : list) {
            System.out.println(lista.getCamion());
        }
    }
}
