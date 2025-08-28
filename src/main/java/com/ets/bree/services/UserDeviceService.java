package com.ets.bree.services;

import com.ets.bree.dtos.UserDeviceDto;
import com.ets.bree.models.Device;
import com.ets.bree.models.User;
import com.ets.bree.models.UserDevice;
import com.ets.bree.repositories.DeviceRepository;
import com.ets.bree.repositories.UserDeviceRepository;
import com.ets.bree.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDeviceService {

    @Autowired
    private UserDeviceRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    public List<UserDevice> findAll() {
        return repository.findAll();
    }

    public Optional<List<UserDevice>> findByUser(long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(u -> repository.findByUser(u));
    }

    public Optional<List<UserDevice>> findByDevice(long id) {
        Optional<Device> device = deviceRepository.findById(id);
        return device.map(d -> repository.findByDevice(d));
    }

    public Optional<UserDevice> findUserDevice(long id) {
        return repository.findById(id);
    }

    public Optional<UserDevice> post(UserDeviceDto dto) {
        UserDevice userDevice = new UserDevice();
        Optional<User> user = userRepository.findById(dto.userID());
        return user.flatMap(u -> {
                    userDevice.setUser(u);
                    Optional <Device> device = deviceRepository.findById(dto.deviceID());
                    return device.map(d -> {
                        userDevice.setDevice(d);
                        return repository.save(userDevice);
                    });
                });
    }

    public Optional<UserDevice> delete(long id) {
        Optional<UserDevice> userDevice = repository.findById(id);
        return userDevice.map(ud -> {
            repository.delete(ud);
            return ud;
            });
        };
}
