package com.learning.elastic.search.service.strategies.impl;

import com.learning.elastic.search.requests.impl.RangeSearchRequest;
import com.learning.elastic.search.service.strategies.SearchStrategy;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;

public class NumberRangeQueryStrategy<T, SR extends RangeSearchRequest> implements SearchStrategy<T, SR> {

    /*
    * Number Range Query, belirlenen alan üzerinde sayısal değer aralığına göre arama yapar.
    * */

    @Override
    public List<T> search(ElasticsearchOperations operations, Class<T> entityClass, SR request) {
        String field = request.getField();
        String fromValue = request.getFrom().toString();
        String toValue = request.getTo().toString();

        Query query = NativeQuery.builder()
                .withQuery(q ->
                        q.range(r ->
                                r.number(n->
                                        n.field(field)
                                                .gte(Double.parseDouble(fromValue))
                                                .lte(Double.parseDouble(toValue))
                                )
                        )
                )
                .build();

        return getHits(query, operations, entityClass);
    }
}
