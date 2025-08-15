package com.ets.bree.services;

import com.ets.bree.models.AccessLevel;
import com.ets.bree.repositories.AccessLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccessLevelService {

    @Autowired
    private AccessLevelRepository repository;

    public List<AccessLevel> getAll() {
        return repository.findAll();
    }

    public Optional<AccessLevel> getById(Long id) {
        return repository.findById(id);
    }

}
