package com.oguevara.mycrud.services;

import com.oguevara.mycrud.model.Book;
import com.oguevara.mycrud.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServices {

    private final BookRepository repository;

    public BookServices(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAllBooks() {
        return repository.getAllBooks();
    }

    public long addBook(Book book) {
        return repository.addBook(book);
    }

    public long deleteBook(Book book) {
        return repository.deleteBook(book);
    }

    public long updateBook(Book book) {
        return repository.updateBook(book);
    }
}
