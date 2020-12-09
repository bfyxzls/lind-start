package com.lind.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class JacksonTest extends AbstractTest {
    @SneakyThrows
    @Test
    public void serializer() {
        DefaultResourceUser user = fromJson("jack.json", DefaultResourceUser.class);
        log.info("user:{}", user.getUsername());
        for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
            log.info("auth:{}", grantedAuthority.getAuthority());
        }

    }


    @JsonDeserialize(using = DefaultResourceUserSerializer.class)
    public interface DefaultResourceUser {
        String getUsername();

        String getEmail();

        Collection<? extends GrantedAuthority> getAuthorities();
    }

    public static class DefaultResourceUserSerializer extends JsonDeserializer<DefaultResourceUser> {

        @Override
        public DefaultResourceUser deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException {
            ObjectCodec oc = jsonParser.getCodec();
            JsonNode node = oc.readTree(jsonParser);
            DefaultResourceUser userAccountAuthentication = new DefaultResourceUser() {
                @Override
                public String getUsername() {
                    return node.get("username").asText();
                }

                @Override
                public String getEmail() {
                    return node.get("email").asText();
                }

                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    List<GrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
                    Iterator<JsonNode> elements = node.get("authorities").elements();
                    while (elements.hasNext()) {
                        JsonNode next = elements.next();
                        JsonNode authority = next.get("authority");
                        simpleGrantedAuthorities.add(new SimpleGrantedAuthority(authority.asText()));
                    }
                    return simpleGrantedAuthorities;
                }
            };
            return userAccountAuthentication;
        }
    }

}
