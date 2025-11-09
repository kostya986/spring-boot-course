package ru.kke.springbootcourse.model.exception;

public class BankBookWithCurrencyAlreadyHaveException extends RuntimeException {
    public BankBookWithCurrencyAlreadyHaveException() {
    }

    public BankBookWithCurrencyAlreadyHaveException(String message) {
        super(message);
    }

    public BankBookWithCurrencyAlreadyHaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankBookWithCurrencyAlreadyHaveException(Throwable cause) {
        super(cause);
    }

}
