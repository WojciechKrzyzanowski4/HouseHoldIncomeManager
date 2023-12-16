package com.Wkrzyz.HouseHoldIncomeManager.services;


import com.Wkrzyz.HouseHoldIncomeManager.model.UserGroup;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.UserGroupDto;

public interface UserGroupService {

    UserGroup findGroupByAdmin(String admin);

    void saveGroup(UserGroupDto userGroupDto);

    UserGroupDto findGroupDtoByAdmin(String admin);

    UserGroupDto findGroupDtoById(Integer id);

}
