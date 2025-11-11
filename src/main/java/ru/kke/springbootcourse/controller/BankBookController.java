package ru.kke.springbootcourse.controller;

import static org.springframework.http.HttpStatus.CREATED;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.kke.springbootcourse.model.BankBookDto;
import ru.kke.springbootcourse.service.BankBookService;
import ru.kke.springbootcourse.validation.Create;
import ru.kke.springbootcourse.validation.Update;
@Validated
@Slf4j
@RestController
@RequiredArgsConstructor
class BankBookController {

    private final BankBookService service;

    @GetMapping("/bank-book/{bankBookId}")
    public ResponseEntity<BankBookDto> get(@Min(0L) @PathVariable("bankBookId") int id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping("/bank-book/by-user-id")
    public ResponseEntity<List<BankBookDto>> getAllByUserId(@CookieValue int userId, @RequestHeader Map<String, String> headers) {
        log.info("call with headers {}", headers);
        return ResponseEntity.ok(service.getByUserId(userId));
    }

    @Validated(Create.class)
    @PostMapping("/bank-book")
    public ResponseEntity<BankBookDto> create(@Valid @RequestBody BankBookDto bankBookDto) {
        return ResponseEntity.status(CREATED).body(service.create(bankBookDto));
    }

    @Validated(Update.class)
    @PutMapping("/bank-book")
    public BankBookDto update(@Valid @RequestBody BankBookDto bankBookDto) {
        return service.update(bankBookDto);
    }

    @DeleteMapping("/bank-book/{bankBookId}")
    public ResponseEntity<Void> delete(@PathVariable("bankBookId") int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}



