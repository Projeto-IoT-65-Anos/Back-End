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

    public Optional<User> getById(long id) {
        return repository.findById(id);
    }

    public Optional<User> post(UserDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        user.setPasswordHash(EncryptingUtils.encrypt(dto.password()));
        Optional<AccessLevel> accessLevel = accessLevelRepository.findById(dto.accessLevelID());
        return accessLevel.map(level -> {
            user.setAccessLevel(level);
            return repository.save(user);
        });
    }

    public Optional<User> put(Long id, UserDto dto) {
        Optional<User> oUser = repository.findById(id);
        return oUser.map(user -> {
            if(dto.name() != null) user.setName(dto.name());
            if(dto.password() != null) user.setPasswordHash(EncryptingUtils.encrypt(dto.password()));
            if(dto.accessLevelID() != null) {
                Optional<AccessLevel> level = accessLevelRepository.findById(dto.accessLevelID());
                AccessLevel accessLevel = level.flatMap(_ -> level).orElse(oUser.get().getAccessLevel());
                user.setAccessLevel(accessLevel);
            }
            repository.save(user);
            return user;
        });
    }

    public Optional<User> delete(Long id) {
        Optional<User> oUser = repository.findById(id);
        return oUser.map(user -> {repository.delete(user); return user;});
    }

}
