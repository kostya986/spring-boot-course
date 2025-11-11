package ru.kke.springbootcourse.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import java.math.BigDecimal;
import lombok.Builder;
import ru.kke.springbootcourse.validation.Create;
import ru.kke.springbootcourse.validation.Currency;
import ru.kke.springbootcourse.validation.Update;

@Builder
public record BankBookDto(
        @Null(groups = Create.class)
        @NotNull(groups = Update.class)
        Integer id,
        int userId,
        @NotBlank(message = "Not blank!")
        String number,
        @Min(0L)
        BigDecimal amount,
        @Currency
        String currency
) {
}
