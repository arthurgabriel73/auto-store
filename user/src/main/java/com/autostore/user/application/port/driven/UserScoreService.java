package com.autostore.user.application.port.driven;


import com.autostore.user.domain.Cpf;


public interface UserScoreService {

    Boolean isApproved(Cpf cpf);

}
