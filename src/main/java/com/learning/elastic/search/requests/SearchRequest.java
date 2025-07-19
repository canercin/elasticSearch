package com.learning.elastic.search.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.learning.elastic.jackson.SearchRequestDeserializer;

@JsonDeserialize(using = SearchRequestDeserializer.class)
public interface SearchRequest {
}
