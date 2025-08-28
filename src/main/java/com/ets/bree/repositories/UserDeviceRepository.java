package com.ets.bree.repositories;

import com.ets.bree.models.Device;
import com.ets.bree.models.Gadget;
import com.ets.bree.models.User;
import com.ets.bree.models.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDeviceRepository extends JpaRepository<UserDevice, Long> {
    List<UserDevice> findByUser(User user);
    List<UserDevice> findByDevice(Device device);
}
