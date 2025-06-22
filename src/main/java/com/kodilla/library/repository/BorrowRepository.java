package com.kodilla.library.repository;

import com.kodilla.library.domain.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    Optional<Borrow> findFirstBookByIdAndReturnDateIsNull(Long bookId);
}
