package com.monggovest.MonggoVestBackEnd.service;

import com.monggovest.MonggoVestBackEnd.exception.EmailAlreadyExistException;
import com.monggovest.MonggoVestBackEnd.model.UserModel;
import com.monggovest.MonggoVestBackEnd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserModel saveUser (UserModel newUser) {
        try{
            newUser.setUserEmail(newUser.getUserEmail());

            newUser.setUserPassword(bCryptPasswordEncoder.encode(newUser.getUserPassword()));

            return userRepository.save(newUser);
        }catch(Exception e){
            throw new EmailAlreadyExistException("Email '" + newUser.getUserEmail() + "' already exist");
        }
    }
}

