package com.Alberto.demo.controllers;

import com.Alberto.demo.DTOs.TruckDTO;
import com.Alberto.demo.entities.Truck;
import com.Alberto.demo.services.TruckServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/truck")
@EnableTransactionManagement

public class TruckControllerImpl extends BaseControllerImpl<Truck, TruckServiceImpl> implements TruckController{

    @Autowired
    private TruckServiceImpl truckService;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(truckService.findAll());

        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":Error, Por favor intente mas tarde.\"}");
        }

    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody TruckDTO entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(truckService.save(entity));

        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":Error, Por favor intente mas tarde.\"}");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody TruckDTO entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(truckService.update(id,entity));

        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":Error, Por favor intente mas tarde.\"}");
        }

    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String filtro,@RequestParam Double filtro2){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.search(filtro,filtro2));

        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/searchPaged")
    public ResponseEntity<?> search(@RequestParam String filtro, @RequestParam Double filtro2,Pageable pageable){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.search(filtro,filtro2,pageable));

        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(truckService.delete(id));

        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":Error, Por favor intente mas tarde.\"}");
        }
    }
}