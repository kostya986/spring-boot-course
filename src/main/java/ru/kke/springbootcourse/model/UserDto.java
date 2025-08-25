package ru.kke.springbootcourse.model;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record UserDto(
        Integer id,
        String username,
        String email) {
}
