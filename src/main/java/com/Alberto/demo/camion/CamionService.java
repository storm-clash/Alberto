package com.Alberto.demo.camion;


import com.Alberto.demo.chofer.Chofer;
import com.Alberto.demo.user.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CamionService {

   private final CamionRepository camionRepository;

    private Camion camionAuxiliar;

    public Camion getCamionAuxiliar() {
        return camionAuxiliar;
    }

    public void setCamionAuxiliar(Camion camionAuxiliar) {
        this.camionAuxiliar = camionAuxiliar;
    }

    public List<Camion> listaBuscar(String busqueda){
            if(busqueda != null){
                return camionRepository.findConcatenar(busqueda);
            }
        return (List<Camion>)camionRepository.findAll();
    }

    public CamionService(CamionRepository camionRepository) {
        this.camionRepository = camionRepository;
    }
    public List<Camion> listAll(){
        return (List<Camion>)camionRepository.findAll();
    }
    public List<Camion> listCamionNoUso(boolean mat) throws Exception{

        try{
            List<Camion> listcamiones = camionRepository.findByUtilizado(mat);
            return listcamiones;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    public void salvar( Camion camion) {
        camionRepository.save(camion);
    }
    public Camion get(Integer id) throws UserNotFoundException {

        Optional<Camion> result = camionRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("No se Encontro al Camion especificado"+id);

    }
    public void delete(Integer id) throws UserNotFoundException{
        Long count= camionRepository.countById(id);
        if(count==null || count==0){
            throw new UserNotFoundException("No se Encontro al camion especificado"+id);
        }
        camionRepository.deleteById(id);
    }
}
