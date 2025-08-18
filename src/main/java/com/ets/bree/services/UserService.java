package com.ets.bree.services;

import com.ets.bree.dtos.UserDto;
import com.ets.bree.models.AccessLevel;
import com.ets.bree.models.User;
import com.ets.bree.repositories.AccessLevelRepository;
import com.ets.bree.repositories.UserRepository;
import com.ets.bree.utils.EncryptingUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private AccessLevelRepository accessLevelRepository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> post(UserDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        Optional<User> oUser;
        Optional<AccessLevel> accessLevel = accessLevelRepository.findById(dto.accessLevelID());
        oUser = accessLevel.map(level -> {
            user.setAccessLevel(level);
            user.setPasswordHash(EncryptingUtils.encryptPassword(dto.password()));
            return repository.save(user);
        });
        return oUser;
    }

}
