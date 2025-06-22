package com.meme.onlinebookportal.dto;

import com.meme.onlinebookportal.constants.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@RequiredArgsConstructor
public class ShippedOrderResponseDto {

    private Long id;
    private Long orderId;
    private BigDecimal orderPrice;
    private String userName;
    private String userAddress;
    private String userPhone;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
    private Long userId;
    private List<String> bookName;
    private LocalDateTime shippedAt;
    private LocalDateTime orderedAt;

}
