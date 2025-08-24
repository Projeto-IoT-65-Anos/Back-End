package com.ets.bree.services;

import com.ets.bree.models.Register;
import com.ets.bree.repositories.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository repository;

    public List<Register> findAll() {
        return repository.findAll();
    }
}
