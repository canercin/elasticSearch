package com.learning.elastic.service.impl;

import dev.canercin.fastsearch.api.context.SearchContext;
import com.learning.elastic.config.SearchContextFactory;
import com.learning.elastic.entity.PersonelEntity;
import com.learning.elastic.repository.PersonelEntityRepository;
import com.learning.elastic.search.repo.PersonelEntitySearchRepository;
import com.learning.elastic.service.PersonelService;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("personelService")
public class PersonelServiceImpl implements PersonelService {

    protected final PersonelEntityRepository personelEntityRepository;
    protected final PersonelEntitySearchRepository personelEntitySearchRepository;
    protected final SearchContext<PersonelEntity> searchContext;
    protected final ElasticsearchOperations elasticsearchOperations;

    public PersonelServiceImpl(PersonelEntityRepository personelEntityRepository,
                               PersonelEntitySearchRepository personelEntitySearchRepository,
                               SearchContextFactory searchContextFactory,
                               ElasticsearchOperations elasticsearchOperations) {
        this.personelEntityRepository = personelEntityRepository;
        this.personelEntitySearchRepository = personelEntitySearchRepository;
        this.searchContext = searchContextFactory.create(PersonelEntity.class);
        this.elasticsearchOperations = elasticsearchOperations;
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
