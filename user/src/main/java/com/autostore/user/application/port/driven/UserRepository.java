package com.autostore.user.application.port.driven;


import com.autostore.user.domain.Cpf;
import com.autostore.user.domain.Email;
import com.autostore.user.domain.User;

import java.util.Optional;


public interface UserRepository {

    User save(User user);

    Optional<User> findByCpf(Cpf cpf);

    Boolean existsByCpf(Cpf cpf);

    Boolean existsByEmail(Email email);

}
