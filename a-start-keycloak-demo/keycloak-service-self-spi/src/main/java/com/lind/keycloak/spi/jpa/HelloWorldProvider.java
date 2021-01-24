package com.lind.keycloak.spi.jpa;

import org.jboss.resteasy.annotations.cache.NoCache;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.UserModel;
import org.keycloak.services.resource.RealmResourceProvider;
import org.keycloak.utils.MediaType;

import javax.ws.rs.Encoded;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

public class HelloWorldProvider implements RealmResourceProvider {

    private KeycloakSession session;

    public HelloWorldProvider(KeycloakSession session) {
        this.session = session;
    }

    @Override
    public Object getResource() {
        return this;
    }

    @GET
    @Path("users")
    @NoCache
    @Produces({MediaType.APPLICATION_JSON})
    @Encoded
    public List<UserModel> getUsers() {
        List<UserModel> userModel = session.users().getUsers(session.getContext().getRealm());
        return userModel;
    }

    @GET
    @NoCache
    @Path("/name")
    @Produces({MediaType.APPLICATION_JSON})
    @Encoded
    public String get() {
        String name = session.getContext().getRealm().getDisplayName();
        if (name == null) {
            name = session.getContext().getRealm().getName();
        }
        return "Hello" + name;
    }

    @Override
    public void close() {
    }
}