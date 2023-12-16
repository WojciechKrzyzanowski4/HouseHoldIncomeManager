package com.Wkrzyz.HouseHoldIncomeManager.services.impl;


import com.Wkrzyz.HouseHoldIncomeManager.enums.Role;
import com.Wkrzyz.HouseHoldIncomeManager.model.User;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.UserDto;
import com.Wkrzyz.HouseHoldIncomeManager.repository.UserRepository;
import com.Wkrzyz.HouseHoldIncomeManager.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        // we save the user with the appropiate privileges;
        user.setRoles(userDto.getRoles());
        user.setUserGroup(userDto.getUserGroup());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UserDto findUserDtoByEmail(String email) {
        User user = userRepository.findByEmail(email);
        UserDto userDto = mapToUserDto(user);
        return userDto;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user){

        UserDto userDto = new UserDto();
        userDto.setRoles(user.getRoles());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setUserGroup(user.getUserGroup());

        return userDto;
    }
}