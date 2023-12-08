package com.Wkrzyz.HouseHoldIncomeManager.controller;

import com.Wkrzyz.HouseHoldIncomeManager.enums.Role;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.TransferDto;
import com.Wkrzyz.HouseHoldIncomeManager.services.TransferService;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import com.Wkrzyz.HouseHoldIncomeManager.model.User;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.UserDto;
import com.Wkrzyz.HouseHoldIncomeManager.repository.UserRepository;
import com.Wkrzyz.HouseHoldIncomeManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TransferService transferService;

    //some of the admin functionality will be handled by this controller
    //this means adding users to the group and so on
}
