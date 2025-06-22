package com.kodilla.library.controller;

import com.kodilla.library.domain.BookDto;
import com.kodilla.library.domain.BookStatus;
import com.kodilla.library.mapper.BookMapper;
import com.kodilla.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @PostMapping
    public ResponseEntity<Void> addBook(@RequestBody BookDto bookDto) {
        bookService.saveBook(bookMapper.mapToBook(bookDto));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{bookId}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long bookId, @RequestParam BookStatus status) {
        bookService.updateStatus(bookId, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/available")
    public ResponseEntity<Long> getAvailableBooks(@RequestParam Long titleId) {
        long count = bookService.countAvailableBooks(titleId);
        return ResponseEntity.ok(count);
    }
}
