package com.monggovest.MonggoVestBackEnd.controller;

import com.monggovest.MonggoVestBackEnd.model.UserModel;
import com.monggovest.MonggoVestBackEnd.repository.TransactionRepository;
import com.monggovest.MonggoVestBackEnd.repository.UserRepository;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping(path = "/user" )
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    public Iterable<UserModel> getAll() {
        return repository.findAll();
    }

    @GetMapping(path = "/{userId}")
    public Optional<UserModel> find(@PathVariable("userId") Long userId) {
        return repository.findById(userId);
    }

    @PostMapping (consumes = "application/json")
    public UserModel create(@RequestBody UserModel user) {
        return repository.save(user);
    }

    @PutMapping(path = "/{userId}")
    public UserModel update(@PathVariable("userId") Long userId, @RequestBody UserModel user) throws BadHttpRequest {
        if (repository.existsById(userId)) {
            user.setUserId(userId);
            return repository.save(user);
        } else {
            throw new BadHttpRequest();
        }
    }


    @DeleteMapping(path = "/{userId}")
    public void delete(@PathVariable("userId") Long userId) {
        repository.deleteById(userId);
    }

}
