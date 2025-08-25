package com.ets.bree.controllers;

import com.ets.bree.dtos.UserDeviceDto;
import com.ets.bree.models.UserDevice;
import com.ets.bree.services.UserDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserDeviceController {

    @Autowired
    private UserDeviceService service;

    @GetMapping
    public ResponseEntity<List<UserDevice>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDevice> getUserDevice(@PathVariable long id) {
        return service.findUserDevice(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDevice> postUserDevice(@RequestBody UserDeviceDto dto) {

    }
}
