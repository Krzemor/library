package com.kodilla.library.repository;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    long countByTitleAndStatus(long titleId, BookStatus status);
    Optional<Book> findFirstBytitleIdAndStatus(Long titleId, BookStatus status);
}
