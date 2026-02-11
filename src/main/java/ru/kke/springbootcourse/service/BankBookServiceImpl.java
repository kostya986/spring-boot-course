package ru.kke.springbootcourse.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.kke.springbootcourse.mapper.BankBookMapper;
import ru.kke.springbootcourse.model.BankBookDto;
import ru.kke.springbootcourse.model.entity.BankBookEntity;
import ru.kke.springbootcourse.model.entity.CurrencyEntity;
import ru.kke.springbootcourse.model.exception.BankBookNotFoundException;
import ru.kke.springbootcourse.model.exception.BankBookNumberCannotBeModifiedException;
import ru.kke.springbootcourse.model.exception.BankBookWithCurrencyAlreadyHaveException;
import ru.kke.springbootcourse.repository.BankBookRepository;
import ru.kke.springbootcourse.repository.CurrencyRepository;

@Validated
@Service
@RequiredArgsConstructor
public class BankBookServiceImpl implements BankBookService {
    private final BankBookRepository bankBookRepository;
    private final BankBookMapper bankBookMapper;
    private final CurrencyRepository currencyRepository;

    @Override
    public BankBookDto get(int id) {
        return bankBookRepository.findById(id)
                .map(bankBookMapper::toDto)
                .orElseThrow(() -> new BankBookNotFoundException("BankBook not found by id: " + id));
    }

    @Override
    public List<BankBookDto> getByUserId(int userId) {
        return bankBookMapper.toDtoList(bankBookRepository.findByUserId(userId));
    }

    @Transactional
    @Override
    public BankBookDto create(BankBookDto bankBookDto) {
        boolean hasBankBook = bankBookRepository.findAll().stream()
                .anyMatch(entity -> entity.getId() == bankBookDto.userId()
                && entity.getNumber().equals(bankBookDto.number())
                && entity.getCurrency().getName().equals(bankBookDto.currency()));
        if (hasBankBook) {
            throw new BankBookWithCurrencyAlreadyHaveException("BankBook with userid: " + bankBookDto.userId()
                    + " and number: " + bankBookDto.number() + " and currency: " + bankBookDto.currency() +
                    " already exists");
        }
        CurrencyEntity currency = currencyRepository.findByName(bankBookDto.currency()).orElseGet(() ->
                currencyRepository.save(new CurrencyEntity().setName(bankBookDto.currency())));
        return Optional.of(bankBookDto)
                .map(bankBookMapper::toEntity)
                .map(b -> bankBookRepository.save(b.setCurrency(currency)))
                .map(bankBookMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    @Override
    public BankBookDto update(BankBookDto bankBookDto) {
        BankBookEntity bankBook = bankBookRepository.findById(bankBookDto.id()).orElse(null);
        if (bankBook != null) {
            if (bankBook.getNumber().equals(bankBookDto.number())) {
                return bankBookMapper.toDto(bankBookRepository.save(bankBook
                        .setAmount(bankBookDto.amount())
                        .setNumber(bankBookDto.number())));
            } else {
                throw new BankBookNumberCannotBeModifiedException("BankBook number: " + bankBookDto.number() + " cannot be modified");
            }
        }
        return Optional.of(bankBookDto)
                .map(bankBookMapper::toEntity)
                .map(bankBookRepository::save)
                .map(bankBookMapper::toDto)
                .orElseThrow();
    }

    @Override
    public void delete(int id) {
        bankBookRepository.deleteById(id);
    }
}
