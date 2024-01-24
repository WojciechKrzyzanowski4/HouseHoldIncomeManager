package com.Wkrzyz.HouseHoldIncomeManager.controller;

import com.Wkrzyz.HouseHoldIncomeManager.enums.Role;
import com.Wkrzyz.HouseHoldIncomeManager.exception.InvalidUserIdFormatException;
import com.Wkrzyz.HouseHoldIncomeManager.model.User;
import com.Wkrzyz.HouseHoldIncomeManager.model.UserGroup;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.TransferDto;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.UserDto;
import com.Wkrzyz.HouseHoldIncomeManager.services.TransferService;
import com.Wkrzyz.HouseHoldIncomeManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class TransferController {

    @Autowired
    private UserService userService;

    @Autowired
    private TransferService transferService;


    /**
     * handler method to handle adding a new transfer
     * 
     * @param model
     * @param id
     * @return addtranfer page
     */
    @SuppressWarnings("unused")
    @GetMapping("/addtransfer")
    public String addTransfer(Model model, @RequestParam String id) {

        try {
            // trying to parse the id`
            Integer superId = Integer.parseInt(id);
            User user = userService.findUserById(superId);

        } catch (NumberFormatException | NoSuchElementException e) {

            throw new InvalidUserIdFormatException();
        }

        // adding the current id as a model attribute
        model.addAttribute("currId", id);
        TransferDto transfer = new TransferDto();
        transfer.setValue(0);
        model.addAttribute("transfer", transfer);
        return "addtransfer";
    }

    /**
     * handler method to handle showing the transfers owned by the currently
     * logged-in user
     * 
     * @param model
     * @param userId
     * @return transfers page
     */
    @GetMapping("/transfers")
    public String findUserTransfers(Model model, @RequestParam(required = false) String userId) {

        // System.out.println(userId);
        // adding the current id as a model attribute
        model.addAttribute("currId", userId);
        Integer uId = 0;
        try {
            uId = Integer.parseInt(userId);
        } catch (NumberFormatException e) {
            throw new InvalidUserIdFormatException();
        }
        try {
            UserDto userDto = userService.findUserDtoById(uId);
            List<TransferDto> transfers = transferService.findAllByOwner(uId);
            model.addAttribute("transfers", transfers);
            model.addAttribute("user", userDto);

        } catch (NoSuchElementException e) {
            throw new InvalidUserIdFormatException();
        }

        return "transfers";

    }

    /**
     * handler method to handle showing all the transfers in the database
     * 
     * @param model
     * @return
     */
    @GetMapping("/transfers/all")
    public String findTransfers(Model model) {
        List<TransferDto> transfers = transferService.findAll();
        model.addAttribute("transfers", transfers);
        return "transfers";
    }

    /**
     * handler method for saving a new transfer in the database
     * 
     * @param transferDto
     * @param result
     * @param model
     * @param id
     * @return addtransfer page
     */
    @PostMapping("/addtransfer/save")
    public String saveTransfer(@ModelAttribute("transfer") TransferDto transferDto, BindingResult result, Model model,
            @RequestParam String id) {

        // checking if the user provided all the necessary credentials
        if (transferDto.getValue() == 0.0 || transferDto.getCategory() == null) {
            result.rejectValue("value", null,
                    "default error message");
        }
        // checking if the result of the action has errors
        if (result.hasErrors()) {
            model.addAttribute("transfer", transferDto);
            return "redirect:/addtransfer?failure&id=" + id;
        }
        // trying to parse the passed in ID string
        try {

            Integer uId = Integer.parseInt(id);
            User user = userService.findUserById(uId);

            //checking if a tiny user is trying to add an income to himself
            SecurityContext context = SecurityContextHolder.getContext();
            UserDto userCheckDto = userService.findUserDtoByEmail(context.getAuthentication().getName());
            if(userCheckDto.getRoles().contains(Role.TINY) && transferDto.getValue() > 0){
                return "redirect:/addtransfer?tooTiny&id=" + id;
            }
            // setting the found user as the owner of the transfer
            
            Date date = Date.valueOf(LocalDate.now());
            transferDto.setDate(date);

            transferDto.setUser(user);
            // saving the transfer in the database
            transferService.saveTransfer(transferDto);
            // redirecting to the default successful URL
            return "redirect:/addtransfer?success&id=" + id;
        } catch (NumberFormatException e) {
            // redirecting to the default unsuccessful URL
            model.addAttribute("transfer", transferDto);
            return "redirect:/addtransfer?failure&id=" + id;
        }
    }

    @GetMapping("/addGroupTransfer")
    public String addGroupTransfer(Model model) {
        TransferDto transfer = new TransferDto();
        transfer.setValue(0);
        model.addAttribute("transfer", transfer);
        return "addGroupTransfer";
    }

    @PostMapping("/addGroupTransfer/save")
    public String saveGroupTransfer(@ModelAttribute("transfer") TransferDto transferDto, BindingResult result,
            Model model) {

        // checking if the user provided all the necessary credentials
        if (transferDto.getValue() == 0.0 || transferDto.getCategory() == null) {
            result.rejectValue("value", null,
                    "default error message");
        }
        // checking if the result of the action has errors
        if (result.hasErrors()) {
            model.addAttribute("transfer", transferDto);
            return "redirect:/addGroupTransfer?failure";
        }

        // getting the currently logged in user
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println(context.getAuthentication().getName());
        UserDto userDto = userService.findUserDtoByEmail(context.getAuthentication().getName());
        // we find the group that the transfer should be associated with

        Date date = Date.valueOf(LocalDate.now());
        transferDto.setDate(date);

        UserGroup userGroup = userDto.getUserGroup();
        transferDto.setUserGroup(userGroup);

        // saving the transfer in the database
        transferService.saveTransfer(transferDto);

        return "redirect:/addGroupTransfer?success";

    }

    @ExceptionHandler(InvalidUserIdFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidUserIdFormatException(Model model) {
        String message = "The format of the Id in the http request in not Correct";
        model.addAttribute("message", message);
        return "error"; // This points to the error.html template
    }

}
