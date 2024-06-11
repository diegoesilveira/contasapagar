package com.totvs.contasapagar.infrastructure.repository.auth;


import com.totvs.contasapagar.infrastructure.repository.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserDetails> findByLogin(String login);
}
