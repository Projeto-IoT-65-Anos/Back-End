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

    public Optional<UserDevice> findUserDevice(long id) {
        return repository.findById(id);
    }

    public Optional<UserDevice> postUserDevice(UserDeviceDto dto) {
        UserDevice userDevice = new UserDevice();
        Optional<User> user = userRepository.findById(dto.userID());
        return user
                .map(u -> {
                    userDevice.setUser(u);
                    Optional <Device> device = deviceRepository.findById(dto.deviceID());
                    return device.map(d -> {
                        userDevice.setDevice(d);
                        return repository.save(userDevice);
                    });
                });
    }
}
