package com.Wkrzyz.HouseHoldIncomeManager.repository;

import com.Wkrzyz.HouseHoldIncomeManager.enums.Category;
import com.Wkrzyz.HouseHoldIncomeManager.model.Transfer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransferRepository extends CrudRepository<Transfer, Integer> {

    //if this piece of shit works I will be very impressed
    //the piece of shit did indeed work

    List<Transfer> findByUser_id(Integer userID);

}
