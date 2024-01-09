package com.Wkrzyz.HouseHoldIncomeManager.model.dto;

import com.Wkrzyz.HouseHoldIncomeManager.enums.Category;
import com.Wkrzyz.HouseHoldIncomeManager.model.Transfer;
import com.Wkrzyz.HouseHoldIncomeManager.model.User;
import com.Wkrzyz.HouseHoldIncomeManager.model.UserGroup;

import java.sql.Date;

/**
 * Data transfer Object for the Transfer class
 * It used transfer only the necessary fields in a given action
 * */
public class TransferDto {

    private Integer id;
    private double value;
    private Category category;
    private User user;
    private UserGroup userGroup;
    private boolean isRecurring;

    private Date date;

    public TransferDto() {

    }

    public TransferDto(Transfer transfer) {
        this.id = transfer.getId();
        this.value = transfer.getValue();
        this.category = transfer.getCategory();
        this.user = transfer.getUser();
        this.isRecurring = transfer.getIsRecurring();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean getIsRecurring() {
        return isRecurring;
    }

    public void setIsRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    public UserGroup getUserGroup(){return userGroup;}

    public void setUserGroup(UserGroup userGroup){this.userGroup = userGroup;}

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
