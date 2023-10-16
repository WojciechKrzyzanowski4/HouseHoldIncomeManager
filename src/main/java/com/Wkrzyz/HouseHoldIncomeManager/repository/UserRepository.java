package com.Wkrzyz.HouseHoldIncomeManager.repository;

import com.Wkrzyz.HouseHoldIncomeManager.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);



}
