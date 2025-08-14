package models;

import jakarta.persistence.*;

@Entity
@Table(name="access_levels")
public class AccessLevels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 15)
    private String access_level;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccess_level() {
        return access_level;
    }

    public void setAccess_level(String access_level) {
        this.access_level = access_level;
    }
}
