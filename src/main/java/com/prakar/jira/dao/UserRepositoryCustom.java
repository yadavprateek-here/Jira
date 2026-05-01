package com.prakar.jira.dao;

import com.prakar.jira.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryCustom extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
}
