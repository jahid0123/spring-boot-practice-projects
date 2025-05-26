package com.meme.onlinebookportal.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@RequiredArgsConstructor
public class UpdateBookDto {

    private Long id;
    private String bookName;
    private Integer bookIsbnNumber;
    private BigDecimal bookPrice;
    private BigDecimal bookRating;
    private String bookCategory;
    private Integer bookQuantity;
    private Set<Long> bookAuthorIds;

}