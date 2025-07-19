package com.learning.elastic.search.service.context;

import com.learning.elastic.search.requests.SearchRequest;
import com.learning.elastic.search.service.enums.SearchType;
import com.learning.elastic.search.service.strategies.SearchStrategy;
import com.learning.elastic.search.service.strategies.impl.*;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchContext<T> {
    private final Map<SearchType, SearchStrategy<T, ? extends SearchRequest>> strategyMap = new HashMap<>();

    public SearchContext() {
        strategyMap.put(SearchType.BOOL_QUERY, new BoolQueryStrategy<>());
        strategyMap.put(SearchType.DATE_RANGE, new DateRangeQueryStrategy<>());
        strategyMap.put(SearchType.MATCH_ALL, new MatchAllSearchStrategy<>());
        strategyMap.put(SearchType.MATCH, new MatchQueryStrategy<>());
        strategyMap.put(SearchType.MULTI_MATCH, new MultiMatchSearchStrategy<>());
        strategyMap.put(SearchType.NUMERIC_RANGE, new NumberRangeQueryStrategy<>());
        strategyMap.put(SearchType.TERM, new TermSearchStrategy<>());
        strategyMap.put(SearchType.TERMS, new TermsQuerySearchStrategy<>());
    }

    public <SR extends SearchRequest> List<T> search(SearchType type, ElasticsearchOperations operations, Class<T> entityClass, SR request) {
        @SuppressWarnings("unchecked")
        final SearchStrategy<T, SR> strategy = (SearchStrategy<T, SR>) strategyMap.get(type);
        return strategy.search(operations, entityClass, request);
    }
}
