package com.lind.uaa.jwt.three.service;

import com.lind.uaa.jwt.service.ResourcePermissionService;
import com.lind.uaa.jwt.three.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserServiceImpl implements UserDetailsService {

  @Autowired
  ResourcePermissionService resourcePermissionService;
  @Autowired
  private PasswordEncoder passwordEncoder;


  /**
   * 2.由getAuthenticationManager().authenticate调用这个方法.
   *
   * @param username
   * @return
   * @throws UsernameNotFoundException
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    com.lind.uaa.jwt.three.entity.User user = new com.lind.uaa.jwt.three.entity.User();
    user.setId("1");
    user.setPassword(passwordEncoder.encode("123456"));
    user.setUsername("Jack");
    user.setEmail("lind@sina.com");
    user.setResourceRoles(Arrays.asList(new Role("1", "管理员", 4)));
    return user;
  }
}
