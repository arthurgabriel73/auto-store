package com.autostore.user.infrastructure.adapter.driven.api;


import com.autostore.user.application.port.driven.UserScoreService;
import com.autostore.user.domain.Cpf;
import lombok.Setter;


@Setter
public class FakeUserScoreService implements UserScoreService {

    private Boolean approved;

    @Override
    public Boolean isApproved(Cpf cpf) {
        return approved;
    }

}
