package com.learning.elastic.controller;

import com.learning.elastic.entity.PersonelEntity;
import com.learning.elastic.service.PersonelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/personel")
public class PersonelController {
    private final PersonelService personelService;

    public PersonelController(PersonelService personelService) {
        this.personelService = personelService;
    }

    @PostMapping("/batch")
    public HttpStatus saveBatch(@RequestBody List<PersonelEntity> personelEntityList) {
        for (PersonelEntity personelEntity : personelEntityList) {
            personelService.save(personelEntity);
        }

        return HttpStatus.CREATED;
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
