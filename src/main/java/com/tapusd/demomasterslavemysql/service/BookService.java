package com.tapusd.demomasterslavemysql.service;

import com.tapusd.demomasterslavemysql.domain.Book;
import com.tapusd.demomasterslavemysql.dto.BookDTO;
import com.tapusd.demomasterslavemysql.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public Book save(BookDTO dto) {
        Assert.notNull(dto, "BookDTO can not be null");
        Assert.notNull(dto.title(), "Title can not be null");
        Assert.notNull(dto.details(), "Details can not be null");

        var book = new Book();
        book.setTitle(dto.title());
        book.setDetails(dto.details());
        return bookRepository.save(book);
    }

    @Transactional
    public void patchBook(BookDTO dto) {
        Assert.notNull(dto.id(), "Book id can not be null");

        bookRepository.findById(dto.id())
                .ifPresentOrElse(book -> {
                    if (StringUtils.hasText(dto.title())) {
                        book.setTitle(dto.title());
                    }

                    if (StringUtils.hasText(dto.details())) {
                        book.setDetails(dto.details());
                    }
                }, () -> {
                    throw new IllegalStateException("Book not found with provided id");
                });
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("Book not found with provided id");
                });
    }
}
