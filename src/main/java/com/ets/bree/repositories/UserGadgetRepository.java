package com.ets.bree.repositories;

import com.ets.bree.models.Gadget;
import com.ets.bree.models.User;
import com.ets.bree.models.UserGadget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGadgetRepository extends JpaRepository<UserGadget, Long> {
    List<UserGadget> findByUser(User user);
    List<UserGadget> findByGadget(Gadget gadget);
}
