package com.autostore.user.domain;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private final FullName fullName;
    private final Cpf cpf;
    private final Email email;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final Address address;
}
