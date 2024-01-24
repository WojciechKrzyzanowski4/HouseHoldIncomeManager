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

    private Integer age;

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
        this.age = user.getAge();
    }

    public UserDto(Integer id, String name, String email, String password, Set<Role> roles, List<Transfer> userTransfers, UserGroup userGroup, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.userTransfers = userTransfers;
        this.userGroup = userGroup;
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
