package com.ets.bree.controllers;

import com.ets.bree.models.AccessLevel;
import com.ets.bree.services.AccessLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/access_levels")
public class AccessLevelController {

    @Autowired
    private AccessLevelService service;

    @GetMapping
    public ResponseEntity<List<AccessLevel>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

//    @GetMapping
//    public String teste(){
//        return "Hello";
//    }
}
