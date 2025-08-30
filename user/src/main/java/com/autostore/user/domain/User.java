package com.autostore.user.domain;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@Builder
public class User {

    private final FullName fullName;
    private final Cpf cpf;
    private final Email email;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final Address address;
    private final UserId id;

}
