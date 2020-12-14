package com.lind.uaa.jwt.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class ResourceRoleDeserializer extends JsonDeserializer<ResourceRole> {
    @Override
    public ResourceRole deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        ResourceRole defaultSourcePermission = new ResourceRole() {
            @Override
            public String getId() {
                return node.get("id").asText();
            }

            @Override
            public String getName() {
                return node.get("name").asText();
            }
        };

        return defaultSourcePermission;
    }
}
