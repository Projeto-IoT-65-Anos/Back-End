package com.ets.bree.services;

import com.ets.bree.models.Device;
import com.ets.bree.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository repository;

    public List<Device> getAll() {
        return repository.findAll();
    }

    public Optional<Device> getById(long id) {
        return repository.findById(id);
    }
}
