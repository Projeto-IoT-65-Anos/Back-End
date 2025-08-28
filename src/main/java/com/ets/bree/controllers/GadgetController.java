package com.ets.bree.controllers;

import com.ets.bree.dtos.GadgetDto;
import com.ets.bree.models.Gadget;
import com.ets.bree.services.GadgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gadgets")
public class GadgetController {

    @Autowired
    private GadgetService service;

    @GetMapping
    public ResponseEntity<List<Gadget>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gadget> getGadget(@PathVariable long id) {
        return service.findGadget(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Gadget> postGadget(@RequestBody GadgetDto dto) {
        return service.post(dto)
                .map(g -> ResponseEntity.status(HttpStatus.CREATED).body(g))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGadget(@PathVariable long id) {
        return service.delete(id)
                .map(_ -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Gadget> patchGadget(@PathVariable long id, @RequestBody Map<String, Object> fields) {
        return service.patch(id, fields)
                .map(g -> ResponseEntity.accepted().body(g))
                .orElse(ResponseEntity.notFound().build());
    }
}
