package com.learning.elastic.controller;

import com.learning.elastic.entity.PersonelEntity;
import com.learning.elastic.service.PersonelService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/personel")
public class PersonelController {
    private final PersonelService personelService;

    public PersonelController(PersonelService personelService) {
        this.personelService = personelService;
    }

    @PostMapping
    public PersonelEntity save(@RequestBody PersonelEntity personelEntity) {
        return personelService.save(personelEntity);
    }

    @PutMapping
    public PersonelEntity update(@RequestBody PersonelEntity personelEntity) {
        return personelService.update(personelEntity);
    }

    @GetMapping
    public Iterable<PersonelEntity> findAll() {
        return personelService.findAll();
    }

    @GetMapping("/{id}")
    public PersonelEntity findById(@PathVariable UUID id) {
        return personelService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        personelService.deleteById(id);
    }
}
