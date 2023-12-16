package com.Wkrzyz.HouseHoldIncomeManager.repository;

import com.Wkrzyz.HouseHoldIncomeManager.model.UserGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserGroupRepository extends CrudRepository<UserGroup, Integer> {

    UserGroup findByAdmin(String admin);

}
