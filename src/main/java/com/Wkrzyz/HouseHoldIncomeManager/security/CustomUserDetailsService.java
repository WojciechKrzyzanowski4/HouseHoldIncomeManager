package com.Wkrzyz.HouseHoldIncomeManager.security;

import com.Wkrzyz.HouseHoldIncomeManager.model.User;
import com.Wkrzyz.HouseHoldIncomeManager.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service class that implements the userDetailsService methods
 * @methods:
 * loadUserByUsername - the email is the field used for searching because it is the field that cannot be duplicated between users
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        System.out.println(email);
        if (user != null) {

            //old code for granting the user privileges
            //List<GrantedAuthority> privileges = new LinkedList<>();
            //privileges.add(new SimpleGrantedAuthority("USER"));
            //return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    //user.getPassword(),
                    //privileges);

            //checking the user role
            for(String i : user.getRoles().stream().map(Enum::name).toArray(String[]::new)){
                System.out.println(i);
            }


            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRoles().stream().map(Enum::name).toArray(String[]::new))
                    .build();

        }else{
            throw new UsernameNotFoundException("Invalid username or password.");

        }
    }
}