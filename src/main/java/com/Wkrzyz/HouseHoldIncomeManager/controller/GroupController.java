package com.Wkrzyz.HouseHoldIncomeManager.controller;

import com.Wkrzyz.HouseHoldIncomeManager.enums.Role;
import com.Wkrzyz.HouseHoldIncomeManager.model.User;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.TransferDto;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.UserDto;
import com.Wkrzyz.HouseHoldIncomeManager.services.TransferService;
import com.Wkrzyz.HouseHoldIncomeManager.services.UserGroupService;
import com.Wkrzyz.HouseHoldIncomeManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    @GetMapping("/adduser")
    public String addUser(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "adduser";
    }


    @PostMapping("/adduser/save")
    public String registration( @ModelAttribute("user") UserDto userDto,
                                BindingResult result,
                                Model model){

        System.out.println(userDto.getEmail());

        //userService.saveUser(userDto);
        return "redirect:/adduser?success";
    }

}
