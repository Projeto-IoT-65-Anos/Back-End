package com.ets.bree.services;

import com.ets.bree.dtos.UserGadgetDto;
import com.ets.bree.models.Gadget;
import com.ets.bree.models.User;
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

    public Optional<List<UserGadget>> findUserGadgetByUser(long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(u -> repository.findByUser(u));
    }

    public Optional<List<UserGadget>> findUserGadgetByGadget(long id){
        Optional<Gadget> gadget = gadgetRepository.findById(id);
        return gadget.map(g -> repository.findByGadget(g));
    }

    public Optional<UserGadget> post(UserGadgetDto dto) {
        UserGadget userGadget = new UserGadget();
        Optional<User> user = userRepository.findById(dto.userID());
        return user.flatMap(u -> {
           Optional<Gadget> gadget = gadgetRepository.findById(dto.gadgetID());
           return gadget.map(g -> {
               userGadget.setUser(u);
               userGadget.setGadget(g);
               return repository.save(userGadget);
           });
        });
    }

    public Optional<UserGadget> delete(long id) {
        Optional<UserGadget> userGadget = repository.findById(id);
        return userGadget.map(ug -> {
            repository.delete(ug);
            return ug;
        });
    }
}
