package com.Wkrzyz.HouseHoldIncomeManager.model.dto;

import com.Wkrzyz.HouseHoldIncomeManager.model.Transfer;
import com.Wkrzyz.HouseHoldIncomeManager.model.User;

import java.util.ArrayList;
import java.util.List;


public class UserGroupDto {

    private Integer id;

    private String name;

    private double balance;

    private String admin;

    private List<User> users = new ArrayList<>();


    private List<Transfer> userGroupTransfers = new ArrayList<>();


    public UserGroupDto(){

    }
    public UserGroupDto(Integer id, String name, double balance, String admin, List<User> users, List<Transfer> userGroupTransfers) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.admin = admin;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}
