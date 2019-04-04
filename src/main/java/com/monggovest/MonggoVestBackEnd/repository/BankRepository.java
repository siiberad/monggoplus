package com.monggovest.MonggoVestBackEnd.repository;

import com.monggovest.MonggoVestBackEnd.model.BankModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<BankModel, Long> {
}