package com.monggovest.MonggoVestBackEnd.controller;

import com.monggovest.MonggoVestBackEnd.model.BankModel;
import com.monggovest.MonggoVestBackEnd.model.ProductModel;
import com.monggovest.MonggoVestBackEnd.model.TransactionModel;
import com.monggovest.MonggoVestBackEnd.repository.BankRepository;
import com.monggovest.MonggoVestBackEnd.repository.ProductRepository;
import com.monggovest.MonggoVestBackEnd.repository.TransactionRepository;
import com.monggovest.MonggoVestBackEnd.repository.UserRepository;
import com.monggovest.MonggoVestBackEnd.service.MailService;
import com.monggovest.MonggoVestBackEnd.utils.UtilsEditAgreement;
import com.monggovest.MonggoVestBackEnd.utils.UtilsRandomNumber;
import com.monggovest.MonggoVestBackEnd.utils.UtilsDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Optional;

@RestController
public class TransactionController implements Serializable {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    UtilsRandomNumber utilsRandomNumber;

    @Autowired
    UtilsDate utilsDate;

    @Autowired
    UtilsEditAgreement utilsEditAgreement;

    @Autowired
    MailService mailService;

    @GetMapping(path = "/transactions")
    public Iterable<TransactionModel> getAll() {
        return transactionRepository.findAll();
    }

    @GetMapping(path = "/transactions/{trxId}")
    public Optional<TransactionModel> findTrx(@PathVariable("trxId") Long trxId){
        return transactionRepository.findById(trxId);
    }

    //CREATE TRANSACTION
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/users/{userId}/transactions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TransactionModel createTransaction(@PathVariable (value = "userId") Long userId,
                                              @Valid @RequestBody TransactionModel model,
                                              @RequestParam(value = "productModel") Long productModel2,
                                              @RequestParam(value = "bankModel") Long bankModel2
                                              ){
        return userRepository.findById(userId).map(transaction -> {
                    model.setUserModel(transaction);

                    //auto generate trxnumber
                    model.setTrxInvoiceNum(utilsDate.generateDateInvoice()+ utilsRandomNumber.generateTrxInvoiceNum(6));

                    //dapat data product
                    Optional<ProductModel> productModel = productRepository.findById(productModel2);
                    if (!productModel.isPresent()) {
                        throw new ResourceNotFoundException("Author with id " + productModel2 + " does not exist");
                    }
                    ProductModel productModel1 = productModel.get();
                    model.setProductModel(productModel1);

                    //dapat data bank
                    Optional<BankModel> bankModel = bankRepository.findById(bankModel2);
                    if (!bankModel.isPresent()) {
                        throw new ResourceNotFoundException("Author with id " + bankModel2 + " does not exist");
                    }
                    BankModel bankModel1 = bankModel.get();
                    model.setBankModel(bankModel1);

                    //sendemail
                    mailService.sendEmail(model);
                    return transactionRepository.save(model);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("User ID " + userId + " not found"));
    }
}