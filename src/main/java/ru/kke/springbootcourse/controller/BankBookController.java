package ru.kke.springbootcourse.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

@Slf4j
@RestController
@RequiredArgsConstructor
class BankBookController {

    private final BankBookService service;

    @GetMapping("/bank-book/{bankBookId}")
    public ResponseEntity<BankBookDto> get(@PathVariable("bankBookId") int id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping("/bank-book/by-user-id")
    public ResponseEntity<List<BankBookDto>> getAllByUserId(@CookieValue int userId, @RequestHeader Map<String, String> headers) {
        log.info("call with headers {}", headers);
        return ResponseEntity.ok(service.getByUserId(userId));
    }

    @PostMapping("/bank-book")
    public ResponseEntity<BankBookDto> create(@RequestBody BankBookDto bankBookDto) {
        return ResponseEntity.status(CREATED).body(service.create(bankBookDto));
    }

    @PutMapping("/bank-book")
    public BankBookDto update(@RequestBody BankBookDto bankBookDto) {
        return service.update(bankBookDto);
    }

    @DeleteMapping("/bank-book/{bankBookId}")
    public ResponseEntity<Void> delete(@PathVariable("bankBookId") int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}



