package com.monggovest.MonggoVestBackEnd.repository;

import com.monggovest.MonggoVestBackEnd.model.TransactionModel;
import com.monggovest.MonggoVestBackEnd.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface    UserRepository extends CrudRepository<UserModel, Long> {
    UserModel findByUserEmail(String userEmail);
    UserModel getByUserId(Long userId);
    UserModel findByConfirmationToken(String confirmationToken);


}