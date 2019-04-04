package com.monggovest.MonggoVestBackEnd.repository;

import com.monggovest.MonggoVestBackEnd.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {


    Optional<TransactionModel> findByUserModelUserIdAndTrxId (Long userId,Long trxId);
}



