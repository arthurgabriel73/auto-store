package com.autostore.user.application.port.driven;


import com.autostore.user.domain.Cpf;
import com.autostore.user.domain.User;


public interface UserRepository {

    User save(User user);

    User findByCpf(Cpf cpf);

}
