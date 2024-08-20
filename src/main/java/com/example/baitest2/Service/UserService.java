package com.example.baitest2.Service;

import com.example.baitest2.Entity.User;
import com.example.baitest2.Repository.UserRepository;
import com.example.baitest2.configuration.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user==null)
            throw  new UsernameNotFoundException(username);
        else
            return new CustomUserDetails(user);
    }

    public void AddUser(User user) throws Exception {
        if(userRepository.findByUsername(user.getUsername())!=null)
        {
            throw new Exception("User already in used");
        }
        else {
            userRepository.save(user);
        }
    }



}
