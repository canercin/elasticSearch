package com.learning.elastic.search.requests.impl;

import com.learning.elastic.search.requests.SearchRequest;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoolQuerySearchRequest implements SearchRequest {
    private Map<String, List<Query>> queries = new HashMap<>();

    public BoolQuerySearchRequest() {
        queries.put("must", List.of());
        queries.put("should", List.of());
        queries.put("must_not", List.of());
        queries.put("filter", List.of());
    }

    public Map<String, List<Query>> getQueries() {
        return queries;
    }

    public void setQueries(Map<String, List<Query>> queries) {
        this.queries = queries;
    }

    /**
    * @param type can be "must", "should", "must_not", or "filter"
    * */
    public List<Query> getQueriesByType(String type) {
        return queries.get(type);
    }
}
