package com.tapusd.demomasterslavemysql.controller;

import com.tapusd.demomasterslavemysql.domain.Book;
import com.tapusd.demomasterslavemysql.dto.ActionResponse;
import com.tapusd.demomasterslavemysql.dto.BookDTO;
import com.tapusd.demomasterslavemysql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<ActionResponse> handle400Exceptions(Exception ex) {
        return ResponseEntity.badRequest().body(new ActionResponse(false, ex.getMessage()));
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewBook(@RequestBody BookDTO dto) {
        bookService.save(dto);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void patchBook(@RequestBody BookDTO dto) {
        bookService.patchBook(dto);
    }
}
