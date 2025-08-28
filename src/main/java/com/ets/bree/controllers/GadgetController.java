package com.ets.bree.controllers;

import com.ets.bree.dtos.GadgetDto;
import com.ets.bree.models.Gadget;
import com.ets.bree.services.GadgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
