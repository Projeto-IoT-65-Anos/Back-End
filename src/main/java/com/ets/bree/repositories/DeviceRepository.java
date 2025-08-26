package com.ets.bree.repositories;

import com.ets.bree.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    boolean existsByToken(String token);
}
