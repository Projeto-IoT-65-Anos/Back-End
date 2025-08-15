package com.ets.bree.controllers;
import com.ets.bree.models.AccessLevel;
import com.ets.bree.services.AccessLevelService;
import org.springframework.beans.factory.annotation.Autowired;
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
        Optional<AccessLevel> accessLevel = service.getById(id);
        if(accessLevel.isPresent()) return ResponseEntity.ok(accessLevel.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 Not Found");
    }
}
