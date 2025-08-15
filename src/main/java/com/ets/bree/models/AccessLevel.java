package com.ets.bree.models;

import jakarta.persistence.*;

@Entity
@Table(name="access_levels")
public class AccessLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "access_levels", nullable = false, length = 15)
    private String accessLevel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccess_level() {
        return accessLevel;
    }

    public void setAccess_level(String access_level) {
        this.accessLevel = accessLevel;
    }
}
