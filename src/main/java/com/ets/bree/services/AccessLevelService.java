package com.ets.services;

import com.ets.models.AccessLevel;
import com.ets.repositories.AccessLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessLevelService {

    @Autowired
    private AccessLevelRepository repository;

    public List<AccessLevel> getAll() {
        return repository.findAll();
    }

}
