package com.lind.keycloak.entity;

import lombok.Data;

@Data
public class UserEntity {
    protected String id;
    protected String username;
    protected String firstName;
    protected Long createdTimestamp;
    protected String lastName;
    protected String email;
    protected boolean enabled;
    protected boolean emailVerified;
}
