package com.nwt.preferences.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PreferenceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preferenceTypeId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    public PreferenceType()
    {
        this.preferenceTypeId = (long)0;
        this.name = "";
    }

    public PreferenceType(Long preferenceTypeId)
    {
        this.preferenceTypeId = preferenceTypeId;
        this.name = "";
    }

    public PreferenceType(Long preferenceTypeId, String preferenceTypeName)
    {
        this.preferenceTypeId = preferenceTypeId;
        this.name = preferenceTypeName;
    }

    public Long getPreferenceTypeId() {
        return preferenceTypeId;
    }

    public void setPreferenceTypeId(Long preferenceTypeId) {
        this.preferenceTypeId = preferenceTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
