package com.ets.bree.services;

import com.ets.bree.models.Gadget;
import com.ets.bree.models.UserGadget;
import com.ets.bree.repositories.GadgetRepository;
import com.ets.bree.repositories.UserGadgetRepository;
import com.ets.bree.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserGadgetService {

    @Autowired
    private UserGadgetRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GadgetRepository gadgetRepository;

    public List<UserGadget> findAll() {
        return repository.findAll();
    }

    public Optional<UserGadget> findUserGadget(long id) {
        Optional<UserGadget> userGadget = repository.findById(id);
        return userGadget.map(ug -> ug);
    }
}
