package com.Wkrzyz.HouseHoldIncomeManager.services.impl;

import com.Wkrzyz.HouseHoldIncomeManager.model.User;
import com.Wkrzyz.HouseHoldIncomeManager.model.UserGroup;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.UserGroupDto;
import com.Wkrzyz.HouseHoldIncomeManager.repository.UserGroupRepository;
import com.Wkrzyz.HouseHoldIncomeManager.repository.UserRepository;
import com.Wkrzyz.HouseHoldIncomeManager.services.UserGroupService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserGroupServiceImpl implements UserGroupService {

    private UserRepository userRepository;

    private UserGroupRepository userGroupRepository;


    public UserGroupServiceImpl(UserGroupRepository userGroupRepository, UserRepository userRepository ) {
        this.userRepository = userRepository;
        this.userGroupRepository = userGroupRepository;
    }
    @Override
    public UserGroup findGroupByAdmin(String admin){
        return userGroupRepository.findByAdmin(admin);
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

        return userGroupDto;

    }
}
