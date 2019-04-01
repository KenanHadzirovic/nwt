package com.nwt.preferences.entities;

import org.hibernate.jdbc.Work;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Preference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preferenceId;

    @Column(name = "value", nullable = false, length = 50)
    private String value;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "preferenceType", nullable = false)
    private Long preferenceType;

    public Preference()
    {
        this.preferenceId = this.userId = this.preferenceType = (long)0;
        this.value = "";
    }

    public Preference(Long preferenceId, String value, Long userId, Long preferenceType){
        this.preferenceId = preferenceId;
        this.value = value;
        this.userId = userId;
        this.preferenceType = preferenceType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(Long preferenceId) {
        this.preferenceId = preferenceId;
    }

    public Long getPreferenceType() {
        return preferenceType;
    }

    public void setPreferenceType(Long preferenceType) {
        this.preferenceType = preferenceType;
    }
}
