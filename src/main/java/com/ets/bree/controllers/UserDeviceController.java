package com.ets.bree.controllers;

import com.ets.bree.dtos.UserDeviceDto;
import com.ets.bree.models.UserDevice;
import com.ets.bree.services.UserDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user_device")
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

    @GetMapping("/user/{id}")
    public ResponseEntity<List<UserDevice>> getUserDeviceByUser(@PathVariable long id) {
        return service.findByUser(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/device/{id}")
    public ResponseEntity<List<UserDevice>> getUserDeviceByDevice(@PathVariable long id) {
        return service.findByDevice(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDevice> postUserDevice(@RequestBody UserDeviceDto dto) {
        return service.post(dto)
                .map(ud -> ResponseEntity.status(HttpStatus.CREATED).body(ud))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserDevice(@PathVariable long id) {
        return service.delete(id)
                .map(_ -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }
}
