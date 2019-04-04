package com.monggovest.MonggoVestBackEnd.controller;

import com.monggovest.MonggoVestBackEnd.model.BankModel;
import com.monggovest.MonggoVestBackEnd.repository.BankRepository;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/banks")
public class BankController {

    @Autowired
    private BankRepository bankRepository;

    @GetMapping
    public List<BankModel> findAll() {
        return bankRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = "application/json")
    public BankModel create(@RequestBody BankModel bank) {
        return  bankRepository.save(bank);
    }

}
