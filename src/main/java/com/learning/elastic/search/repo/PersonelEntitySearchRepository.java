package com.learning.elastic.search.repo;

import com.learning.elastic.entity.PersonelEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonelEntitySearchRepository extends ElasticsearchRepository<PersonelEntity, UUID> {
}
