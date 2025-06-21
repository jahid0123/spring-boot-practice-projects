package com.meme.onlinebookportal.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.meme.onlinebookportal.dto.BookResponseDto;
import org.springframework.stereotype.Service;

import com.meme.onlinebookportal.dto.AddBookDto;
import com.meme.onlinebookportal.dto.UpdateBookDto;
import com.meme.onlinebookportal.model.Author;
import com.meme.onlinebookportal.model.Book;
import com.meme.onlinebookportal.repository.AuthorRepository;
import com.meme.onlinebookportal.repository.BookRepository;

import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BookService {

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;

	public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}

	@Transactional
	public Book addNewBook(AddBookDto addBookDto, MultipartFile imageFile) {

		// Check for existing book by ISBN
		Book existBook = bookRepository.findByBookIsbnNumber(addBookDto.getBookIsbnNumber());
		if (existBook != null) {
			throw new RuntimeException("Book already exists with ISBN Number: " + addBookDto.getBookIsbnNumber());
		}

		Book book = new Book();
		book.setBookName(addBookDto.getBookName());
		book.setBookIsbnNumber(addBookDto.getBookIsbnNumber());
		book.setBookPrice(addBookDto.getBookPrice());
		book.setBookRating(addBookDto.getBookRating());
		book.setBookCategory(addBookDto.getBookCategory());
		book.setBookQuantity(addBookDto.getBookQuantity());

		// Save image file to local folder and store the path
		if (imageFile != null && !imageFile.isEmpty()) {
			try {
				String uploadDir = "uploads/";
				String originalFilename = imageFile.getOriginalFilename();
				String uniqueFilename = UUID.randomUUID() + "_" + originalFilename;
				Path uploadPath = Paths.get(uploadDir);

				if (!Files.exists(uploadPath)) {
					Files.createDirectories(uploadPath);
				}

				Path filePath = uploadPath.resolve(uniqueFilename);
				Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

				// Save the relative or absolute file path
				book.setBookImageUrl("/uploads/" + uniqueFilename); // or filePath.toString() for absolute path

			} catch (IOException e) {
				throw new RuntimeException("Failed to save book image", e);
			}
        }

		// Set authors
		if (addBookDto.getBookAuthorIds() != null && !addBookDto.getBookAuthorIds().isEmpty()) {
			Set<Author> authors = new HashSet<>(authorRepository.findAllById(addBookDto.getBookAuthorIds()));
			book.setBookAuthors(authors);
		}

		return bookRepository.save(book);
	}


	@Transactional
	public List<BookResponseDto> getAllBooks() {

		List<Book> books = bookRepository.findAllBooks();
        return books.stream().map(this::mapBookResponseDto).collect(Collectors.toList());
	}

	private BookResponseDto mapBookResponseDto(Book book) {
		BookResponseDto bookResponseDto = new BookResponseDto();
		bookResponseDto.setId(book.getId());
		bookResponseDto.setBookName(book.getBookName());
		bookResponseDto.setBookCategory(book.getBookCategory());
		bookResponseDto.setBookPrice(book.getBookPrice());
		bookResponseDto.setBookRating(book.getBookRating());
		bookResponseDto.setBookImageUrl(book.getBookImageUrl());
		bookResponseDto.setBookIsbnNumber(book.getBookIsbnNumber());

		return bookResponseDto;
	}


	@Transactional
	public Book updateBook(UpdateBookDto updateBookDto) {

		Book existBook = bookRepository.findById(updateBookDto.getId()).orElseThrow(
				() -> new RuntimeException("Book not found with ID: " + updateBookDto.getId())
		);

			if (updateBookDto.getBookName() != null)
				existBook.setBookName(updateBookDto.getBookName());
			if (updateBookDto.getBookCategory() != null)
				existBook.setBookCategory(updateBookDto.getBookCategory());
			if (updateBookDto.getBookQuantity() != null)
				existBook.setBookQuantity(updateBookDto.getBookQuantity());
			if (updateBookDto.getBookIsbnNumber() != null)
				existBook.setBookIsbnNumber(updateBookDto.getBookIsbnNumber());
			if (updateBookDto.getBookPrice() != null)
				existBook.setBookPrice(updateBookDto.getBookPrice());
			if (updateBookDto.getBookRating() != null)
				existBook.setBookRating(updateBookDto.getBookRating());
			if (updateBookDto.getBookImageUrl() != null)
				existBook.setBookImageUrl(updateBookDto.getBookImageUrl());
			if (updateBookDto.getBookAuthorIds() != null) {
				Set<Author> authors = new HashSet<>(authorRepository.findAllById(updateBookDto.getBookAuthorIds()));
				existBook.setBookAuthors(authors);
			}

			return bookRepository.save(existBook);
	}

	@Transactional
	public void deleteBookById(Long id) {
		bookRepository.deleteById(id);
	}

	@Transactional
	public Book getBookById(Long id) {
		Book book = bookRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Book not found with ID: " + id));
		return book;
	}

	@Transactional
	public List<Book> getAllBooksByAdmin() {
		return bookRepository.findAllBooks();
	}
}
