package ru.kke.springbootcourse.service;

import java.util.List;
import ru.kke.springbootcourse.model.BankBookDto;

public interface BankBookService {

    BankBookDto get(int id);

    List<BankBookDto> getByUserId(int userId);

    BankBookDto create(BankBookDto bankBookDto);

    BankBookDto update(BankBookDto bankBookDto);

    void delete(int id);

}
