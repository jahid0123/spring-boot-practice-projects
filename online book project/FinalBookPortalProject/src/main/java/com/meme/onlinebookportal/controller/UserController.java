package com.meme.onlinebookportal.controller;

import com.meme.onlinebookportal.dto.AuthorResponseDto;
import com.meme.onlinebookportal.dto.BookResponseDto;
import com.meme.onlinebookportal.dto.OrderRequestDto;
import com.meme.onlinebookportal.dto.OrderResponseDto;
import com.meme.onlinebookportal.model.Author;
import com.meme.onlinebookportal.model.Book;
import com.meme.onlinebookportal.model.Order;
import com.meme.onlinebookportal.service.AuthorService;
import com.meme.onlinebookportal.service.BookService;
import com.meme.onlinebookportal.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    private final BookService bookService;
    private final OrderService orderService;
    private final AuthorService authorService;

    public UserController(BookService bookService, OrderService orderService, AuthorService authorService) {
        this.bookService = bookService;
        this.orderService = orderService;
        this.authorService = authorService;
    }

    @GetMapping("/get/all/books")
    public ResponseEntity<?> getAllBooks() {
        List<BookResponseDto> bookList = bookService.getAllBooks();
        return new ResponseEntity<>(bookList, HttpStatus.OK) ;
    }

    @GetMapping("/get/all/authors")
    public ResponseEntity<?> getAllAuthors() {
        List<AuthorResponseDto> authorList = authorService.getAllAuthors();
        return new ResponseEntity<>(authorList, HttpStatus.OK) ;
    }

    @PostMapping("/order/request")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto orderRequest = orderService.placeOrderRequest(orderRequestDto);
        return new ResponseEntity<>(orderRequest,HttpStatus.CREATED);
    }
    @GetMapping("/get/all/order/by/me")
    public ResponseEntity<?> getAllOrdersByMe(@RequestParam Long userId ) {
        List<Order> orders = orderService.getAllOrdersByMe(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}
