package com.learning.elastic.controller;

import com.learning.elastic.entity.PersonelEntity;
import com.learning.elastic.service.extended.PersonelExtendedService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/personel")
public class PersonelController {
    private final PersonelExtendedService personelExtendedService;

    public PersonelController(PersonelExtendedService personelExtendedService) {
        this.personelExtendedService = personelExtendedService;
    }

    @PostMapping("/batch")
    public HttpStatus saveBatch(@RequestBody List<PersonelEntity> personelEntityList) {
        for (PersonelEntity personelEntity : personelEntityList) {
            personelExtendedService.save(personelEntity);
        }

        return HttpStatus.CREATED;
    }

    @PostMapping
    public PersonelEntity save(@RequestBody PersonelEntity personelEntity) {
        return personelExtendedService.save(personelEntity);
    }

    @PutMapping
    public PersonelEntity update(@RequestBody PersonelEntity personelEntity) {
        return personelExtendedService.update(personelEntity);
    }

    @GetMapping
    public Iterable<PersonelEntity> findAll() {
        return personelExtendedService.findAll();
    }

    @GetMapping("/{id}")
    public PersonelEntity findById(@PathVariable UUID id) {
        return personelExtendedService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        personelExtendedService.deleteById(id);
    }
}
