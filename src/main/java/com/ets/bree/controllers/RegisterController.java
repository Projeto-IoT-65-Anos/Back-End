package com.ets.bree.controllers;

import com.ets.bree.models.Register;
import com.ets.bree.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
