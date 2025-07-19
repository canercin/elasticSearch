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

    public static  <SR extends SearchRequest> Query generateQuery(SearchType searchType, SR searchRequest) {
        switch (searchType) {
            case DATE_RANGE -> {
                if (searchRequest instanceof RangeSearchRequest rangeSearchRequest) {
                    return Query.of(q -> q
                            .range(r -> r.date(d -> d
                                            .field(rangeSearchRequest.getField())
                                            .gte(rangeSearchRequest.getFrom().toString())
                                            .lte(rangeSearchRequest.getTo().toString())
                                    )
                            )
                    );
                }
            }
            case MATCH -> {
                if (searchRequest instanceof SingleValueSearchRequest singleValueSearchRequest) {
                    return Query.of(q -> q
                            .match(m -> m
                                    .field(singleValueSearchRequest.getField())
                                    .query(singleValueSearchRequest.getValue().toString())
                            )
                    );
                }
            }
            case NUMERIC_RANGE -> {
                if (searchRequest instanceof RangeSearchRequest rangeSearchRequest) {
                    return Query.of(q -> q
                            .range(r -> r.number(
                                            n -> n.field(rangeSearchRequest.getField())
                                                    .gte(Double.parseDouble(rangeSearchRequest.getFrom().toString()))
                                                    .lte(Double.parseDouble(rangeSearchRequest.getTo().toString()))
                                    )
                            )
                    );
                }
            }
            case TERM -> {
                if (searchRequest instanceof SingleValueSearchRequest singleValueSearchRequest) {
                    return Query.of(q -> q
                            .term(t -> t
                                    .field(singleValueSearchRequest.getField())
                                    .value(FieldValue.of(singleValueSearchRequest.getValue()))
                            )
                    );
                }
            }
        }
        return null;
    }
}
