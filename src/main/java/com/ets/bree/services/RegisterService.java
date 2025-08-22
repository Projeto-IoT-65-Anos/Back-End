package com.ets.bree.services;

import com.ets.bree.repositories.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository repository;
}
