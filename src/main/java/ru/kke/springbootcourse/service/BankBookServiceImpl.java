package ru.kke.springbootcourse.service;

import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.kke.springbootcourse.model.BankBookDto;
import ru.kke.springbootcourse.model.exception.BankBookNotFoundException;
import ru.kke.springbootcourse.model.exception.BankBookNumberCannotBeModifiedException;
import ru.kke.springbootcourse.model.exception.BankBookWithCurrencyAlreadyHaveException;

@Validated
@Service
@RequiredArgsConstructor
public class BankBookServiceImpl implements BankBookService {

    private final Map<Integer, BankBookDto> repository = new ConcurrentHashMap<>();
    private final AtomicInteger sequenceId = new AtomicInteger(1);

    @PostConstruct
    public void init() {
        repository.put(1, BankBookDto.builder()
                        .id(sequenceId.getAndIncrement())
                        .userId(1)
                        .amount(BigDecimal.TEN)
                        .currency("RUB")
                        .number("num1").build());
    }

    @Override
    public BankBookDto get(int id) {
        return Optional.ofNullable(repository.get(id))
                .orElseThrow(() -> new BankBookNotFoundException("BankBook not found by id: " + id));
    }

    @Override
    public List<BankBookDto> getByUserId(int userId) {
        return repository.values()
                .stream()
                .filter(b -> b.userId() == userId)
                .toList();
    }

    @Override
    public BankBookDto create(BankBookDto bankBookDto) {
        boolean hasBankBook = repository.values().stream()
                .anyMatch(dto -> dto.userId() == bankBookDto.userId()
                && dto.number().equals(bankBookDto.number())
                && dto.currency().equals(bankBookDto.currency()));
        if (hasBankBook) {
            throw new BankBookWithCurrencyAlreadyHaveException("BankBook with userid: " + bankBookDto.userId()
                    + " and number: " + bankBookDto.number() + " and currency: " + bankBookDto.currency() +
                    " already exists");
        }
        int id = sequenceId.getAndIncrement();
        BankBookDto bankBook = BankBookDto.builder().id(id).userId(bankBookDto.userId()).number(bankBookDto.number())
                .currency(bankBookDto.currency()).amount(bankBookDto.amount()).build();
        repository.put(id, bankBook);
        return bankBook;
    }

    @Override
    public BankBookDto update(BankBookDto bankBookDto) {
        BankBookDto bankBook = repository.get(bankBookDto.id());
        if (bankBook != null) {
            if (bankBook.number().equals(bankBookDto.number())) {
                BankBookDto newBankBook = BankBookDto.builder().id(bankBookDto.id())
                        .userId(bankBookDto.userId())
                        .amount(bankBookDto.amount())
                        .currency(bankBookDto.currency())
                        .number(bankBookDto.number()).build();
                repository.put(bankBook.id(), newBankBook);
                return newBankBook;
            } else {
                throw new BankBookNumberCannotBeModifiedException("BankBook number: " + bankBookDto.number() + " cannot be modified");
            }
        }
        int id = sequenceId.getAndIncrement();
        BankBookDto newBankBook = BankBookDto.builder().id(id)
                .userId(bankBookDto.userId())
                .amount(bankBookDto.amount())
                .currency(bankBookDto.currency())
                .number(bankBookDto.number()).build();
        repository.put(id, newBankBook);
        return newBankBook;
    }

    @Override
    public void delete(int id) {
        repository.remove(id);
    }
}
