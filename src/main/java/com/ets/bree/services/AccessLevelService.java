package com.ets.bree.services;

import com.ets.bree.dtos.AccessLevelDto;
import com.ets.bree.models.AccessLevel;
import com.ets.bree.repositories.AccessLevelRepository;
import org.springframework.beans.BeanUtils;
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

    public AccessLevel post(AccessLevelDto dto) {
        System.out.println("DTO " + dto);
        AccessLevel accessLevel = new AccessLevel();
        BeanUtils.copyProperties(dto, accessLevel);
        repository.save(accessLevel);
        return accessLevel;
    }

    public Optional<AccessLevel> delete(Long id) {
        Optional<AccessLevel> accessLevel = repository.findById(id);
        if(accessLevel.isPresent()) {
            repository.delete(accessLevel.get());
        }
        return accessLevel;
    }

}
