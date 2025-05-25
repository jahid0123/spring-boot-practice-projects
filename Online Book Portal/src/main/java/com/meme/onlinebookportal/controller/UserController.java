package com.meme.onlinebookportal.controller;

import com.meme.onlinebookportal.model.Book;
import com.meme.onlinebookportal.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    private final BookService bookService;

    public UserController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/get/all/books")
    public ResponseEntity<?> getAllBooks(){

        List<Book> allBooks = bookService.getAllBooks();

        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }
}
