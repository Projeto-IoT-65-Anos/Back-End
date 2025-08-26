package com.ets.bree.dtos;

import java.time.LocalDateTime;

public record DeviceDto(String name, String description, String location, long statusID, long ownerID) {
}
