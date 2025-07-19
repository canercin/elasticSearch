package com.learning.elastic.search.requests;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import com.learning.elastic.search.requests.impl.RangeSearchRequest;
import com.learning.elastic.search.requests.impl.SingleValueSearchRequest;
import com.learning.elastic.search.service.enums.SearchType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryGenerator {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    public static  <SR extends SearchRequest> Query generateQuery(SR searchRequest) {
        if (searchRequest instanceof SingleValueSearchRequest) {

        }

        return null;
    }
}
