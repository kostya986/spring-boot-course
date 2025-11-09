package ru.kke.springbootcourse.model.exception;

public class BankBookNumberCannotBeModifiedException extends RuntimeException {
    public BankBookNumberCannotBeModifiedException(String message) {
        super(message);
    }

    public BankBookNumberCannotBeModifiedException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankBookNumberCannotBeModifiedException(Throwable cause) {
        super(cause);
    }

}
