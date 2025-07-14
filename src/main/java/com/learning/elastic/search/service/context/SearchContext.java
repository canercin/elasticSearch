package com.learning.elastic.search.service.context;

import com.learning.elastic.search.requests.SearchRequest;
import com.learning.elastic.search.service.enums.SearchType;
import com.learning.elastic.search.service.strategies.SearchStrategy;
import com.learning.elastic.search.service.strategies.impl.DateRangeQueryStrategy;
import com.learning.elastic.search.service.strategies.impl.MatchQueryStrategy;
import com.learning.elastic.search.service.strategies.impl.NumberRangeQueryStrategy;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchContext<T> {
    private final Map<SearchType, SearchStrategy<T, ? extends SearchRequest>> strategyMap = new HashMap<>();

    public SearchContext() {
        strategyMap.put(SearchType.DATE_RANGE, new DateRangeQueryStrategy<>());
        strategyMap.put(SearchType.NUMERIC_RANGE, new NumberRangeQueryStrategy<>());
        strategyMap.put(SearchType.MATCH, new MatchQueryStrategy<>());
    }

    public <SR extends SearchRequest> List<T> search(SearchType type, ElasticsearchOperations operations, Class<T> entityClass, SR request) {
        @SuppressWarnings("unchecked")
        final SearchStrategy<T, SR> strategy = (SearchStrategy<T, SR>) strategyMap.get(type);
        return strategy.search(operations, entityClass, request);
    }
}
