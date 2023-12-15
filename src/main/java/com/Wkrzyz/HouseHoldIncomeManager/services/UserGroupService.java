package com.Wkrzyz.HouseHoldIncomeManager.services;


import com.Wkrzyz.HouseHoldIncomeManager.model.UserGroup;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.UserGroupDto;

public interface UserGroupService {

    UserGroup findGroupByUser(Integer id);

    void saveGroup(UserGroupDto userGroupDto);

}
