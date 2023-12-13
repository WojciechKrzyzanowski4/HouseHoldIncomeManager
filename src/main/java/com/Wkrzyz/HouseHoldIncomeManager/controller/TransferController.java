package com.Wkrzyz.HouseHoldIncomeManager.controller;

import com.Wkrzyz.HouseHoldIncomeManager.enums.Role;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class TransferController {


    @Autowired
    private UserService userService;

    @Autowired
    private TransferService transferService;

    /** handler method to handle adding a new transfer
     *
     * @return addtranfer page
     */
    @GetMapping("/addtransfer")
    public String addTransfer(Model model, @RequestParam String id){

        model.addAttribute("currId", id);
        TransferDto transfer = new TransferDto();
        transfer.setValue(0);
        model.addAttribute("transfer", transfer);
        return "addtransfer";
    }

    /**
     * handler method to handle showing the transfers owned by the currently logged-in user
     * @param model
     * @return transfers page
     */
    @GetMapping("/transfers")
    public String findUserTransfers(Model model){
        //getting the context of the currently logged-in user
        SecurityContext context = SecurityContextHolder.getContext();
        //using the context to retrive the email of the currently logged-in user
        UserDto userDto = userService.findUserDtoByEmail(context.getAuthentication().getName());
        model.addAttribute("user", userDto);
        //List<Transfer> transfers = user.getUserTransfers();
        List<TransferDto> transfers = transferService.findAllByOwner(userDto.getId());
        model.addAttribute("transfers", transfers);
        return "transfers";
    }

    /**
     * handler method to handle showing all the transfers in the database
     * @param model
     * @return
     */
    @GetMapping("/transfers/all")
    public String findTransfers(Model model){
        List<TransferDto> transfers = transferService.findAll();
        model.addAttribute("transfers", transfers);
        return "transfers";
    }

    /**
     * handler method for saving a new transfer in the database
     * @param transferDto
     * @param result
     * @param model
     * @return addtransfer page
     */

    @PostMapping("/addtransfer/save")
    public String saveTransfer(@ModelAttribute("transfer") TransferDto transferDto, BindingResult result, Model model,  @RequestParam String id){

        System.out.println(transferDto.getValue());
        System.out.println(transferDto.getIsRecurring());
        System.out.println(transferDto.getCategory());

        //checking if the user provided all the necessary credentials
        if(transferDto.getValue() == 0.0 || transferDto.getCategory() == null) {
            result.rejectValue("value", null,
                    "default error message");
        }
        //checking if the result of the action has errors
        if(result.hasErrors()){
            model.addAttribute("transfer", transferDto);
            return "redirect:/addtransfer?failure&id=" + id;
        }

        try{

            Integer uId = Integer.parseInt(id);
            User user = userService.findUserById(uId);
            //setting the current user as the owner of the transfer
            transferDto.setUser(user);
            //saving the transfer in the database
            transferService.saveTransfer(transferDto);

            return "redirect:/addtransfer?success&id=" + id;

        } catch(NumberFormatException e) {

            model.addAttribute("transfer", transferDto);
            return "redirect:/addtransfer?failure&id=" + id;

        }
    }
}
