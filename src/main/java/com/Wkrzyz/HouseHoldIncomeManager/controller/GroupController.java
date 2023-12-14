package com.Wkrzyz.HouseHoldIncomeManager.controller;

import com.Wkrzyz.HouseHoldIncomeManager.model.dto.UserDto;
import com.Wkrzyz.HouseHoldIncomeManager.services.TransferService;
import com.Wkrzyz.HouseHoldIncomeManager.services.UserGroupService;
import com.Wkrzyz.HouseHoldIncomeManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GroupController {

    @Autowired
    UserService userService;

    @Autowired
    TransferService transferService;

    @GetMapping("/mainPage")
    public String mainPage(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "mainPage";
    }


    //this controller will handle returning the main Group page data
    //including:
    //group specific transfers list
    //
}
