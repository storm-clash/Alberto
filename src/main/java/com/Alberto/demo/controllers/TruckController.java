package com.Alberto.demo.controllers;

import com.Alberto.demo.entities.Truck;
import com.Alberto.demo.services.TruckServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/truck")

public class TruckController extends BaseControllerImpl<Truck, TruckServiceImpl>{

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
}