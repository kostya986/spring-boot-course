package ru.kke.springbootcourse.model.exception;

public class BankBookNotFoundException extends RuntimeException {

    public BankBookNotFoundException() {}

    public BankBookNotFoundException(String message) {
        super(message);
    }

    public BankBookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankBookNotFoundException(Throwable cause) {
        super(cause);
    }

}
