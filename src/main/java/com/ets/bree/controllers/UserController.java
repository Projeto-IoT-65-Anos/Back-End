package com.ets.bree.controllers;

import com.ets.bree.dtos.UserDto;
import com.ets.bree.models.User;
import com.ets.bree.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        Optional<User> user = service.getById(id);
        return user.map(_ -> ResponseEntity.ok(user.get())).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody UserDto dto) {
        Optional<User> user = service.post(dto);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> putUser(@PathVariable long id, @RequestBody UserDto dto) {
        Optional<User> oUser = service.put(id, dto);
        return oUser.map(u -> ResponseEntity.accepted().body(u))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUser(@PathVariable long id, @RequestBody Map<String, Object> fields) {
        return service.patch(id, fields)
                .map(u -> ResponseEntity.accepted().body(u))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable long id) {
        return service.delete(id)
                .map(_ -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }
}
