package com.Wkrzyz.HouseHoldIncomeManager.model.dto;

import com.Wkrzyz.HouseHoldIncomeManager.enums.Role;
import com.Wkrzyz.HouseHoldIncomeManager.model.Transfer;
import com.Wkrzyz.HouseHoldIncomeManager.model.User;
import com.Wkrzyz.HouseHoldIncomeManager.model.UserGroup;

import java.util.List;
import java.util.Set;
/**
 * Data transfer Object for the User class
 * It used transfer only the necessary fields in a given action
 * */
public class UserDto {

    private Integer id;

    private String name;

    private String email;

    private String password;

    private Set<Role> roles;

    private List<Transfer> userTransfers;

    private UserGroup userGroup;


    public UserDto(){

    }
    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        //this.password = user.getPassword();
        this.roles = user.getRoles();
        this.userTransfers = user.getUserTransfers();
        this.userGroup = user.getUserGroup();
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
}
