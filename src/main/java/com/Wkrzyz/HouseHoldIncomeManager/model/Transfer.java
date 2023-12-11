package com.Wkrzyz.HouseHoldIncomeManager.model;

import com.Wkrzyz.HouseHoldIncomeManager.enums.Category;
import jakarta.persistence.*;

/**
 * Model class that represents the transfer table in the database
 */
@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private double value;

    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "userGroup_id")
    private UserGroup userGroup;



    private boolean isRecurring;

    public Transfer() {}

    public Transfer(Integer id, double value, Category category, User user, boolean isRecurring) {
        this.id = id;
        this.value = value;
        this.category = category;
        this.user = user;
        this.isRecurring = isRecurring;
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
    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }


}
