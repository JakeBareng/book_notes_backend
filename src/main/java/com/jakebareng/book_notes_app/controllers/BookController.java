package com.jakebareng.book_notes_app.controllers;


import com.jakebareng.book_notes_app.RequestClasses.BookRequest;
import com.jakebareng.book_notes_app.repository.BookRepository;
import com.jakebareng.book_notes_app.repository.UserRepository;
import com.jakebareng.book_notes_app.schemas.Book;
import com.jakebareng.book_notes_app.schemas.User;
import com.jakebareng.book_notes_app.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

  private final BookRepository bookRepository;
  private final UserService userService;
  public BookController(BookRepository bookRepository, UserRepository userRepository, UserService userService) {
    this.bookRepository = bookRepository;
    this.userService = userService;
  }


  @GetMapping
  public List<Book> getAllBooks(@AuthenticationPrincipal OAuth2User oAuth2User) {
    // get user
    User user = userService.getUser(oAuth2User);

    // get user books
      return bookRepository.findAllByUser_Id(user.getId());
  }

  @GetMapping("/{book_id}")
  public Book getBookById(@AuthenticationPrincipal OAuth2User oAuth2User, @PathVariable Long book_id) {
    User user = userService.getUser(oAuth2User);

    return bookRepository.findByUser_IdAndId(user.getId(),book_id);
  }

  @PostMapping("/")
  public Book createBook(@AuthenticationPrincipal OAuth2User oAuth2User, @RequestBody BookRequest bookRequest) {
    User user = userService.getUser(oAuth2User);
    Book book = new Book();
    book.setUser(user);
    book.setTitle(bookRequest.title);
    book.setAuthor(bookRequest.author);
    return bookRepository.save(book);
  }

  @PutMapping("/{book_id}")
  public Book EditBook(@PathVariable long book_id,@AuthenticationPrincipal OAuth2User oAuth2User, @RequestBody BookRequest bookRequest) {
    User user = userService.getUser(oAuth2User);
    Book book = bookRepository.findByUser_IdAndId(user.getId(),book_id);

    if (book != null) {
      book.setTitle(bookRequest.title);
      book.setAuthor(bookRequest.author);
      return bookRepository.save(book);
    }
   return null;
  }

  @DeleteMapping("/{book_id}")
  public void deleteBook(@PathVariable long book_id, @AuthenticationPrincipal OAuth2User oAuth2User) {
    User user = userService.getUser(oAuth2User);
    Book book = bookRepository.findByUser_IdAndId(user.getId(),book_id);
    if (book != null) {
      bookRepository.delete(book);
    }
  }
}
