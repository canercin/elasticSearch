package com.learning.elastic.service.impl;

import com.learning.elastic.entity.PersonelEntity;
import com.learning.elastic.repository.PersonelEntityRepository;
import com.learning.elastic.search.repo.PersonelEntitySearchRepository;
import com.learning.elastic.service.PersonelService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PersonelServiceImpl implements PersonelService {

    private final PersonelEntityRepository personelEntityRepository;
    private final PersonelEntitySearchRepository personelEntitySearchRepository;

    public PersonelServiceImpl(PersonelEntityRepository personelEntityRepository,
                               PersonelEntitySearchRepository personelEntitySearchRepository) {
        this.personelEntityRepository = personelEntityRepository;
        this.personelEntitySearchRepository = personelEntitySearchRepository;
    }


    @Override
    public PersonelEntity save(PersonelEntity personelEntity) {
        PersonelEntity saved = personelEntityRepository.save(personelEntity);
        personelEntitySearchRepository.save(saved);
        return saved;
    }

    @Override
    public PersonelEntity update(PersonelEntity personelEntity) {
        PersonelEntity updated = personelEntityRepository.save(personelEntity);
        personelEntitySearchRepository.save(updated);
        return updated;
    }

    @Override
    public Iterable<PersonelEntity> findAll() {
        return personelEntitySearchRepository.findAll();
    }

    @Override
    public PersonelEntity findById(UUID id) {
        return personelEntitySearchRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(UUID id) {
        personelEntityRepository.deleteById(id);
        personelEntitySearchRepository.deleteById(id);
    }
}
