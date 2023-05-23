package com.lind.auth.model;

import lombok.Data;

/**
 * @author lind
 * @date 2023/5/22 17:59
 * @since 1.0.0
 */
@Data
public class User {
    private String id;
    private String userName;
    private String password;
    private String role;
}
