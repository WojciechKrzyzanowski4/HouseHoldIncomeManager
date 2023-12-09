package com.Wkrzyz.HouseHoldIncomeManager.controller;

import com.Wkrzyz.HouseHoldIncomeManager.model.Transfer;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TransferController {


    @Autowired
    private UserService userService;

    @Autowired
    private TransferService transferService;

    @GetMapping("/transfers")
    public String findUserTransfers(Neo4jProperties.Authentication authentication, Model model){
        //getting the context of the currently logged-in user
        SecurityContext context = SecurityContextHolder.getContext();
        //using the context to retrive the email of the currently logged-in user
        User user = userService.findUserByEmail(context.getAuthentication().getName());
        model.addAttribute("user", user);
        //List<Transfer> transfers = user.getUserTransfers();
        List<TransferDto> transfers = transferService.findAllByOwner(user.getId());
        model.addAttribute("transfers", transfers);
        return "transfers";
    }
    @GetMapping("/transfers/all")
    public String findTransfers(Neo4jProperties.Authentication authentication, Model model){
        //getting the context of the currently logged-in user
        SecurityContext context = SecurityContextHolder.getContext();
        User user = userService.findUserByEmail(context.getAuthentication().getName());
        model.addAttribute("user", user);
        List<TransferDto> transfers = transferService.findAll();
        model.addAttribute("transfers", transfers);
        return "transfers";
    }

    @PostMapping("/transfer/save")
    public String saveTransfer(@ModelAttribute("transfer") TransferDto transferDto, Model model){

        //handle the save method here
        //probably return the same page as the one we use to save the transfer but this reamains to be seen

        return "transfers";
    }
}
