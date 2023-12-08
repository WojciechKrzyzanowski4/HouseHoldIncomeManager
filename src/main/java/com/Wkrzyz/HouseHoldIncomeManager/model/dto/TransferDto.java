package com.Wkrzyz.HouseHoldIncomeManager.model.dto;

import com.Wkrzyz.HouseHoldIncomeManager.enums.Category;
import com.Wkrzyz.HouseHoldIncomeManager.model.Transfer;
import com.Wkrzyz.HouseHoldIncomeManager.model.User;


public class TransferDto {

    private Integer id;
    private double value;
    private Category category;
    private User user;
    private boolean isRecurring;

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
}
