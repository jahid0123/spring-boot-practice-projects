package com.meme.onlinebookportal.controller;

import java.util.ArrayList;
import java.util.List;

import com.meme.onlinebookportal.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meme.onlinebookportal.dto.AddAuthorDto;
import com.meme.onlinebookportal.dto.AddBookDto;
import com.meme.onlinebookportal.dto.AuthorResponseDto;
import com.meme.onlinebookportal.dto.BookResponseDto;
import com.meme.onlinebookportal.dto.BookWithAuthorsDto;
import com.meme.onlinebookportal.dto.UpdateAuthorDto;
import com.meme.onlinebookportal.dto.UpdateBookDto;
import com.meme.onlinebookportal.model.Author;
import com.meme.onlinebookportal.model.Book;
import com.meme.onlinebookportal.service.AuthorService;
import com.meme.onlinebookportal.service.BookService;
import com.meme.onlinebookportal.service.OrderService;
import com.meme.onlinebookportal.service.UserService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final OrderService orderService;
    private final UserService userService;

    public AdminController(BookService bookService, AuthorService authorService, OrderService orderService,
                           UserService userService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.orderService = orderService;
        this.userService = userService;
    }


    @PostMapping(value = "/add/book", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addNewBook(
            @RequestPart("book") AddBookDto addBookDto,
            @RequestPart("image") MultipartFile imageFile) {

        Book book = bookService.addNewBook(addBookDto, imageFile);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping("/get/all/books")
    public ResponseEntity<?> getAllBooks() {
        List<Book> books = bookService.getAllBooksByAdmin();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PutMapping("/update/book")
    public ResponseEntity<?> updateBook(@RequestBody UpdateBookDto updateBookDto) {
        Book book = bookService.updateBook(updateBookDto);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("/delete/book")
    public ResponseEntity<Void> deleteBook(@RequestParam Long id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.OK) ;
    }

    // author
    @PostMapping("/add/author")
    public ResponseEntity<?> addNewAuthor(@RequestBody AddAuthorDto addAuthorDto) {
        Author author = authorService.addNewAuthor(addAuthorDto);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @GetMapping("/get/all/authors")
    public ResponseEntity<?> getAllAuthors() {
        List<Author> author = authorService.getAllAuthorsByAdmin();
        return new ResponseEntity<>(author, HttpStatus.OK);
    }


    @PutMapping("/update/author")
    public ResponseEntity<?> editAuthor(@RequestBody UpdateAuthorDto updateAuthorDto) {
        Author author = authorService.updateAuthor(updateAuthorDto);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @DeleteMapping("/delete/author")
    public ResponseEntity<Void> deleteAuthor(@RequestParam Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get/all/order")
    public ResponseEntity<?> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/get/all/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}