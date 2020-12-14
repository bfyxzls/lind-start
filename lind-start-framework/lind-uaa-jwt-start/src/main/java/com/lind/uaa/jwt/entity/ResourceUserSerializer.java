package com.lind.uaa.jwt.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.util.CollectionUtils;

import java.io.IOException;

public class ResourceUserSerializer extends JsonSerializer<ResourceUser> {
    @Override
    public void serialize(ResourceUser resourceUser, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (resourceUser != null) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("id", resourceUser.getId());
            jsonGenerator.writeStringField("username", resourceUser.getUsername());
            jsonGenerator.writeStringField("email", resourceUser.getEmail());
            jsonGenerator.writeStringField("password", resourceUser.getPassword());
            if (!CollectionUtils.isEmpty(resourceUser.getResourceRoles())) {
                jsonGenerator.writeArrayFieldStart("authorities");
                for (ResourceRole role : resourceUser.getResourceRoles()) {
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("id", role.getId());
                    jsonGenerator.writeStringField("name", role.getName());
                    jsonGenerator.writeEndObject();
                }
                jsonGenerator.writeEndArray();
            }
            jsonGenerator.writeEndObject();
        }
    }
}
