package com.ets.bree.repositories;

import com.ets.bree.models.Gadget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GadgetRepository extends JpaRepository<Gadget, Long> {
    boolean existsByToken(String token);
}
