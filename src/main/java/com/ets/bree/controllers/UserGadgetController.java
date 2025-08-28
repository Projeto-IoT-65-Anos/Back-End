package com.ets.bree.controllers;

import com.ets.bree.dtos.UserGadgetDto;
import com.ets.bree.models.UserGadget;
import com.ets.bree.services.UserGadgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
public class UserGadgetController {

    @Autowired
    private UserGadgetService service;

    @GetMapping
    public ResponseEntity<List<UserGadget>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGadget> getUserGadget(@PathVariable long id) {
        return service.findUserGadget(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<UserGadget>> getUserGadgetByUser(@PathVariable long id) {
        return service.findUserGadgetByUser(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/gadget/{id}")
    public ResponseEntity<List<UserGadget>> getUserGadgetByGadget(@PathVariable long id) {
        return service.findUserGadgetByGadget(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserGadget> postUserGadget(@RequestBody UserGadgetDto dto) {
        return service.post(dto)
                .map(ug -> ResponseEntity.status(HttpStatus.CREATED).body(ug))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserGadget(@PathVariable long id) {
        return service.delete(id)
                .map(_ -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }
}
