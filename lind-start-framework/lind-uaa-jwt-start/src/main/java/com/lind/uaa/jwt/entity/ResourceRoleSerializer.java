package com.lind.uaa.jwt.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ResourceRoleSerializer extends JsonSerializer<ResourceRole> {

    @Override
    public void serialize(ResourceRole resourceRole, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        if (resourceRole != null) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("id", resourceRole.getId());
            jsonGenerator.writeStringField("name", resourceRole.getName());
            jsonGenerator.writeEndObject();
        }
    }
}
