package com.autostore.user.application.port.driver.model.query;


import com.autostore.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class FindUserByCpfQueryOutput {

    private String id;
    private String name;
    private String cpf;
    private String email;

    public FindUserByCpfQueryOutput(User user) {
        this.id = user.getId().string();
        this.name = user.getFullName().completeName();
        this.cpf = user.getCpf().value();
        this.email = user.getEmail().value();
    }

}
