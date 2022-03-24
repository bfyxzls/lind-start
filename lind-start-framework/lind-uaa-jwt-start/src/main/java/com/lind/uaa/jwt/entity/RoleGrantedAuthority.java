package com.lind.uaa.jwt.entity;

import org.springframework.security.core.GrantedAuthority;

public class RoleGrantedAuthority implements GrantedAuthority {
  private String name;
  private String id;
  private Integer buttonGrant;

  public RoleGrantedAuthority(String name, String id, Integer buttonGrant) {
    this.name = name;
    this.id = id;
    this.buttonGrant = buttonGrant;
  }

  public Integer getButtonGrant() {
    return buttonGrant;
  }

  public void setButtonGrant(Integer buttonGrant) {
    this.buttonGrant = buttonGrant;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String getAuthority() {
    return name;
  }
}
