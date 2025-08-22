package com.ets.bree.repositories;

import com.ets.bree.models.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<Register, Long> {
}
