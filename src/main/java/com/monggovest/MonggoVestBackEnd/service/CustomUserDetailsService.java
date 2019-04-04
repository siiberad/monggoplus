package com.monggovest.MonggoVestBackEnd.service;

import com.monggovest.MonggoVestBackEnd.model.UserModel;
import com.monggovest.MonggoVestBackEnd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUserEmail(userEmail);

        if (user==null) throw new UsernameNotFoundException("User not found");
        return user;
    }

    @Transactional
    public UserModel loadUserById(Long userId) throws UsernameNotFoundException{
        UserModel user = userRepository.getByUserId(userId);

        if (user==null) throw new UsernameNotFoundException("User not found");
        return user;
    }
}
