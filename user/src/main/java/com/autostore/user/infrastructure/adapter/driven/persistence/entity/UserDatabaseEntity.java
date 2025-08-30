package com.autostore.user.infrastructure.adapter.driven.persistence.entity;


import com.autostore.user.domain.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserDatabaseEntity {

    @Id
    private UUID id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressDatabaseEntity address;

    public static UserDatabaseEntity fromDomain(User user) {
        return UserDatabaseEntity.builder()
                .id(user.getId().value())
                .fullName(user.getFullName().completeName())
                .cpf(user.getCpf().value())
                .email(user.getEmail().value())
                .address(AddressDatabaseEntity.fromDomain(user.getAddress()))
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    @PrePersist
    public void prePersist() {
        var now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public User toDomain() {
        return User.builder()
                .id(UserId.of(id.toString()))
                .fullName(FullName.of(fullName.split(" ")[0], fullName.split(" ")[1]))
                .cpf(Cpf.of(cpf))
                .email(Email.of(email))
                .address(address.toDomain())
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

}
