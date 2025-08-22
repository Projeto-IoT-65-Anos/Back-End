package com.ets.bree.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "register")
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="device_id", nullable = false)
    private Device device;

    @Column(name="date", nullable = false)
    private LocalDateTime date;

    @Column(name="temperature")
    private float temperature;

    @Column(name="umidity")
    private float umidity;

    @Column(name="external_temperature")
    private float externalTemperature;

    @Column(name="external_umidity")
    private float externalUmidity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getUmidity() {
        return umidity;
    }

    public void setUmidity(float umidity) {
        this.umidity = umidity;
    }

    public float getExternalTemperature() {
        return externalTemperature;
    }

    public void setExternalTemperature(float externalTemperature) {
        this.externalTemperature = externalTemperature;
    }

    public float getExternalUmidity() {
        return externalUmidity;
    }

    public void setExternalUmidity(float externalUmidity) {
        this.externalUmidity = externalUmidity;
    }
}
