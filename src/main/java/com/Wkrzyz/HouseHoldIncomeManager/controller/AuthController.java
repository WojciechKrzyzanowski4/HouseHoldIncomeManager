package com.Wkrzyz.HouseHoldIncomeManager.controller;

import com.Wkrzyz.HouseHoldIncomeManager.enums.Role;
import com.Wkrzyz.HouseHoldIncomeManager.model.User;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.TransferDto;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.UserDto;
import com.Wkrzyz.HouseHoldIncomeManager.services.TransferService;
import com.Wkrzyz.HouseHoldIncomeManager.services.UserService;
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
    /** handler method to handle home page request
     *
     * @return home page
     */
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    /**
     * handler method to handle user registration form request
     * @return register page
     * */
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create the model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    /** handler method to handle login request
     *
     * @return login page
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**handler method that returns all users in the database
     * @return users page
     * */
    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/mainPage")
    public String mainPage(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "mainPage";
    }


    /**
     * this function is an example how to get the data regarding the currently logged-in user
     * @return _core page
     * */
    @GetMapping("/adminPage")
    public String testUser(Model model){
        //getting the context of the currently logged-in user
        SecurityContext context = SecurityContextHolder.getContext();
        //using the context to retrive the email of the currently logged-in user
        UserDto userDto = userService.findUserDtoByEmail(context.getAuthentication().getName());
        Set<Role> roles = userDto.getRoles();
        for(Role r : roles){
            //we retrive a set from the database according to the found email
            System.out.println(r);
            //to do
            //add this data to a return model for the controller
        }
        return "_core";
    }
    /**
     * handler method that saves the user in the database
     * @param userDto
     * @param result
     * @param model
     * */
    @PostMapping("/register/save")
    public String registration( @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){



        System.out.println(userDto.getEmail());
        //checking if the user provided all the necessary credentials
        if(userDto.getEmail().equals("") || userDto.getPassword().equals("") || userDto.getName().equals("")){
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
            return "redirect:/register?failure";
        }
        //creating the roles set for the user
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ADMIN);
        userDto.setRoles(roles);

        //saving the user in the database
        userService.saveUser(userDto);
        return "redirect:/register?success";
    }
}