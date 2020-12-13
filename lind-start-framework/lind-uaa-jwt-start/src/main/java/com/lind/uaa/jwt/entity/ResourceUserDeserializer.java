package com.lind.uaa.jwt.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * DefaultResourceUser反序列化工具.
 */
public class ResourceUserDeserializer extends JsonDeserializer<ResourceUser> {

    @Override
    public ResourceUser deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        ResourceUser userAccountAuthentication = new ResourceUser() {
            @Override
            public String getEmail() {
                if (node.get("email") != null) {
                    return node.get("email").asText();
                }
                return null;
            }

            @Override
            public String getId() {
                return node.get("id").asText();
            }

            @Override
            public List<? extends ResourcePermission> getResourcePermissions() {
                return null;
            }

            @Override
            public List<? extends ResourceRole> getResourceRoles() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return node.get("username").asText();
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<GrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
                Iterator<JsonNode> elements = node.get("authorities").elements();
                while (elements.hasNext()) {
                    JsonNode next = elements.next();
                    JsonNode authority = next.get("name");
                    simpleGrantedAuthorities.add(new SimpleGrantedAuthority(authority.asText()));
                }
                return simpleGrantedAuthorities;
            }
        };
        return userAccountAuthentication;
    }
}