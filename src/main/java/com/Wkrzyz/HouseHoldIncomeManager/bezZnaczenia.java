package com.Wkrzyz.HouseHoldIncomeManager;

import com.Wkrzyz.HouseHoldIncomeManager.model.User;
import com.Wkrzyz.HouseHoldIncomeManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class bezZnaczenia implements CommandLineRunner {

    @Autowired
    public UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {

        System.out.println("yoopikayeee");

    }
}
