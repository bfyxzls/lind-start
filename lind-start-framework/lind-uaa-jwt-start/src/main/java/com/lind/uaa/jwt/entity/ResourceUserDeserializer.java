package com.lind.uaa.jwt.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.security.core.GrantedAuthority;

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
            List<? extends ResourceRole> resourceRoles;

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
            public void setResourcePermissions(List<? extends ResourcePermission> resourcePermission) {

            }

            @Override
            public List<? extends ResourceRole> getResourceRoles() {
                return this.resourceRoles;
            }

            @Override
            public void setResourceRoles(List<? extends ResourceRole> resourceRoles) {
                this.resourceRoles = resourceRoles;
            }

            @Override
            public String getPassword() {
                return node.get("password").asText();
            }

            @Override
            public String getUsername() {
                return node.get("username").asText();
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<GrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
                if (node.get("authorities") != null) {
                    Iterator<JsonNode> elements = node.get("authorities").elements();
                    while (elements.hasNext()) {
                        JsonNode next = elements.next();
                        String id = next.get("id").asText();
                        String name = next.get("name").asText();
                        simpleGrantedAuthorities.add(new RoleGrantedAuthority(name, id));
                    }
                }
                return simpleGrantedAuthorities;
            }
        };
        return userAccountAuthentication;
    }
}