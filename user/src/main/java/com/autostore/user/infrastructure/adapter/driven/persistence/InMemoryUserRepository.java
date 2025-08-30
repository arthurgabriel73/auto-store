package com.autostore.user.infrastructure.adapter.driven.persistence;


import com.autostore.user.application.port.driven.UserRepository;
import com.autostore.user.domain.Cpf;
import com.autostore.user.domain.User;
import com.autostore.user.domain.UserId;

import java.util.ArrayList;
import java.util.List;


public class InMemoryUserRepository implements UserRepository {

    List<User> users = new ArrayList<>();

    @Override
    public User save(User user) {
        users.add(toNewInstance(user));
        return toNewInstance(user);
    }

    @Override
    public User findByCpf(Cpf cpf) {
        return users.stream()
                .filter(user -> user.getCpf().equals(cpf))
                .findFirst()
                .map(this::toNewInstance)
                .orElse(null);
    }

    private User toNewInstance(User user) {
        UserId newId = UserId.of(user.getId().string());
        return User
                .builder()
                .id(newId)
                .fullName(user.getFullName())
                .cpf(user.getCpf())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .address(user.getAddress())
                .build();
    }

}
