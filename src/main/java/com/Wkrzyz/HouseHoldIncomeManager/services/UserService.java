package com.Wkrzyz.HouseHoldIncomeManager.services;

import com.Wkrzyz.HouseHoldIncomeManager.model.User;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.UserDto;
import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);

    UserDto findUserDtoByEmail(String email);

    User findUserByEmail(String email);

    User findUserById(Integer id);

    List<UserDto> findAllUsers();

}
