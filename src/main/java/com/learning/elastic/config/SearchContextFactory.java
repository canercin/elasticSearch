package com.learning.elastic.config;

import com.fastsearch.fast_search.api.context.SearchContext;
import org.springframework.stereotype.Component;

@Component
public class SearchContextFactory {

    public <T> SearchContext<T> create(Class<T> clazz) {
        return new SearchContext<>();
    }
}
