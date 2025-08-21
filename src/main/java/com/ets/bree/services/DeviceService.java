package com.ets.bree.services;

import com.ets.bree.dtos.DeviceDto;
import com.ets.bree.models.Device;
import com.ets.bree.models.Status;
import com.ets.bree.repositories.DeviceRepository;
import com.ets.bree.repositories.StatusRepository;
import com.ets.bree.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository repository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Device> getAll() {
        return repository.findAll();
    }

    public Optional<Device> getById(long id) {
        return repository.findById(id);
    }

    public Optional<Device> post(DeviceDto dto) {
        Device device = new Device();
        BeanUtils.copyProperties(dto, device);
        device.setRegisterDate(LocalDateTime.now());
        return statusRepository.findById(dto.statusID()).map(s -> {
            device.setStatus(s);
            return userRepository.findById(dto.ownerID()).map(o -> {
                device.setOwner(o);
                repository.save(device);
                return device;
            });
        }).orElse(Optional.empty());
    }
}
