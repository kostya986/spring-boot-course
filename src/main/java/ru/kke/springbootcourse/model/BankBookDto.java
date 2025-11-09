package ru.kke.springbootcourse.model;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record BankBookDto(
        int id,
        int userId,
        String number,
        BigDecimal amount,
        String currency
) {

}
