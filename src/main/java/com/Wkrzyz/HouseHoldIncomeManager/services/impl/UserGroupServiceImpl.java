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
    public UserGroup findGroupByUser(Integer id){
        return userGroupRepository.findById(id).get();
    }
    @Override
    public void saveGroup(UserGroupDto userGroupDto){

        UserGroup userGroup = new UserGroup();

        userGroup.setId(userGroupDto.getId());
        userGroup.setName(userGroupDto.getName());

        userGroupRepository.save(userGroup);

    }
}
