package com.ets.bree.services;

import com.ets.bree.data.UserDetailData;
import com.ets.bree.models.User;
import com.ets.bree.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(username);
        return user.map( _ -> new UserDetailData(user))
                .orElseThrow(() -> new UsernameNotFoundException("User [" + username + "] not found."));
    }
}
