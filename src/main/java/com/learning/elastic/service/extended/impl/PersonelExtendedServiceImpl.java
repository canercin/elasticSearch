package com.learning.elastic.service.extended.impl;

import com.learning.elastic.entity.PersonelEntity;
import com.learning.elastic.repository.PersonelEntityRepository;
import com.learning.elastic.search.repo.PersonelEntitySearchRepository;
import com.learning.elastic.search.requests.impl.*;
import com.learning.elastic.search.service.context.SearchContext;
import com.learning.elastic.search.service.enums.SearchType;
import com.learning.elastic.service.extended.PersonelExtendedService;
import com.learning.elastic.service.impl.PersonelServiceImpl;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonelExtendedServiceImpl extends PersonelServiceImpl implements PersonelExtendedService {

    public PersonelExtendedServiceImpl(PersonelEntityRepository personelEntityRepository,
                                       PersonelEntitySearchRepository personelEntitySearchRepository,
                                       SearchContext<PersonelEntity> searchContext,
                                       ElasticsearchOperations elasticsearchOperations) {
        super(personelEntityRepository, personelEntitySearchRepository, searchContext, elasticsearchOperations);
    }


    @Override
    public List<PersonelEntity> matchAll() {
        return searchContext.search(SearchType.MATCH_ALL, elasticsearchOperations, PersonelEntity.class, null);
    }

    @Override
    public List<PersonelEntity> match(SingleValueSearchRequest singleValueSearchRequest) {
        return searchContext.search(SearchType.MATCH, elasticsearchOperations, PersonelEntity.class, singleValueSearchRequest);
    }

    @Override
    public List<PersonelEntity> term(SingleValueSearchRequest singleValueSearchRequest) {
        return searchContext.search(SearchType.TERM, elasticsearchOperations, PersonelEntity.class, singleValueSearchRequest);
    }

    @Override
    public List<PersonelEntity> terms(MultipleValueSearchRequest multipleValueSearchRequest) {
        return searchContext.search(SearchType.TERMS, elasticsearchOperations, PersonelEntity.class, multipleValueSearchRequest);
    }

    @Override
    public List<PersonelEntity> multiMatch(MultiMatchSearchRequest multiMatchSearchRequest) {
        return searchContext.search(SearchType.MULTI_MATCH, elasticsearchOperations, PersonelEntity.class, multiMatchSearchRequest);
    }

    @Override
    public List<PersonelEntity> numericRange(RangeSearchRequest rangeSearchRequest) {
        return searchContext.search(SearchType.NUMERIC_RANGE, elasticsearchOperations, PersonelEntity.class, rangeSearchRequest);
    }

    @Override
    public List<PersonelEntity> dateRange(RangeSearchRequest rangeSearchRequest) {
        return searchContext.search(SearchType.DATE_RANGE, elasticsearchOperations, PersonelEntity.class, rangeSearchRequest);
    }

    @Override
    public List<PersonelEntity> boolQuery(BoolQuerySearchRequest boolQuerySearchRequest) {
        return searchContext.search(SearchType.BOOL_QUERY, elasticsearchOperations, PersonelEntity.class, boolQuerySearchRequest);
    }
}
