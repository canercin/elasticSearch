package com.learning.elastic.search.service.strategies.impl;

import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import com.learning.elastic.dto.SearchRequest;
import com.learning.elastic.search.service.strategies.SearchStrategy;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;

public class NumberRangeQueryStrategy<T> implements SearchStrategy<T> {

    /*
    * Number Range Query, belirlenen alan üzerinde sayısal değer aralığına göre arama yapar.
    * */

    @Override
    public List<T> search(ElasticsearchOperations operations, Class<T> entityClass, SearchRequest request) {
        String field = request.getParams().get("field").toString();
        Object fromValue = request.getParams().get("from");
        Object toValue = request.getParams().get("to");

        Query query = NativeQuery.builder()
                .withQuery(q ->
                        q.range(RangeQuery.of(r ->
                                r.number(n ->
                                        n.field(field)
                                                .gte((Double) fromValue)
                                                .lte((Double) toValue)))))
                .build();

        return getHits(query, operations, entityClass);
    }
}
