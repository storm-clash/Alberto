package com.Alberto.demo.controllers;

import com.Alberto.demo.DTOs.DriverDTO;
import com.Alberto.demo.entities.Driver;
import com.Alberto.demo.services.DriveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/driver")
@EnableTransactionManagement

public class DriverController extends BaseControllerImpl<Driver, DriveServiceImpl>{

    @Autowired
    private DriveServiceImpl driveService;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(driveService.findAll());

        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":Error, Por favor intente mas tarde.\"}");
        }

    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody DriverDTO entity){

        return ResponseEntity
                .accepted()
                .body(driveService.save(entity));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody DriverDTO entity){

            return ResponseEntity
                    .accepted()
                    .body(driveService.update(id,entity));



    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String filtro){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.search(filtro));

        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/searchPaged")
    public ResponseEntity<?> search(@RequestParam String filtro, Pageable pageable){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.search(filtro,pageable));

        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @PostMapping("/assign_Driver/{driver_id}")
    public ResponseEntity<?> assign_Driver(@PathVariable Long driver_id,@RequestParam Long truck_id){

            return ResponseEntity
                    .accepted()
                    .body(driveService.assign(driver_id,truck_id));



    }

    @GetMapping("/terminate_Use/{driver_id}")
    public ResponseEntity<?> terminate_Use(@PathVariable Long driver_id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(driveService.terminate_Use(driver_id));

        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":Error, Por favor intente mas tarde.\"}");
        }

    }

    @GetMapping("/verify_use/{driver_id}")
    public ResponseEntity<?> verify_UseOfATruck(@PathVariable Long driver_id, @RequestParam Date filtro){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(driveService.verify_Use(driver_id,filtro));

        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":Error, Por favor intente mas tarde.\"}");
        }

    }


    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(driveService.delete(id));

        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":Error, Por favor intente mas tarde.\"}");
        }
    }
}