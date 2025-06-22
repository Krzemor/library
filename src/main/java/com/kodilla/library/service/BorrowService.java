package com.kodilla.library.service;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookStatus;
import com.kodilla.library.domain.Borrow;
import com.kodilla.library.domain.Reader;
import com.kodilla.library.repository.BookRepository;
import com.kodilla.library.repository.BorrowRepository;
import com.kodilla.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookService bookService;
    private final ReaderRepository readerRepository;

    public void borrowBook(Long readerId, Long titleId) {
        Reader reader = readerRepository.findById(readerId).orElseThrow(() -> new RuntimeException("Reader not found"));
        Book book = bookService.getAvailableBook(titleId);

        book.setStatus(BookStatus.BORROWED);
        bookService.saveBook(book);

        Borrow borrow = new Borrow();
        borrow.setBook(book);
        borrow.setReader(reader);
        borrow.setBorrowDate(LocalDate.now());
        borrow.setReturnDate(null);

        borrowRepository.save(borrow);
    }

    public void returnBook(Long bookId) {
        Book book = bookService.getById(bookId);
        Borrow borrow = borrowRepository.findFirstBookByIdAndReturnDateIsNull(bookId)
                .orElseThrow(() -> new RuntimeException("Active borrow not found"));

        borrow.setReturnDate(LocalDate.now());
        borrowRepository.save(borrow);

        book.setStatus(BookStatus.AVAILABLE);
        bookService.saveBook(book);
    }

    public List<Borrow> getAll() {
        return borrowRepository.findAll();
    }
}
