package com.monggovest.MonggoVestBackEnd.repository;

import com.monggovest.MonggoVestBackEnd.model.ProvinceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<ProvinceModel, Long> {
}
