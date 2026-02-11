package ru.kke.springbootcourse.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import ru.kke.springbootcourse.repository.CurrencyRepository;

@RequiredArgsConstructor
public class CurrencyValidator implements ConstraintValidator<Currency, String> {

    private final CurrencyRepository currencyRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return currencyRepository.findByName(s).isPresent();
    }
}
