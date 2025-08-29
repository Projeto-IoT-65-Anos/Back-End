package com.ets.bree.services;

import com.ets.bree.dtos.UserDto;
import com.ets.bree.models.AccessLevel;
import com.ets.bree.models.User;
import com.ets.bree.repositories.AccessLevelRepository;
import com.ets.bree.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private AccessLevelRepository accessLevelRepository;
    @Autowired
    private PasswordEncoder encoder;

    /*private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$\n");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }*/

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> getById(long id) {
        return repository.findById(id);
    }

    public Optional<User> post(UserDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
       /* if(!validateEmail(dto.email())) {
            return Optional.empty();
        }*/
        user.setPasswordHash(encoder.encode(dto.password()));
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
            if(dto.password() != null) user.setPasswordHash(encoder.encode(dto.password()));
            if(dto.accessLevelID() != null) {
                Optional<AccessLevel> level = accessLevelRepository.findById(dto.accessLevelID());
                AccessLevel accessLevel = level.flatMap(_ -> level).orElse(oUser.get().getAccessLevel());
                user.setAccessLevel(accessLevel);
            }
            repository.save(user);
            return user;
        });
    }

    public Optional<User> patch(Long id, Map<String, Object> fields) {
        Optional<User> user = repository.findById(id);
        return user.map(u -> {
            fields.forEach((String property, Object value) -> {
                switch (property)
                        {
                            case "name" -> u.setName((String)value);
                            case "password" -> u.setPasswordHash(encoder.encode((String)value));
                            case "accessLevelID" -> {
                                Optional<AccessLevel> level = accessLevelRepository.findById(((Integer)value).longValue());
                                level.ifPresent(u::setAccessLevel);
                            }
                        }
            });
            repository.save(u);
            return u;
                });
    }

    public Optional<User> delete(Long id) {
        Optional<User> oUser = repository.findById(id);
        return oUser.map(user -> {repository.delete(user); return user;});
    }

}
