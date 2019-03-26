package com.nwt.preferences.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PreferenceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preferenceTypEId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    public Long getPreferenceTypEId() {
        return preferenceTypEId;
    }

    public void setPreferenceTypEId(Long preferenceTypEId) {
        this.preferenceTypEId = preferenceTypEId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
