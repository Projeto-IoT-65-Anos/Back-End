package com.ets.bree.services;

import com.ets.bree.models.Status;
import com.ets.bree.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {

    @Autowired
    private StatusRepository repository;

    public List<Status> getAll() {
        return repository.findAll();
    }

    public Optional<Status> getById(Long id) {
        return repository.findById(id);
    }
}
