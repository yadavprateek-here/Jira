package com.prakar.jira.dao;

import com.prakar.jira.entity.User;
import com.prakar.jira.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryCustom extends JpaRepository<UserInfo,Long> {

    Optional<UserInfo> findByEmail(String email);
}
