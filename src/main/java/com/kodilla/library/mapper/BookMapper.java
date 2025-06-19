package com.kodilla.library.mapper;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.BookDto;
import com.kodilla.library.domain.Title;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookMapper {

    public Book mapToBook(final BookDto dto, final Title title) {
        return new Book(
                dto.getId(),
                title,
                dto.getStatus()
        );
    }

    public BookDto mapToBookDto(final Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle().getId(),
                book.getStatus()
        );
    }

    public List<BookDto> mapToBookDtoList(final List<Book> bookList) {
        return bookList.stream()
                .map(this::mapToBookDto)
                .toList();
    }
}
