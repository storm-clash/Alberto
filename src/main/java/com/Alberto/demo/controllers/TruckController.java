package com.Alberto.demo.controllers;

import com.Alberto.demo.DTOs.TruckDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface TruckController {

    public ResponseEntity<?> getAll();
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody TruckDTO entity);
    public ResponseEntity<?> save(@RequestBody TruckDTO entity);



}
