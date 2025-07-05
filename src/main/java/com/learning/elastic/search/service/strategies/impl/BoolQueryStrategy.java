package com.learning.elastic.search.service.strategies.impl;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import com.learning.elastic.dto.SearchRequest;
import com.learning.elastic.search.service.strategies.SearchStrategy;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;

public class BoolQueryStrategy<T> implements SearchStrategy<T> {

    @Override
    public List<T> search(ElasticsearchOperations operations, Class<T> entityClass, SearchRequest request) {
        List<co.elastic.clients.elasticsearch._types.query_dsl.Query> mustQueries =
                (List<co.elastic.clients.elasticsearch._types.query_dsl.Query>) request.getParams().getOrDefault("must", List.of());
        List<co.elastic.clients.elasticsearch._types.query_dsl.Query> shouldQueries =
                (List<co.elastic.clients.elasticsearch._types.query_dsl.Query>) request.getParams().getOrDefault("should", List.of());
        List<co.elastic.clients.elasticsearch._types.query_dsl.Query> mustNotQueries =
                (List<co.elastic.clients.elasticsearch._types.query_dsl.Query>) request.getParams().getOrDefault("must_not", List.of());
        List<co.elastic.clients.elasticsearch._types.query_dsl.Query> filterQueries =
                (List<co.elastic.clients.elasticsearch._types.query_dsl.Query>) request.getParams().getOrDefault("filter", List.of());

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

        Query query = NativeQuery.builder()
                .withQuery(q -> q.bool(boolQueryBuilder.build()))
                .build();

        return getHits(query, operations, entityClass);
    }
}
