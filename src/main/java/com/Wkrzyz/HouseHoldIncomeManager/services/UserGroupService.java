package com.Wkrzyz.HouseHoldIncomeManager.services;


import com.Wkrzyz.HouseHoldIncomeManager.model.UserGroup;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.UserGroupDto;

public interface UserGroupService {

    UserGroup findGroupByAdmin(String admin);

    UserGroup findGroupById(Integer id);

    void saveGroup(UserGroupDto userGroupDto);

    void saveGroup(UserGroup userGroup);

    UserGroupDto findGroupDtoByAdmin(String admin);

    UserGroupDto findGroupDtoById(Integer id);

}
