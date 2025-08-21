package com.ets.bree.controllers;

import com.ets.bree.dtos.DeviceDto;
import com.ets.bree.models.Device;
import com.ets.bree.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceService service;

    @GetMapping
    public ResponseEntity<List<Device>> findAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> findDevice(@PathVariable long id) {
        return service.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Device> postDevice(@RequestBody DeviceDto dto) {
        return service.post(dto)
                .map(d -> ResponseEntity.status(HttpStatus.CREATED).body(d))
                .orElse(ResponseEntity.notFound().build());
    }
}
