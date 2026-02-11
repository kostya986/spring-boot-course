package ru.kke.springbootcourse.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.kke.springbootcourse.model.entity.BankBookEntity;

public interface BankBookRepository extends JpaRepository<BankBookEntity, Integer> {
    List<BankBookEntity> findByUserId(Integer userId);
}
