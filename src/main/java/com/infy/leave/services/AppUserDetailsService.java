package com.infy.leave.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.infy.leave.DAO.UserRepository;
import com.infy.leave.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chenjigaram Naveen
 */
@Service
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(s);

        if(user == null) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
        }
        
        if(user.getAccountStatus().equals("InActive")) {
        	
        		throw new UsernameNotFoundException(String.format("The username %s account is InActive ", s));
        	
        }
        
        List<GrantedAuthority> authorities = new ArrayList<>();
       
            authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
       

        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(user.getUserName(), user.getPassword(), authorities);
        
        return userDetails;
    }
}
