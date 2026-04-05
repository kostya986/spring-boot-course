package ru.kke.springbootcourse.repository;

import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import ru.kke.springbootcourse.model.entity.BankBookEntity;

public interface BankBookRepository extends JpaRepository<BankBookEntity, Integer> {
    List<BankBookEntity> findByUserId(Integer userId);

    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<BankBookEntity> findWithLockById(Integer id);
}
