package com.learning.elastic.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.learning.elastic.search.requests.SearchRequest;
import com.learning.elastic.search.requests.impl.MultipleValueSearchRequest;
import com.learning.elastic.search.requests.impl.SingleValueSearchRequest;

import java.io.IOException;

public class SearchRequestDeserializer extends JsonDeserializer<SearchRequest> {
    @Override
    public SearchRequest deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        ObjectNode objectNode = mapper.readTree(jsonParser);

        JsonNode typeNode = objectNode.get("type");
        if (typeNode == null || typeNode.isNull()) {
            throw new IOException("Missing 'type' field in SearchRequest");
        }

        String type = typeNode.asText();

        return switch (type.toLowerCase()){
            case "single" -> mapper.treeToValue(objectNode, SingleValueSearchRequest.class);
            case "multiple" -> mapper.treeToValue(objectNode, MultipleValueSearchRequest.class);
            default -> throw new IOException("Unknown SearchRequest type: " + type);
        };
    }
}
