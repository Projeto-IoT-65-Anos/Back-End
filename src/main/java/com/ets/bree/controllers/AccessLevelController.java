package com.ets.controllers;

import com.ets.models.AccessLevel;
import com.ets.services.AccessLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/access_levels")
public class AccessLevelController {

    @Autowired
    private AccessLevelService service;

    @GetMapping
    public ResponseEntity<List<AccessLevel>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
