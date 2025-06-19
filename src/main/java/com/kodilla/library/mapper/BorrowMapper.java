package com.kodilla.library.mapper;

import com.kodilla.library.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowMapper {

    public Borrow mapToBorrow(final BorrowDto dto, final Reader reader, final Book book) {
        return new Borrow(
                dto.getId(),
                book,
                reader,
                dto.getBorrowDate(),
                dto.getReturnDate()
        );
    }

    public BorrowDto mapToBorrowDto(final Borrow borrow) {
        return new BorrowDto(
                borrow.getId(),
                borrow.getBook().getId(),
                borrow.getReader().getId(),
                borrow.getBorrowDate(),
                borrow.getReturnDate()
        );
    }

    public List<BorrowDto> mapToBorrowDtoList(final List<Borrow> borrowList) {
        return borrowList.stream()
                .map(this::mapToBorrowDto)
                .toList();
    }
}
