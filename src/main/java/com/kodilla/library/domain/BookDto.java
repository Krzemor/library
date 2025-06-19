package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookDto {
    private Long id;
    private Long titleId;
    private BookStatus status;
}
