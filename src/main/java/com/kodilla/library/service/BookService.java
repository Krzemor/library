package com.kodilla.library.service;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookStatus;
import com.kodilla.library.domain.Title;
import com.kodilla.library.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    public final BookRepository bookRepository;
    public final TitleService titleService;

    public Book saveBook(Book book) {
        Title tile =titleService.getTitleById(book.getTitle().getId());
        book.setTitle(tile);
        return bookRepository.save(book);
    }

    public void updateStatus(Long id, BookStatus status) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setStatus(status);
    }

    public long countAvailableBooks(Long titleId) {
        return bookRepository.countByTitleAndStatus(titleId, BookStatus.AVAILABLE);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getAvailableBook(Long titleId) {
        return bookRepository.findFirstBytitleIdAndStatus(titleId, BookStatus.AVAILABLE)
                .orElseThrow(() -> new RuntimeException("No available book"));
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }
}
