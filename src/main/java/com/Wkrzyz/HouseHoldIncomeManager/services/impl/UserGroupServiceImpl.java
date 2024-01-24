package com.Wkrzyz.HouseHoldIncomeManager.services.impl;

import com.Wkrzyz.HouseHoldIncomeManager.model.UserGroup;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.UserGroupDto;
import com.Wkrzyz.HouseHoldIncomeManager.repository.UserGroupRepository;
import com.Wkrzyz.HouseHoldIncomeManager.services.UserGroupService;
import org.springframework.stereotype.Service;

@Service
public class UserGroupServiceImpl implements UserGroupService {
    

    private UserGroupRepository userGroupRepository;


    public UserGroupServiceImpl(UserGroupRepository userGroupRepository ) {
        this.userGroupRepository = userGroupRepository;
    }
    @Override
    public UserGroup findGroupByAdmin(String admin){
        return userGroupRepository.findByAdmin(admin);
    }

    @Override
    public UserGroup findGroupById(Integer id) {
        return userGroupRepository.findById(id).get();
    }

    @Override
    public void saveGroup(UserGroupDto userGroupDto){
        UserGroup userGroup = new UserGroup();
        userGroup.setName(userGroupDto.getName());
        userGroup.setAdmin(userGroupDto.getAdmin());
        userGroup.setUsers(userGroupDto.getUsers());
        userGroup.setUserGroupTransfers(userGroupDto.getUserGroupTransfers());
        userGroupRepository.save(userGroup);
    }
    @Override
    public void saveGroup(UserGroup userGroup){
        userGroupRepository.save(userGroup);
    }

    @Override
    public UserGroupDto findGroupDtoByAdmin(String admin) {
        UserGroup userGroup = userGroupRepository.findByAdmin(admin);
        return mapUserGroupDto(userGroup);
    }

    @Override
    public UserGroupDto findGroupDtoById(Integer id) {
        UserGroup userGroup = userGroupRepository.findById(id).get();
        return mapUserGroupDto(userGroup);
    }

    private UserGroupDto mapUserGroupDto(UserGroup userGroup){

        UserGroupDto userGroupDto = new UserGroupDto();
        userGroupDto.setAdmin(userGroup.getAdmin());
        userGroupDto.setId(userGroup.getId());
        userGroupDto.setName(userGroup.getName());
        userGroupDto.setUsers(userGroup.getUsers());
        userGroupDto.setUserGroupTransfers(userGroup.getUserGroupTransfers());
        userGroupDto.setBalance(userGroup.getBalance());

        return userGroupDto;

    }
}
