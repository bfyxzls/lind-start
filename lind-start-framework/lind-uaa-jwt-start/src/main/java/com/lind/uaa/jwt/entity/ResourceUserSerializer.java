package com.lind.uaa.jwt.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ResourceUserSerializer extends JsonSerializer<ResourceUser> {
    @Override
    public void serialize(ResourceUser resourceUser, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (resourceUser != null) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("id", resourceUser.getId());
            jsonGenerator.writeStringField("username", resourceUser.getUsername());
            jsonGenerator.writeStringField("email", resourceUser.getEmail());
            jsonGenerator.writeStringField("authorities", new ObjectMapper().writeValueAsString(resourceUser.getAuthorities()));
            jsonGenerator.writeEndObject();
        }
    }
}
