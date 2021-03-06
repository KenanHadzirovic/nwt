package com.nwt.users.entities;

import org.hibernate.jdbc.Work;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Username is a required field.")
    @Size(min = 1, max = 20, message = "Username must be between 1 and 20 characters.")
    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @NotNull(message = "Password is a required field.")
    @Size(min = 1, max = 20, message = "Password must be between 1 and 30 characters.")
    @Column(name = "password", nullable = false, length = 30)
    private String password;

    @NotNull(message = "First Name is a required field.")
    @Size(min = 1, max = 20, message = "First Name must be between 1 and 50 characters.")
    @Column(name = "firstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "middleName", length = 50)
    @Size(max = 20, message = "Middle Name can be up to 20 characters.")
    private String middleName;

    @NotNull(message = "Last Name is a required field.")
    @Size(min = 1, max = 50, message = "First Name must be between 1 and 50 characters.")
    @Column(name = "lastName", nullable = false, length = 50)
    private String lastName;

    @Column(name = "isActive", nullable = false)
    private boolean isActive;

    @Column(name = "createdDate", nullable = false)
    private Date createdDate;

    @Column(name = "modifiedDate", nullable = true)
    private Date modifiedDate;

    @OneToMany(
            mappedBy = "owner",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Workspace> workspaces = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void addWorkspace(Workspace workspace) {
        workspaces.add(workspace);
        workspace.setOwner(this);
    }

    public void removeWorkspace(Workspace workspace) {
        workspaces.remove(workspace);
        workspace.setOwner(null);
    }
}
