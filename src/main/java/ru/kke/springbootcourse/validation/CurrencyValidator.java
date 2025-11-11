package ru.kke.springbootcourse.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Set;

public class CurrencyValidator implements ConstraintValidator<Currency, String> {

    private static final Set<String> CURRENCY_CODES = Set.of("RUB", "EUR", "USD", "GBP");

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return CURRENCY_CODES.contains(s);
    }
}
