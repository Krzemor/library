package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class BorrowDto {

    private Long id;
    private Long bookId;
    private Long readerId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
