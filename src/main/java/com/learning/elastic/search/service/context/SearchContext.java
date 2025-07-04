package com.learning.elastic.search.service.context;

import com.learning.elastic.dto.SearchRequest;
import com.learning.elastic.search.service.strategies.SearchStrategy;
import com.learning.elastic.search.service.enums.SearchType;
import com.learning.elastic.search.service.strategies.impl.MatchQueryStrategy;
import com.learning.elastic.search.service.strategies.impl.TermSearchStrategy;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchContext<T> {
    private final Map<SearchType, SearchStrategy<T>> strategyMap = new HashMap<>();

    public SearchContext() {
        strategyMap.put(SearchType.MATCH, new MatchQueryStrategy<>());
        strategyMap.put(SearchType.TERM, new TermSearchStrategy<>());
    }
    public List<T> search(SearchType type, ElasticsearchOperations operations, Class<T> entityClass, SearchRequest request) {
        return strategyMap.get(type).search(operations, entityClass, request);
    }
}
