package com.ets.bree.services;

import com.ets.bree.dtos.UserDto;
import com.ets.bree.models.User;
import com.ets.bree.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User post(UserDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        repository.save(user);
        return user;
    }

}
