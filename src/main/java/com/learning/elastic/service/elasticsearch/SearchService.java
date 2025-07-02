package com.learning.elastic.service.elasticsearch;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchService<T> {

    private final ElasticsearchOperations elasticsearchOperations;
    private final Class<T> entityClass;

    public SearchService(ElasticsearchOperations elasticsearchOperations, Class<T> entityClass) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.entityClass = entityClass;
    }

    /**
     * Searches for documents in Elasticsearch based on the provided CriteriaQuery.
     *
     * @param criteriaQuery The CriteriaQuery defining the search conditions.
     * @return A list of documents matching the search criteria.
     */
    public List<T> search(CriteriaQuery criteriaQuery) {
        return getHits(criteriaQuery);
    }

    /**
     * Searches for documents in Elasticsearch based on the provided field and value.
     *
     * @param field The field to search on.
     * @param value The value to search for.
     * @return A list of documents matching the search criteria.
     */
    public List<T> search(String field, Object value) {
        Criteria criteria = new Criteria(field).is(value);
        Query criteriaQuery = new CriteriaQuery(criteria);

        return getHits(criteriaQuery);
    }

    /**
     * Searches for documents in Elasticsearch based on the provided field and a list of values.
     *
     * @param field  The field to search on.
     * @param values The list of values to search for.
     * @return A list of documents matching the search criteria.
     */
    public List<T> search(String field, List<Object> values) {
        Criteria criteria = new Criteria(field).in(values);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);

        return getHits(criteriaQuery);
    }

    /**
     * Searches for documents in Elasticsearch based on a partial match of the field value.
     *
     * @param field The field to search on.
     * @param value The partial value to search for.
     * @return A list of documents matching the search criteria.
     */
    public List<T> searchGreaterThan(String field, Object value) {
        Criteria criteria = new Criteria(field).greaterThan(value);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);

        return getHits(criteriaQuery);
    }

    /**
     * Searches for documents in Elasticsearch where the field value is greater than or equal to the specified value.
     *
     * @param field The field to search on.
     * @param value The value to compare against.
     * @return A list of documents matching the search criteria.
     */
    public List<T> searchGreaterThanEqual(String field, Object value) {
        Criteria criteria = new Criteria(field).greaterThanEqual(value);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);

        return getHits(criteriaQuery);
    }

    /**
     * Searches for documents in Elasticsearch where the field value is less than the specified value.
     *
     * @param field The field to search on.
     * @param value The value to compare against.
     * @return A list of documents matching the search criteria.
     */
    public List<T> searchLessThan(String field, Object value) {
        Criteria criteria = new Criteria(field).lessThan(value);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);

        return getHits(criteriaQuery);
    }

    /**
     * Searches for documents in Elasticsearch where the field value is less than or equal to the specified value.
     *
     * @param field The field to search on.
     * @param value The value to compare against.
     * @return A list of documents matching the search criteria.
     */
    public List<T> searchLessThanEqual(String field, Object value) {
        Criteria criteria = new Criteria(field).lessThanEqual(value);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);

        return getHits(criteriaQuery);
    }

    /**
     * Searches for documents in Elasticsearch where the field value is between the specified range.
     *
     * @param field The field to search on.
     * @param from  The lower bound of the range.
     * @param to    The upper bound of the range.
     * @return A list of documents matching the search criteria.
     */
    public List<T> searchBetween(String field, Object from, Object to) {
        Criteria criteria = new Criteria(field).between(from, to);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);

        return getHits(criteriaQuery);
    }

    /**
     * Retrieves the search hits from the Elasticsearch index based on the provided CriteriaQuery.
     *
     * @param query The Query defining the search conditions.
     * @return A list of documents matching the search criteria.
     */
    private List<T> getHits(Query query) {
        SearchHits<T> searchHits = elasticsearchOperations.search(query, entityClass);
        return searchHits.getSearchHits()
                .stream()
                .map(SearchHit::getContent)
                .toList();
    }

    /**
     * Retrieves a single document from the Elasticsearch index based on the provided CriteriaQuery.
     *
     * @param criteriaQuery The CriteriaQuery defining the search conditions.
     * @return An Optional containing the document if found, or empty if not found.
     */
    private Optional<T> getHit(CriteriaQuery criteriaQuery) {
        SearchHit<T> searchHit = elasticsearchOperations.searchOne(criteriaQuery, entityClass);
        return Optional.ofNullable(searchHit != null ? searchHit.getContent() : null);
    }
}
