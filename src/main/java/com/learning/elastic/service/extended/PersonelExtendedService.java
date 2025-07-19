package com.learning.elastic.service.extended;

import com.learning.elastic.entity.PersonelEntity;
import com.learning.elastic.search.requests.SearchRequest;
import com.learning.elastic.search.requests.impl.*;
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

    List<PersonelEntity> boolQuery(List<SearchRequest> searchRequestList);
}
