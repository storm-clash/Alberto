package com.Alberto.demo.controllers;
import com.Alberto.demo.entities.Base;
import com.Alberto.demo.services.BaseServiceImpl;
import com.Alberto.demo.services.DriveServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class BaseControllerImpl <E extends Base, S extends BaseServiceImpl<E,Long>> implements BaseController<E,Long> {

    @Autowired
    protected S servicio;





    //ResponseEntity->Brinda los status de respuesta en formato JSON

    @GetMapping("/paged")//Paginacion
    public ResponseEntity<?> getAll(Pageable pageable){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll(pageable));

        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":Error, Por favor intente mas tarde.\"}");
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findById(id));

        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":Error, Por favor intente mas tarde.\"}");
        }
    }



}
