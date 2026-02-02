package ru.kke.springbootcourse.model;

import lombok.Builder;

@Builder
public record AddressDto(
        String country,
        String city,
        String street,
        String home) {

}
