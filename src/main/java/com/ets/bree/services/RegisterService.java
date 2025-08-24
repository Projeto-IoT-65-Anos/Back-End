package com.ets.bree.services;

import com.ets.bree.models.Register;
import com.ets.bree.repositories.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository repository;

    public List<Register> findAll() {
        return repository.findAll();
    }

    public Optional<Register> findById(long id) {
        return repository.findById(id);
    }
}
