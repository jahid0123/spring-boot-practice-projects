package com.meme.onlinebookportal.controller;

import com.meme.onlinebookportal.dto.OrderRequestDto;
import com.meme.onlinebookportal.model.Book;
import com.meme.onlinebookportal.model.Order;
import com.meme.onlinebookportal.service.BookService;
import com.meme.onlinebookportal.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    private final BookService bookService;
    private final OrderService orderService;

    public UserController(BookService bookService, OrderService orderService) {
        this.bookService = bookService;
        this.orderService = orderService;
    }

    @GetMapping("/get/all/books")
    public ResponseEntity<?> getAllBooks(){

        List<Book> allBooks = bookService.getAllBooks();

        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    @PostMapping("/order/request")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequestDto orderRequestDto){
        ResponseEntity<?> orderRequest = orderService.placeOrderRequest(orderRequestDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
