package com.Wkrzyz.HouseHoldIncomeManager.repository;

import com.Wkrzyz.HouseHoldIncomeManager.model.UserGroup;
import org.springframework.data.repository.CrudRepository;


public interface UserGroupRepository extends CrudRepository<UserGroup, Integer> {

    UserGroup findByAdmin(String admin);

}
