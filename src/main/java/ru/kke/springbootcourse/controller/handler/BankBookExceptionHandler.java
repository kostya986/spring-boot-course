package ru.kke.springbootcourse.controller.handler;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.kke.springbootcourse.model.ErrorDto;
import ru.kke.springbootcourse.model.exception.BankBookNotFoundException;
import ru.kke.springbootcourse.model.exception.BankBookNumberCannotBeModifiedException;
import ru.kke.springbootcourse.model.exception.BankBookWithCurrencyAlreadyHaveException;

@Slf4j
@RestControllerAdvice
public class BankBookExceptionHandler {

    @ExceptionHandler(value = BankBookNotFoundException.class)
    public ResponseEntity<ErrorDto> handleBankBookNotFoundException(BankBookNotFoundException e) {
        log.error("ERROR!", e);
        ErrorDto errorDto = ErrorDto.builder()
                .code(SC_NOT_FOUND)
                .status(HttpStatus.NOT_FOUND.name())
                .message(e.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorDto);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BankBookNumberCannotBeModifiedException.class)
    public ErrorDto handleBankBookNumberCannotBeModifiedException(BankBookNumberCannotBeModifiedException e) {
        log.error("ERROR!", e);
        return ErrorDto.builder()
                .code(SC_BAD_REQUEST)
                .status(HttpStatus.BAD_REQUEST.name())
                .message(e.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BankBookWithCurrencyAlreadyHaveException.class)
    public ErrorDto handleBankBookWithCurrencyAlreadyHaveException(BankBookWithCurrencyAlreadyHaveException e) {
        log.error("ERROR!", e);
        return ErrorDto.builder()
                .code(SC_BAD_REQUEST)
                .status(HttpStatus.BAD_REQUEST.name())
                .message(e.getMessage())
                .build();
    }

}
