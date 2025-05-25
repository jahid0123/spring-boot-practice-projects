package com.meme.onlinebookportal.controller;

import com.meme.onlinebookportal.dto.AddAuthorDto;
import com.meme.onlinebookportal.dto.AddBookDto;
import com.meme.onlinebookportal.model.Author;
import com.meme.onlinebookportal.model.Book;
import com.meme.onlinebookportal.service.AuthorService;
import com.meme.onlinebookportal.service.BookService;
import com.meme.onlinebookportal.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final OrderService orderService;


    public AdminController(BookService bookService, AuthorService authorService, OrderService orderService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.orderService = orderService;
    }

    @PostMapping("/add/book")
    public ResponseEntity<?> addNewBook(@RequestBody AddBookDto addBookDto){
        Book book = bookService.addNewBook(addBookDto);

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PostMapping("/add/author")
    public ResponseEntity<?> addNewAuthor(@RequestBody AddAuthorDto addAuthorDto){
        Author author = authorService.addNewAuthor(addAuthorDto);

        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

//    @GetMapping("/add/book")
//    public ResponseEntity<?> getAllBooks(){
//        List<Book> book = bookService.addNewBook();
//
//        return new ResponseEntity<>(book, HttpStatus.CREATED);
//    }

}
