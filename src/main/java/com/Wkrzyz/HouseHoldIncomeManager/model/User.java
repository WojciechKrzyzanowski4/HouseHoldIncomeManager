package com.Wkrzyz.HouseHoldIncomeManager.model;

import com.Wkrzyz.HouseHoldIncomeManager.enums.Role;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 * Model class that represents the user table in the database
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String email;

    private String password;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Transfer> userTransfers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_group_id")
    private UserGroup userGroup;


    public User() {

    }
    public User(Integer id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(Integer id, String name, String email, String password, Set<Role> roles, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public List<Transfer> getUserTransfers() {
        return userTransfers;
    }

    public void setUserTransfers(List<Transfer> userTransfers) {
        this.userTransfers = userTransfers;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
