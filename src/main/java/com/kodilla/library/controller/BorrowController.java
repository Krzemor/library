package com.kodilla.library.controller;

import com.kodilla.library.domain.BorrowDto;
import com.kodilla.library.mapper.BorrowMapper;
import com.kodilla.library.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/borrows")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;
    private final BorrowMapper borrowMapper;

    @GetMapping
    public ResponseEntity<List<BorrowDto>> getAllBorrows() {
        return ResponseEntity.ok(borrowMapper.mapToBorrowDtoList(borrowService.getAll()));
    }

    @PostMapping("/borrow")
    public ResponseEntity<Void> borrowBook(@RequestParam Long readerId, @RequestParam Long titleId) {
        borrowService.borrowBook(readerId, titleId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/return")
    public ResponseEntity<Void> returnBook(@RequestParam Long bookId) {
        borrowService.returnBook(bookId);
        return ResponseEntity.ok().build();
    }
}
