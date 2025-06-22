package com.meme.onlinebookportal.service;

import com.meme.onlinebookportal.dto.OrderRequestDto;
import com.meme.onlinebookportal.dto.OrderResponseDto;
import com.meme.onlinebookportal.model.Book;
import com.meme.onlinebookportal.model.Order;
import com.meme.onlinebookportal.model.User;
import com.meme.onlinebookportal.repository.BookRepository;
import com.meme.onlinebookportal.repository.OrderRepository;
import com.meme.onlinebookportal.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public OrderResponseDto placeOrderRequest(OrderRequestDto orderRequestDto) {

        // Fetch and validate user
        User user = userRepository.findById(orderRequestDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found with ID: " + orderRequestDto.getUserId()));

        // Fetch and validate books
        List<Long> requestedIds = orderRequestDto.getBookIds();
        List<Book> foundBooks = bookRepository.findAllById(requestedIds);

        if (foundBooks.size() != requestedIds.size()) {
            throw new RuntimeException("Some books not found with IDs: " + requestedIds);
        }

        List<Book> books = new ArrayList<>(foundBooks);


        BigDecimal totalPrice = books.stream().map(book -> book.getBookPrice() != null ? book.getBookPrice() : BigDecimal.ZERO).reduce(BigDecimal.ZERO, BigDecimal::add);

        // Create Order entity
        Order order = new Order();
        order.setUser(user);
        order.setBooks(new HashSet<>(books));

        BigDecimal finalPrice = orderRequestDto.getOrderPrice() != null ? orderRequestDto.getOrderPrice() : totalPrice;

        order.setOrderPrice(finalPrice);

        // Save order
        Order savedOrder = orderRepository.save(order);

        // Build response DTO
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setOrderId(savedOrder.getOrderId());
        responseDto.setUserId(savedOrder.getUser().getId());
        responseDto.setOrderPrice(savedOrder.getOrderPrice());


        return responseDto;
    }


    //Admin property
    @Transactional
    public List<OrderResponseDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::mapOrderResponseDto).collect(Collectors.toList());
    }

    private OrderResponseDto mapOrderResponseDto(Order order) {
        OrderResponseDto convertTo = new OrderResponseDto();
        convertTo.setOrderId(order.getOrderId());
        convertTo.setUserId(order.getUser().getId());
        convertTo.setOrderPrice(order.getOrderPrice());
        convertTo.setCreatedAt(order.getCreatedAt());
        convertTo.setUserPhone(order.getUser().getPhoneNumber());
        convertTo.setUserName(order.getUser().getName());
        convertTo.setUserAddress(order.getAddress());
        // Assuming getBooks() returns a List<Book> and Book has a getName() method
        List<String> bookNames = order.getBooks()
                .stream()
                .map(Book::getBookName)
                .collect(Collectors.toList());
        convertTo.setBookName(bookNames); // assuming setBookName accepts List<String>
        return convertTo;
    }

    @Transactional
    public List<Order> getAllOrdersByMe(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found with ID: " + userId));

        return orderRepository.findAllByUser_Id(userId);
    }
}


