package com.learning.elastic.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class SearchRequest {
    private Map<String, Object> params;
}
