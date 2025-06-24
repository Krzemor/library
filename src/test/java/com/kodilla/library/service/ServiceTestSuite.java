package com.kodilla.library.service;

import com.kodilla.library.domain.*;
import com.kodilla.library.repository.BookRepository;
import com.kodilla.library.repository.BorrowRepository;
import com.kodilla.library.repository.ReaderRepository;
import com.kodilla.library.repository.TitleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class ServiceTestSuite {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowRepository borrowRepository;

    @Test
    void testCreateAndFindReader() {
        //Given
        Reader reader = new Reader("Jan", "Nowak", LocalDate.of(2025, 6,1));
        readerRepository.save(reader);

        //When
        List<Reader> readers = readerRepository.findAll();

        //Then
        assertThat(readers.size()).isEqualTo(1);
        assertThat(readers.get(0).getFirstName()).isEqualTo("Jan");
    }

    @Test
    void testSaveTitleAndBook() {
        //Given
        Title title = new Title("Metro 2033", "Dimitry Glukhovsky", 2005);
        titleRepository.save(title);

        Book book = new Book(title, BookStatus.AVAILABLE);
        Book book2 = new Book(title, BookStatus.BORROWED);
        bookRepository.save(book);
        bookRepository.save(book2);

        //When
        List<Book> books = bookRepository.findAll();

        //then
        assertThat(books.get(0).getTitle().getTitle()).isEqualTo("Metro 2033");
        assertThat(books.size()).isEqualTo(2);
    }

    @Test
    void testBorrowBook() {
        //Given
        Reader reader = new Reader("Jan", "Nowak", LocalDate.of(2025, 6,1));
        readerRepository.save(reader);

        Title title = new Title("1984", "George Orwell", 1949);
        titleRepository.save(title);

        Book book = new Book(title, BookStatus.AVAILABLE);
        bookRepository.save(book);

        Borrow borrow = new Borrow(book, reader, LocalDate.now(), null);
        borrowRepository.save(borrow);

        //When
        List<Borrow> borrows = borrowRepository.findAll();

        //Then
        assertThat(borrows.size()).isEqualTo(1);
        assertThat(borrows.get(0).getReader().getFirstName()).isEqualTo("Jan");
    }

    @Test
    void testCountAvailableBooks() {
        //Given
        Title title = new Title("1984", "George Orwell", 1949);
        titleRepository.save(title);

        Book book1 = new Book(title, BookStatus.AVAILABLE);
        Book book2 = new Book(title, BookStatus.AVAILABLE);
        Book book3 = new Book(title, BookStatus.BORROWED);
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        BookService bookService = new BookService(bookRepository, null, titleRepository);

        //When
        long ctr = bookService.countAvailableBooks(title.getId());

        //Then
        assertThat(ctr).isEqualTo(2);
    }
}
