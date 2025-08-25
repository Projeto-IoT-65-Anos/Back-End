package com.ets.bree.controllers;

import com.ets.bree.dtos.RegisterDto;
import com.ets.bree.models.Register;
import com.ets.bree.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registers")
public class RegisterController {

    @Autowired
    private RegisterService service;

    @GetMapping
    public ResponseEntity<List<Register>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Register> getRegister(@PathVariable long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Register> postRegister(@RequestBody RegisterDto dto) {
        return service.post(dto)
                .map(r -> ResponseEntity.status(HttpStatus.CREATED).body(r))
                .orElse(ResponseEntity.notFound().build());
    }
}
