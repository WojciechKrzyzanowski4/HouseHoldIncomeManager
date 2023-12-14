package com.Wkrzyz.HouseHoldIncomeManager.controller;

import com.Wkrzyz.HouseHoldIncomeManager.enums.Role;
import com.Wkrzyz.HouseHoldIncomeManager.model.Transfer;
import com.Wkrzyz.HouseHoldIncomeManager.model.User;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.TransferDto;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.UserDto;
import com.Wkrzyz.HouseHoldIncomeManager.services.TransferService;
import com.Wkrzyz.HouseHoldIncomeManager.services.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
public class TransferController {


    @Autowired
    private UserService userService;

    @Autowired
    private TransferService transferService;

    /** handler method to handle adding a new transfer
     * @param model
     * @param id
     * @return addtranfer page
     */
    @GetMapping("/addtransfer")
    public String addTransfer(Model model, @RequestParam String id){
        //adding the current id as a model attribute
        model.addAttribute("currId", id);
        TransferDto transfer = new TransferDto();
        transfer.setValue(0);
        model.addAttribute("transfer", transfer);
        return "addtransfer";
    }

    /**
     * handler method to handle showing the transfers owned by the currently logged-in user
     * @param model
     * @param userId
     * @return transfers page
     */
    @GetMapping("/transfers")
    public String findUserTransfers(Model model, @RequestParam(required = false) String userId){

        System.out.println(userId);
        //adding the current id as a model attribute
        model.addAttribute("currId", userId);

        try{
            //trying to parse the id
            Integer uId = Integer.parseInt(userId);
            User user = userService.findUserById(uId);

            if(user.equals(null)){
                //if the id does not exist we display a fabricated error page
                User userTemp = new User();
                user.setName("you did something very wrong");
                model.addAttribute("user", userTemp);

            }
            else{
                //we find and display all the transfers associated with the user
                List<TransferDto> transfers = transferService.findAllByOwner(uId);
                model.addAttribute("transfers", transfers);
                model.addAttribute("user", user);

            }
        }
        catch(NumberFormatException e){
            //if the id was not provided in the appropriate format we also display a fabricated error page
            User user = new User();
            user.setName("you did something very wrong");
            model.addAttribute("user", user);

        }

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
     * @param id
     * @return addtransfer page
     */

    @PostMapping("/addtransfer/save")
    public String saveTransfer(@ModelAttribute("transfer") TransferDto transferDto, BindingResult result, Model model,  @RequestParam String id){

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
        //trying to parse the passed in ID string
        try{
            Integer uId = Integer.parseInt(id);
            User user = userService.findUserById(uId);
            //setting the found user as the owner of the transfer
            transferDto.setUser(user);
            //saving the transfer in the database
            transferService.saveTransfer(transferDto);
            //redirecting to the default successful URL
            return "redirect:/addtransfer?success&id=" + id;
        }
        catch(NumberFormatException e) {
            //redirecting to the default unsuccessful URL
            model.addAttribute("transfer", transferDto);
            return "redirect:/addtransfer?failure&id=" + id;
        }
    }
}
