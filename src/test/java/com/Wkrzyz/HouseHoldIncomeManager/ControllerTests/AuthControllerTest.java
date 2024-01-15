package com.Wkrzyz.HouseHoldIncomeManager.ControllerTests;

import com.Wkrzyz.HouseHoldIncomeManager.controller.AuthController;
import com.Wkrzyz.HouseHoldIncomeManager.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    private MockMvc mockMvc;

    public AuthControllerTest(){
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    public void assertionTest() throws Exception{
        assertEquals(1, 1);
    }

}


