package com.ets.bree.controllers;

import com.ets.bree.dtos.UserDto;
import com.ets.bree.models.User;
import com.ets.bree.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody UserDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.post(dto));
    }
}
