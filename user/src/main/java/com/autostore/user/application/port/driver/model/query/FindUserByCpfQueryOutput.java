package com.autostore.user.application.port.driver.model.query;


import com.autostore.user.domain.User;
import lombok.Getter;


@Getter
public class FindUserByCpfQueryOutput {

    private final String id;
    private final String name;

    public FindUserByCpfQueryOutput(User user) {
        this.id = user.getId().string();
        this.name = user.getFullName().completeName();
    }

}
