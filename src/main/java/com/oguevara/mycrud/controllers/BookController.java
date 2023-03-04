package com.oguevara.mycrud.controllers;

import com.oguevara.mycrud.model.Book;
import com.oguevara.mycrud.services.BookServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    private final BookServices services;

    public BookController(BookServices services) {
        this.services = services;
    }


    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return services.getAllBooks();
    }

    @PostMapping("/books")
    public long addBook(@RequestBody Book book) {
        return services.addBook(book);
    }

    //CREAR RUTA PARA ELIMINAR UN LIBRO
    @PostMapping("/books/delete")
    public long deleteBook(@RequestBody Book book) {
        return services.deleteBook(book);
    }

    //CREAR RUTA PARA ACTUALIZAR UN LIBRO
    @PostMapping("/books/update")
    public long updateBook(@RequestBody Book book) {
        return services.updateBook(book);
    }

}