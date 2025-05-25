package com.meme.onlinebookportal.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.meme.onlinebookportal.model.Author;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Data
@RequiredArgsConstructor
public class AddBookDto {

    private String bookName;
    private Integer bookIsbnNumber;
    private BigDecimal bookPrice;
    private BigDecimal bookRating;
    private String bookCategory;
    private Integer bookQuantity;
    private Set<Long> bookAuthorIds;

}
