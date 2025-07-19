package com.learning.elastic.search.service.strategies.impl;

import com.learning.elastic.search.requests.impl.SingleValueSearchRequest;
import com.learning.elastic.search.service.strategies.SearchStrategy;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;

public class MatchAllSearchStrategy<T, SR extends SingleValueSearchRequest> implements SearchStrategy<T, SR> {

    /**
     * Match All Query, bütün dokümanları eşleştirir.
     * */

    @Override
    public List<T> search(ElasticsearchOperations operations, Class<T> entityClass, SR request) {
        String field = request.getField();
        String value = request.getValue().toString();

        Query query = NativeQuery.builder()
                .withQuery(q -> q
                        .matchAll(ma -> ma
                                .queryName(value)
                        )
                ).build();

        return getHits(query, operations, entityClass);
    }
}
