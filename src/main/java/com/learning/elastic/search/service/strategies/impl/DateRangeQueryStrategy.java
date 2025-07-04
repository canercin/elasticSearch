package com.learning.elastic.search.service.strategies.impl;

import com.learning.elastic.dto.SearchRequest;
import com.learning.elastic.search.service.strategies.SearchStrategy;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;

public class DateRangeQueryStrategy<T> implements SearchStrategy<T> {
    @Override
    public List<T> search(ElasticsearchOperations operations, Class<T> entityClass, SearchRequest request) {
        String field = request.getParams().get("field").toString();
        Object fromValue = request.getParams().get("from");
        Object toValue = request.getParams().get("to");

        Query query = NativeQuery.builder()
                .withQuery(q -> q.range(
                        r -> r.date(
                                d -> d.field(field)
                                        .gte((String) fromValue)
                                        .lte((String) toValue)
                        )
                )).build();

        return getHits(query, operations, entityClass);
    }
}
