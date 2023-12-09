package com.Wkrzyz.HouseHoldIncomeManager.repository;

import com.Wkrzyz.HouseHoldIncomeManager.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
/**
 * Interface that extends CrudRepository and is used to retrieve data form the database
 * It is used in regard to the user data table
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);







}
