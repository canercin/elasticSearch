package com.learning.elastic.search.service.strategies.impl;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import com.learning.elastic.search.requests.impl.BoolQuerySearchRequest;
import com.learning.elastic.search.service.strategies.SearchStrategy;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.util.List;

public class BoolQueryStrategy<T, SR extends BoolQuerySearchRequest> implements SearchStrategy<T, SR> {


    @Override
    public List<T> search(ElasticsearchOperations operations, Class<T> entityClass, SR request) {
        List<Query> mustQueries = request.getQueriesByType("must");
        List<Query> shouldQueries = request.getQueriesByType("should");
        List<Query> mustNotQueries = request.getQueriesByType("must_not");
        List<Query> filterQueries = request.getQueriesByType("filter");

        BoolQuery.Builder boolQueryBuilder = new BoolQuery.Builder();

        if (!mustQueries.isEmpty()) {
            boolQueryBuilder.must(mustQueries);
        }
        if (!shouldQueries.isEmpty()) {
            boolQueryBuilder.should(shouldQueries);
        }
        if (!mustNotQueries.isEmpty()) {
            boolQueryBuilder.mustNot(mustNotQueries);
        }
        if (!filterQueries.isEmpty()) {
            boolQueryBuilder.filter(filterQueries);
        }
        BoolQuery boolQuery = boolQueryBuilder.build();

        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(q -> q.bool(boolQuery))
                .build();

        return getHits(nativeQuery, operations, entityClass);
    }
}
