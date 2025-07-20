package com.learning.elastic.service.extended;

import com.fastsearch.fast_search.api.requests.impl.MultiMatchSearchRequest;
import com.fastsearch.fast_search.api.requests.impl.MultipleValueSearchRequest;
import com.fastsearch.fast_search.api.requests.impl.RangeSearchRequest;
import com.fastsearch.fast_search.api.requests.impl.SingleValueSearchRequest;
import com.learning.elastic.entity.PersonelEntity;
import com.learning.elastic.service.PersonelService;

import java.util.List;

public interface PersonelExtendedService extends PersonelService {
    List<PersonelEntity> matchAll();

    List<PersonelEntity> match(SingleValueSearchRequest singleValueSearchRequest);

    List<PersonelEntity> term(SingleValueSearchRequest singleValueSearchRequest);

    List<PersonelEntity> terms(MultipleValueSearchRequest multipleValueSearchRequest);

    List<PersonelEntity> multiMatch(MultiMatchSearchRequest multiMatchSearchRequest);

    List<PersonelEntity> numericRange(RangeSearchRequest rangeSearchRequest);

    List<PersonelEntity> dateRange(RangeSearchRequest rangeSearchRequest);

    List<PersonelEntity> boolQuery();
}
