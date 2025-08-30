package com.autostore.user.infrastructure.adapter.driven.persistence.entity;


import com.autostore.user.domain.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "addresses")
public class AddressDatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "complement")
    private String complement;

    @Column(name = "country", nullable = false)
    private String country;

    public static AddressDatabaseEntity fromDomain(Address address) {
        return AddressDatabaseEntity.builder()
                .id(address.getId())
                .street(address.getStreet())
                .number(address.getNumber())
                .neighborhood(address.getNeighborhood())
                .city(address.getCity())
                .state(address.getState())
                .zipCode(address.getZipCode())
                .complement(address.getComplement())
                .country(address.getCountry())
                .build();
    }

    public Address toDomain() {
        return Address.of(
                this.id,
                this.street,
                this.number,
                this.neighborhood,
                this.city,
                this.state,
                this.zipCode,
                this.complement,
                this.country
        );
    }

}
