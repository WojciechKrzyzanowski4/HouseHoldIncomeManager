package com.Wkrzyz.HouseHoldIncomeManager.controller;

import com.Wkrzyz.HouseHoldIncomeManager.enums.Role;
import com.Wkrzyz.HouseHoldIncomeManager.model.User;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.UserDto;
import com.Wkrzyz.HouseHoldIncomeManager.services.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AuthController {


    @Autowired
    UserService userService;
    // handler method to handle home page request
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();

        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle login request
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        //System.out.println(users);
        return "users";
    }
    /**
     * this function is an example how to get the data regarding the currently logged in user
     * */
    @GetMapping("/adminPage")
    public String testUser(Neo4jProperties.Authentication authentication, Model model){
        //getting the context of the currently logged in user
        SecurityContext context = SecurityContextHolder.getContext();
        //using the context to retrive the email of the currently logged in user
        System.out.println(context.getAuthentication().getName());
        System.out.println(context);

        User user = userService.findUserByEmail(context.getAuthentication().getName());
        //zaimplementowac konstruktor kopiujący
        UserDto userDto = new UserDto(user);
        Set<Role> roles = userDto.getRoles();
        for(Role r : roles){
            //we retrive a set from the database according to the found email
            System.out.println(r);
            //to do
            //add this data to a return model for the controller
        }
        return "_core";
    }

    @PostMapping("/register/save")
    public String registration( @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        Set<Role> roles = new HashSet<>();
        roles.add(Role.ADMIN);
        userDto.setRoles(roles);

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }
}