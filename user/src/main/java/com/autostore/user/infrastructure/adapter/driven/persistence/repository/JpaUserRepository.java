package com.autostore.user.infrastructure.adapter.driven.persistence.repository;


import com.autostore.user.infrastructure.adapter.driven.persistence.entity.UserDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface JpaUserRepository extends JpaRepository<UserDatabaseEntity, UUID> {

    Optional<UserDatabaseEntity> findByCpf(String value);

    Boolean existsByCpf(String value);

    Boolean existsByEmail(String value);

}
