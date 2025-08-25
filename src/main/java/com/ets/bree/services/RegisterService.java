package com.ets.bree.services;

import com.ets.bree.dtos.RegisterDto;
import com.ets.bree.models.Device;
import com.ets.bree.models.Register;
import com.ets.bree.repositories.DeviceRepository;
import com.ets.bree.repositories.RegisterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository repository;
    @Autowired
    private DeviceRepository deviceRepository;

    public List<Register> findAll() {
        return repository.findAll();
    }

    public Optional<Register> findById(long id) {
        return repository.findById(id);
    }

    public Optional<Register> post(RegisterDto dto) {
        Register register = new Register();
        BeanUtils.copyProperties(dto, register);
        Optional<Device> device = deviceRepository.findById(dto.deviceID());
        return device
                .map(d -> {
                    register.setDate(LocalDateTime.now());
                    register.setDevice(d);
                    return repository.save(register);
                });
    }

    public Optional<Register> delete(long id) {
        Optional<Register> register = repository.findById(id);
        return register.map(r -> {
            repository.delete(r);
            return r;
        });
    }
}
