package com.learning.elastic.search.service.strategies.impl;

import co.elastic.clients.elasticsearch._types.FieldValue;
import com.learning.elastic.dto.SearchRequest;
import com.learning.elastic.search.service.strategies.SearchStrategy;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;

public class MatchQueryStrategy<T> implements SearchStrategy<T> {
    @Override
    public List<T> search(ElasticsearchOperations operations, Class<T> entityClass, SearchRequest request) {
        String field = request.getParams().get("field").toString();
        Object value = request.getParams().get("value");
        FieldValue fieldValue = FieldValue.of(value);

        Query query = NativeQuery.builder()
                .withQuery(q ->
                        q.match(m -> m.field(field).query(fieldValue))
                ).build();

        return getHits(query, operations, entityClass);
    }
}
