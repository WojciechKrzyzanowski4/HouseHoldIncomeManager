package com.Wkrzyz.HouseHoldIncomeManager.ControllerTests;

import com.Wkrzyz.HouseHoldIncomeManager.controller.GroupController;
import com.Wkrzyz.HouseHoldIncomeManager.enums.Role;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.UserDto;
import com.Wkrzyz.HouseHoldIncomeManager.model.dto.UserGroupDto;
import com.Wkrzyz.HouseHoldIncomeManager.services.UserGroupService;
import com.Wkrzyz.HouseHoldIncomeManager.services.UserService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupControllerTest {

    @Mock
    private UserService userService;
    @Mock
    private UserGroupService userGroupService;

    private MockMvc mockMvc;

    @InjectMocks
    private GroupController groupController;

    public GroupControllerTest() {
        // This constructor is used to initialize the mocks
        MockitoAnnotations.openMocks(this);

    }

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(groupController).build();
    }

    @Test
    public void assertionTest() throws Exception{
        assertEquals(1, 1);
    }
    @Test
    public void testSaveLimits() throws Exception {
        //this is the case that thymeleaf does not catch and must be handled in the controller;
        UserGroupDto mockUserGroupDto = new UserGroupDto(33, "a",-100, "ja", null, null);
        mockMvc.perform(MockMvcRequestBuilders.post("/setLimits/save")
                        .flashAttr("userGroup", mockUserGroupDto)) // Set form parameters as needed
                        .andExpect(MockMvcResultMatchers.status().is3xxRedirection()) // Assuming a redirect on success
                        .andExpect(MockMvcResultMatchers.view().name("redirect:/setLimits?failure"));
    }

    @Test
    public void testUserAdd1() throws Exception {
        //insufficient parameters
        UserDto mockUserDto = new UserDto(33, "", "", "password", null , null, null);
        mockMvc.perform(MockMvcRequestBuilders.post("/adduser/save")
                        .flashAttr("user", mockUserDto)) // Set form parameters as needed
                        .andExpect(MockMvcResultMatchers.status().is3xxRedirection()) // Assuming a redirect on success
                        .andExpect(MockMvcResultMatchers.view().name("redirect:/adduser?failure"));
    }
    @Test
    public void testUserAdd2() throws Exception {
        //email already exists
        UserDto mockUserDto = new UserDto(33, "imie", "", "password", null , null, null);
        mockMvc.perform(MockMvcRequestBuilders.post("/adduser/save")
                        .flashAttr("user", mockUserDto)) // Set form parameters as needed
                        .andExpect(MockMvcResultMatchers.status().is3xxRedirection()) // Assuming a redirect on success
                        .andExpect(MockMvcResultMatchers.view().name("redirect:/adduser?failure"));
    }

}
