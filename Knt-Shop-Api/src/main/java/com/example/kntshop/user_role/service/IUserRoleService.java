package com.example.kntshop.user_role.service;

import com.example.kntshop.roles.model.Role;
import com.example.kntshop.user_role.model.UserRole;

public interface IUserRoleService {
    void createUserRole(UserRole userRole);

    String findRoleByUsername(String username);
}
