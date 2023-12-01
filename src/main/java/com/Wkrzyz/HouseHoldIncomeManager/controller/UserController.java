package com.Wkrzyz.HouseHoldIncomeManager.controller;

import org.springframework.ui.Model;
import com.Wkrzyz.HouseHoldIncomeManager.model.User;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.UserDto;
import com.Wkrzyz.HouseHoldIncomeManager.repository.UserRepository;
import com.Wkrzyz.HouseHoldIncomeManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email, @RequestParam String password) {

        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setPassword(password);
        userRepository.save(u);
        return "saved";

    }

    //some of the admin functionality will be handled by this controller
    //this means adding users to the group and so on
}
