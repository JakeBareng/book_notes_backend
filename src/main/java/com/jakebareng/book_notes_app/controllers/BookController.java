package com.jakebareng.book_notes_app.controllers;


import com.jakebareng.book_notes_app.repository.BookRepository;
import com.jakebareng.book_notes_app.repository.UserRepository;
import com.jakebareng.book_notes_app.schemas.Book;
import com.jakebareng.book_notes_app.schemas.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

  private BookRepository bookRepository;
  private final UserRepository userRepository;
  public BookController(BookRepository bookRepository, UserRepository userRepository) {
    this.bookRepository = bookRepository;
    this.userRepository = userRepository;
  }


  @GetMapping("/")
  public Map<String, Object> getAllBooks(@AuthenticationPrincipal OAuth2User principal) {
    principal.getAttributes();
  }

}
