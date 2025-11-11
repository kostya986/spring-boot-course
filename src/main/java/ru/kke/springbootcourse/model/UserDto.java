package ru.kke.springbootcourse.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;
import ru.kke.springbootcourse.validation.Create;
import ru.kke.springbootcourse.validation.Update;

@Builder
@Jacksonized
public record UserDto(
        @Null(groups = Create.class)
        @NotNull(groups = Update.class)
        Integer id,
        @NotEmpty
        String username,
        @Email
        String email) {
}
