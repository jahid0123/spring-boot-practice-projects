package com.meme.onlinebookportal.service;

import com.meme.onlinebookportal.constants.OrderStatus;
import com.meme.onlinebookportal.dto.OrderResponseDto;
import com.meme.onlinebookportal.dto.ShippedOrderResponseDto;
import com.meme.onlinebookportal.model.Book;
import com.meme.onlinebookportal.model.Order;
import com.meme.onlinebookportal.model.OrderShipped;
import com.meme.onlinebookportal.repository.OrderRepository;
import com.meme.onlinebookportal.repository.OrderShippedRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderShippedService {

    private final OrderShippedRepository orderShippedRepository;
    private final OrderRepository orderRepository;


    public OrderShippedService(OrderShippedRepository orderShippedRepository, OrderRepository orderRepository) {
        this.orderShippedRepository = orderShippedRepository;
        this.orderRepository = orderRepository;
    }


    @Transactional
    public OrderShipped orderShipped(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Order not found by the id: "+id)
        );

        OrderShipped orderShipped = new OrderShipped();
        order.setOrderStatus(OrderStatus.DEVLIVERED);
        orderShipped.setOrder(order);

        return orderShippedRepository.save(orderShipped);
    }

    @Transactional
    public List<ShippedOrderResponseDto> getAllShippedOrder() {
        List<OrderShipped> list = orderShippedRepository.findAll();

        List<ShippedOrderResponseDto> shippedList = list.stream().map(this::ShippedOrderResponseDto).collect(Collectors.toList());
        return shippedList;
    }

    private ShippedOrderResponseDto ShippedOrderResponseDto(OrderShipped orderShipped) {

        ShippedOrderResponseDto dto = new ShippedOrderResponseDto();
        dto.setId(orderShipped.getId());

        dto.setOrderStatus(orderShipped.getOrderStatus());
        dto.setOrderPrice(orderShipped.getOrder().getOrderPrice());
        List<String > bookNames = orderShipped.getOrder().getBooks().stream().map(Book::getBookName).collect(Collectors.toList());
        dto.setBookName(bookNames);
        dto.setOrderedAt(orderShipped.getOrder().getCreatedAt());
        dto.setOrderId(orderShipped.getOrder().getOrderId());

        dto.setShippedAt(orderShipped.getCreatedAt());

        dto.setUserAddress(orderShipped.getOrder().getAddress());
        dto.setUserName(orderShipped.getOrder().getUser().getName());
        dto.setUserId(orderShipped.getOrder().getUser().getId());
        dto.setUserPhone(orderShipped.getOrder().getUser().getPhoneNumber());

        return dto;
    }
}
