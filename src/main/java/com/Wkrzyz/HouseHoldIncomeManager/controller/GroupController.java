package com.Wkrzyz.HouseHoldIncomeManager.controller;

import com.Wkrzyz.HouseHoldIncomeManager.enums.Role;
import com.Wkrzyz.HouseHoldIncomeManager.model.Transfer;
import com.Wkrzyz.HouseHoldIncomeManager.model.User;
import com.Wkrzyz.HouseHoldIncomeManager.model.UserGroup;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.UserDto;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.UserGroupDto;
import com.Wkrzyz.HouseHoldIncomeManager.services.UserGroupService;
import com.Wkrzyz.HouseHoldIncomeManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class GroupController {

    @Autowired
    UserService userService;



    @Autowired
    UserGroupService userGroupService;

    /**
     * handler method that displays the main page of the application
     * @param model
     * @return mainPage page
     */
    @GetMapping("/mainPage")
    public String mainPage(Model model){

        //find the currently logged-in user
        SecurityContext context = SecurityContextHolder.getContext();
        UserDto userDto = userService.findUserDtoByEmail(context.getAuthentication().getName());

        String role = "";
        model.addAttribute("currUserId", userDto.getId());
        if(userDto.getRoles().contains(Role.ADMIN)){
            role = "ROLE_ADMIN";
        }else if(userDto.getRoles().contains(Role.USER)){
            role = "ROLE_USER";
        }else{
            role = "ROLE_TINY";
        }
        model.addAttribute("userRole", role);
        //get his group
        UserGroupDto userGroupDto = userGroupService.findGroupDtoById(userDto.getUserGroup().getId());
        //the model still needs the group transfers

        List<Transfer> transfers = userGroupDto.getUserGroupTransfers();
        //get the list of users in said group
        List<User> users = userGroupDto.getUsers();

        for(User u : users ){
            transfers.addAll(u.getUserTransfers());
        }

        //for each to add the transfers to get all of them


        //add the users to a table using thymeleaf
        model.addAttribute("users", users);
        //add the transfers to a table using thymeleaf
        model.addAttribute("transfers", transfers);
        //necessary data for the group displayed using thymeleaf
        model.addAttribute("userGroup", userGroupDto);

        return "mainPage";
    }


    @GetMapping("/adduser")
    public String addUser(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "adduser";
    }

    /**
     * Adding a new non admin user to the application
     * @param userDto
     * @param result
     * @param model
     * @return adduser page
     */
    @PostMapping("/adduser/save")
    public String userAdd(@ModelAttribute("user") UserDto userDto,
                          BindingResult result,
                          Model model){


        //checking if the user provided all the necessary credentials
        if(userDto.getEmail().equals("") || userDto.getPassword().equals("") || userDto.getName().equals("") || userDto.getAge()==null){
            result.rejectValue("email", null,
                    "default error message");
        }
        //checking if the user already exists in the database
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }
        //checking if the result of the action has errors
        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "redirect:/adduser?failure";
        }

        //creating the roles set for the user
        //the privilege lever of the user reflects his age
        
        Set<Role> roles = new HashSet<>();
        if(userDto.getAge() != null && userDto.getAge()<14){
            roles.add(Role.TINY);
            userDto.setRoles(roles);
        }else{
            roles.add(Role.USER);
            userDto.setRoles(roles);
        }

        SecurityContext context = SecurityContextHolder.getContext();
        UserDto adminDto = userService.findUserDtoByEmail(context.getAuthentication().getName());
        UserGroup userGroup = userGroupService.findGroupByAdmin(adminDto.getEmail());
        userDto.setUserGroup(userGroup);
        userService.saveUser(userDto);
        //userService.saveUser(userDto);
        return "redirect:/adduser?success";
    }

    @GetMapping("/setLimits")
    public String setLimits(Model model){
        UserGroupDto userGroup = new UserGroupDto();
        userGroup.setBalance(0);
        model.addAttribute("userGroup", userGroup);
        return "setLimits";
    }

    @PostMapping("/setLimits/save")
    @PutMapping("/setLimits/save")
    public String saveLimits(@ModelAttribute("userGroup") UserGroupDto userGroupDto, BindingResult result, Model model){
        //checking if the user provided all the necessary credentials

        if(userGroupDto.getBalance() <= 0.0) {
            result.rejectValue("balance", null,
                    "default error message");
        }
        //checking if the result of the action has errors
        if(result.hasErrors()){
            model.addAttribute("userGroup", userGroupDto);
            return "redirect:/setLimits?failure";
        }

        try{

            //Modifying the userGroup in the database
            //getting the currently logged in user
            SecurityContext context = SecurityContextHolder.getContext();
            System.out.println(context.getAuthentication().getName());
            UserDto userDto = userService.findUserDtoByEmail(context.getAuthentication().getName());

            UserGroup userGroup = userDto.getUserGroup();
            userGroup.setBalance(userGroupDto.getBalance());
            userGroupService.saveGroup(userGroup);

            return "redirect:/setLimits?success";
        }
        catch(NumberFormatException e) {
            //redirecting to the default unsuccessful URL
            model.addAttribute("userGroup", userGroupDto);
            return "redirect:/addtransfer?failure";
        }
    }
}
