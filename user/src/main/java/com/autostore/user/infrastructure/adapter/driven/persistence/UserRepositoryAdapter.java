package com.autostore.user.infrastructure.adapter.driven.persistence;


import com.autostore.user.application.port.driven.UserRepository;
import com.autostore.user.domain.Cpf;
import com.autostore.user.domain.User;
import com.autostore.user.infrastructure.adapter.driven.persistence.entity.UserDatabaseEntity;
import com.autostore.user.infrastructure.adapter.driven.persistence.repository.JpaUserRepository;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@Named
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final JpaUserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(UserDatabaseEntity.fromDomain(user)).toDomain();
    }

    @Override
    public Optional<User> findByCpf(Cpf cpf) {
        var entity = repository.findByCpf(cpf.value());
        if (entity.isEmpty()) return Optional.empty();
        return entity.map(UserDatabaseEntity::toDomain);
    }

}
