package com.ets.bree.controllers;

import com.ets.bree.dtos.StatusDto;
import com.ets.bree.models.Status;
import com.ets.bree.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private StatusService service;

    @GetMapping
    public ResponseEntity<List<Status>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Status> getStatus(@PathVariable long id) {
        return service.getById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Status> postStatus(@RequestBody StatusDto dto) {
        return ResponseEntity.ok(service.post(dto));
    }


}
