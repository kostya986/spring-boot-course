package ru.kke.springbootcourse.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.kke.springbootcourse.model.entity.CurrencyEntity;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Integer> {

    Optional<CurrencyEntity> findByName(String name);
}
