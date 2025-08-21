package com.ets.bree.services;

import com.ets.bree.dtos.DeviceDto;
import com.ets.bree.models.Device;
import com.ets.bree.models.Status;
import com.ets.bree.models.User;
import com.ets.bree.repositories.DeviceRepository;
import com.ets.bree.repositories.StatusRepository;
import com.ets.bree.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
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

    public Optional<Device> patch(long id, Map<String, Object> fields) {
        Optional<Device> device = repository.findById(id);
        return device.map(d -> {fields.forEach((property, value) -> {
            switch (property) {
                case "name" -> d.setName((String) value);
                case "description" -> d.setDescription((String) value);
                case "location" -> d.setLocation((String) value);
                case "statusID" -> {
                    Optional<Status> status = statusRepository.findById((long)value);
                    status.ifPresent(d::setStatus);
                }
                case "ownerID" -> {
                    Optional<User> user = userRepository.findById((long)value);
                    user.ifPresent(d::setOwner);
                }
            }
        });
        return repository.save(d);
        });
    }
}
