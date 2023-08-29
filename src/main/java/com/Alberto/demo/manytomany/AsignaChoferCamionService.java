package com.Alberto.demo.manytomany;

import com.Alberto.demo.camion.Camion;
import com.Alberto.demo.chofer.Chofer;
import com.Alberto.demo.user.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignaChoferCamionService {

    private final AsignarCamionChoferRepo asignarCamionChoferRepo;


    public AsignaChoferCamionService(AsignarCamionChoferRepo asignarCamionChoferRepo) {
        this.asignarCamionChoferRepo = asignarCamionChoferRepo;
    }

    public List<AsignarCaminionesAChofer> listadoQuery(){
        return (List<AsignarCaminionesAChofer>)asignarCamionChoferRepo.findAllquery();
    }
    public List<AsignarCaminionesAChofer> listadoporChofer(Integer id){

        return (List<AsignarCaminionesAChofer>) asignarCamionChoferRepo.findbyChofer(id);
    }

    public List<AsignarCaminionesAChofer> listadoPorFecha(){

        return (List<AsignarCaminionesAChofer>)asignarCamionChoferRepo.findAllByFecha_Termino();
    }
   public List<AsignarCaminionesAChofer> listAll(){
       return (List<AsignarCaminionesAChofer>)asignarCamionChoferRepo.findAll();
   }

    public void salvar( AsignarCaminionesAChofer camion) {
        asignarCamionChoferRepo.save(camion);
    }



    public AsignarCaminionesAChofer get(Integer id) throws UserNotFoundException {

        Optional<AsignarCaminionesAChofer> result = asignarCamionChoferRepo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("No se Encontro al Camion especificado"+id);

    }
}
