package com.monggovest.MonggoVestBackEnd.controller;

import com.monggovest.MonggoVestBackEnd.model.ProvinceModel;
import com.monggovest.MonggoVestBackEnd.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/province")
public class ProvinceController {

    @Autowired
    private ProvinceRepository repository;

    @GetMapping
    public List<ProvinceModel> getAll() {
        return repository.findAll();
    }

    @GetMapping(path = "/(provinceId}")
    public Optional<ProvinceModel> find(@PathVariable("provinceId") Long provinceId) {
        return repository.findById(provinceId);
    }

    @PostMapping(consumes = "application/json")
    public ProvinceModel create(@RequestBody ProvinceModel model) {
        return repository.save(model);
    }
    @DeleteMapping(path = "/{provinceId}")
    public void delete(@PathVariable("provinceId") Long provinceId) {
        repository.deleteById(provinceId);
    }
}
