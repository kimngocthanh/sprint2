package com.example.kntshop.user_role.service;

import com.example.kntshop.user_role.model.UserRole;
import com.example.kntshop.user_role.repository.IUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleRepository implements IUserRoleService{
    @Autowired
    private IUserRoleRepository userRoleRepository;
    @Override
    public void createUserRole(UserRole userRole) {
        userRoleRepository.save(userRole);
    }

    @Override
    public String findRoleByUsername(String username) {
        return userRoleRepository.getRoleNameByAccountName(username);
    }
}
