package com.Wkrzyz.HouseHoldIncomeManager.repository;

import com.Wkrzyz.HouseHoldIncomeManager.model.Transfer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Interface that extends CrudRepository and is used to retrieve data form the database
 * It is used in regard to the transfer data table
 */
public interface TransferRepository extends CrudRepository<Transfer, Integer> {


    //if this piece of shit works I will be very impressed
    //the piece of shit did indeed work

    List<Transfer> findByUser_id(Integer userId);


}
