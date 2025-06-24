package com.kodilla.library.repository;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookStatus;
import com.kodilla.library.domain.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    long countByTitleIdAndStatus(Long titleId, BookStatus status);
    Optional<Book> findFirstByTitleIdAndStatus(Long titleId, BookStatus status);
}
