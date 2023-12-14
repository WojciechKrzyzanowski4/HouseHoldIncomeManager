package com.Wkrzyz.HouseHoldIncomeManager.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private Float balance;

    @OneToMany(mappedBy = "userGroup", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "userGroup", cascade = CascadeType.ALL)
    private List<Transfer> userGroupTransfers = new ArrayList<>();


    public UserGroup(){

    }
    public UserGroup(Integer id, String name, Float balance, List<User> users, List<Transfer> userGroupTransfers) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.users = users;
        this.userGroupTransfers = userGroupTransfers;
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

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        balance = balance;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Transfer> getUserGroupTransfers() {
        return userGroupTransfers;
    }

    public void setUserGroupTransfers(List<Transfer> userGroupTransfers) {
        this.userGroupTransfers = userGroupTransfers;
    }
}
