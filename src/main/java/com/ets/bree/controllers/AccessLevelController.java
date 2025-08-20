package com.ets.bree.controllers;
import com.ets.bree.dtos.AccessLevelDto;
import com.ets.bree.models.AccessLevel;
import com.ets.bree.services.AccessLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/access_levels")
public class AccessLevelController {

    @Autowired
    private AccessLevelService service;

    @GetMapping
    public ResponseEntity<List<AccessLevel>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAccessLevel(@PathVariable Long id) {
        Optional<AccessLevel> level = service.getById(id);
        return level.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 Not Found"));
    }

    @PostMapping
    public ResponseEntity<AccessLevel> postAccessLevel(@RequestBody AccessLevelDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.post(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccessLevel> putAccessLevel(@PathVariable long id, @RequestBody AccessLevelDto dto) {
        Optional<AccessLevel> level = service.getById(id);
        return level.map(accessLevel -> {return ResponseEntity.accepted().body(service.put(accessLevel, dto));})
                .orElse(ResponseEntity.notFound().build());
    }
}
