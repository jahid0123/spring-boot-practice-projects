package com.meme.onlinebookportal.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.meme.onlinebookportal.model.Book;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@RequiredArgsConstructor
public class AddAuthorDto {

    private String authorName;
    private Integer authorNid;
    private String authorBio;
    private String address;
    private Set<Long> authorBookIds;
}
