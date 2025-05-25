package com.meme.onlinebookportal.service;

import com.meme.onlinebookportal.dto.AddAuthorDto;
import com.meme.onlinebookportal.model.Author;
import com.meme.onlinebookportal.model.Book;
import com.meme.onlinebookportal.repository.AuthorRepository;
import com.meme.onlinebookportal.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public Author addNewAuthor(AddAuthorDto addAuthorDto) {
        Author existAuthor = authorRepository.findByAuthorNid(addAuthorDto.getAuthorNid());

        if (existAuthor!=null){
            throw new RuntimeException("Author exist by the nid "+ addAuthorDto.getAuthorNid());
        }

        Author author = new Author();
        author.setAuthorName(addAuthorDto.getAuthorName());
        author.setAuthorBio(addAuthorDto.getAuthorBio());
        author.setAuthorNid(addAuthorDto.getAuthorNid());
        author.setAddress(addAuthorDto.getAddress());

        Set<Book> books = bookRepository.findAllById(addAuthorDto.getAuthorBookIds())
                .stream().collect(Collectors.toSet());
        author.setAuthorBooks(books);

        return authorRepository.save(author);

    }
}
