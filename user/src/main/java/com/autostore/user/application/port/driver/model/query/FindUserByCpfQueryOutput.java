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
        this.cpf = getMaskedFromFormattedCpf(user.getCpf().formatted());
        this.email = getMaskedEmail(user.getEmail().value());
    }

    private String getMaskedFromFormattedCpf(String cpf) {
        return "***.***.***-" + cpf.substring(12);
    }

    private String getMaskedEmail(String email) {
        int atIndex = email.indexOf("@");
        if (atIndex <= 1) return "***" + email.substring(atIndex);
        String localPart = email.substring(0, atIndex);
        String domainPart = email.substring(atIndex);
        String maskedLocalPart = localPart.charAt(0) + "***" + localPart.charAt(localPart.length() - 1);
        return maskedLocalPart + domainPart;
    }

}
