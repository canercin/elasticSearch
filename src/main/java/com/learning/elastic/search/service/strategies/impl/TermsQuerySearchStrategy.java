package com.learning.elastic.search.service.strategies.impl;

import co.elastic.clients.elasticsearch._types.FieldValue;
import com.learning.elastic.dto.SearchRequest;
import com.learning.elastic.search.service.strategies.SearchStrategy;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;

public class TermsQuerySearchStrategy<T> implements SearchStrategy<T> {

    @Override
    public List<T> search(ElasticsearchOperations operations, Class<T> entityClass, SearchRequest request) {
        String field = request.getParams().get("field").toString();
        List<Object> valueList = (List<Object>) request.getParams().get("value");
        List<FieldValue> fieldValues = valueList.stream().map(FieldValue::of).toList();

        Query query = NativeQuery.builder()
                .withQuery(q ->
                        q.terms(ts -> ts
                                .field(field)
                                .terms(terms -> terms
                                        .value(fieldValues)
                                )
                        )
                )
                .build();

        return getHits(query, operations, entityClass);
    }
}
