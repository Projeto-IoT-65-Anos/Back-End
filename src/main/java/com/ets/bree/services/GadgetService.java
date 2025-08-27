package com.ets.bree.services;

import com.ets.bree.dtos.GadgetDto;
import com.ets.bree.models.Gadget;
import com.ets.bree.models.Status;
import com.ets.bree.models.User;
import com.ets.bree.repositories.GadgetRepository;
import com.ets.bree.repositories.StatusRepository;
import com.ets.bree.repositories.UserRepository;
import com.ets.bree.utils.EncryptingUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class GadgetService {

    @Autowired
    private GadgetRepository repository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private UserRepository userRepository;

    private Random random;

    private String generateToken() {
        int length = 16;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String token;
        StringBuilder builder = new StringBuilder(length);
        do {
            for(int i = 0; i < length; i++) {
                builder.append(alphabet.charAt(random.nextInt(alphabet.length())));
            }
            String identifier = builder.toString();
            int num = random.nextInt(6969);
            token = EncryptingUtils.encrypt(String.valueOf(num)).substring(0, 16) + "-" + identifier;
        } while(repository.existsByToken(token));
        return token;
    }

    public List<Gadget> findAll() {
        return repository.findAll();
    }

    public Optional<Gadget> findGadget(long id) {
        return repository.findById(id);
    }

    public Optional<Gadget> post(GadgetDto dto) {
        Gadget gadget = new Gadget();
        BeanUtils.copyProperties(dto, gadget);
        gadget.setToken(generateToken());
        Optional<Status> status = statusRepository.findById(dto.statusID());
        return status.flatMap(s -> {
           gadget.setStatus(s);
           Optional<User> owner = userRepository.findById(dto.ownerID());
           return owner.map(o -> {
               gadget.setOwner(o);
               return repository.save(gadget);
           });
        });
    }

    public Optional<Gadget> patch(long id, Map<String, Object> fields) {
        Optional<Gadget> gadget = repository.findById(id);
        return gadget.map(g -> {
            fields.forEach((property, value) -> {
                switch (property)
                {
                    case "name" -> g.setName((String)value);
                    case "description" -> g.setDescription((String)value);
                    case "location" -> g.setLocation((String)value);
                    case "statusID" -> {
                        Optional<Status> status = statusRepository.findById((long)value);
                        status.ifPresent(g::setStatus);
                    }
                    case "ownerID" -> {
                        Optional<User> owner = userRepository.findById((long)value);
                        owner.ifPresent(g::setOwner);
                    }
                }
            });
            return repository.save(g);
        });
    }

    public Optional<Gadget> delete(long id){
        Optional<Gadget> gadget = repository.findById(id);
        return gadget.map(g -> {
            repository.delete(g);
            return g;
        });
    }
}
