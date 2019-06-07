package com.nwt.preferences.entities;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.jdbc.Work;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Preference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preferenceId;

    @Column(name = "value", nullable = true, length = 50)
    private String value;

    @Column(name = "boolValue", nullable = true)
    private Boolean boolValue;

    @NotNull(message = "UserId can't be null")
    @Column(name = "userId", nullable = false)
    private Long userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preference_type", referencedColumnName = "preferenceTypeId")
    private PreferenceType preferenceType;

    public Preference()
    {
        this.preferenceId = this.userId = (long)0;
        this.value = "";
        this.boolValue = false;
        this.preferenceType = new PreferenceType((long)0);
    }

    public Preference(Long preferenceId, String value, Boolean boolValue, Long userId, Long preferenceType){
        this.preferenceId = preferenceId;
        this.value = value;
        this.userId = userId;
        this.boolValue = boolValue;
        this.preferenceType = new PreferenceType(preferenceType);
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

    public PreferenceType getPreferenceType() {
        return preferenceType;
    }

    public void setPreferenceType(PreferenceType preferenceType) {
        this.preferenceType = preferenceType;
    }

    public void setPreferenceTypeById(Long preferenceTypeId) { this.preferenceType = new PreferenceType(preferenceTypeId); }

    public Boolean getBoolValue() {
        return boolValue;
    }

    public void setBoolValue(Boolean boolValue) {
        this.boolValue = boolValue;
    }
}
