package com.example.kntshop.user_role.repository;

import com.example.kntshop.user_role.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserRoleRepository extends JpaRepository<UserRole,Integer> {
    @Query(value = "select r.name from  user_role ur " +
            " join role r on ur.role_id = r.id " +
            " join account_user au on au.id = ur.account_id " +
            " where au.user_name like :accountName ", nativeQuery = true)
    String getRoleNameByAccountName(String accountName);
}
