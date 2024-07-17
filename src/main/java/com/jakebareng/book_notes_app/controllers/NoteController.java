package com.jakebareng.book_notes_app.controllers;

import com.jakebareng.book_notes_app.RequestClasses.NoteRequest;
import com.jakebareng.book_notes_app.repository.BookRepository;
import com.jakebareng.book_notes_app.repository.NoteRepository;
import com.jakebareng.book_notes_app.repository.UserRepository;
import com.jakebareng.book_notes_app.schemas.Book;
import com.jakebareng.book_notes_app.schemas.Note;
import com.jakebareng.book_notes_app.schemas.User;
import com.jakebareng.book_notes_app.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

  private final BookRepository bookRepository;
  private final NoteRepository noteRepository;
  private final UserRepository userRepository;
  private final UserService userService;


  public NoteController(BookRepository bookRepository, NoteRepository noteRepository, UserRepository userRepository, UserService userService) {
    this.bookRepository = bookRepository;
    this.noteRepository = noteRepository;
    this.userRepository = userRepository;
    this.userService = userService;
  }

  /**
   * get all notes
   * @return List<Note>
   */
  @GetMapping
  public List<Note> getAllNotes(@AuthenticationPrincipal OAuth2User oAuth2User) {
    User user = userService.getUser(oAuth2User);
    return noteRepository.findAllByUserId(user.getId());
  }

  @GetMapping("/{book_id}")
  public List<Note> getNoteByBook(@AuthenticationPrincipal OAuth2User oAuth2User,@PathVariable Long book_id) {
    User user = userService.getUser(oAuth2User);
    Book book = bookRepository.findById(book_id).orElse(null);
    if (book == null) { return null;}
    if (book.getUser().equals(user)) {
      return noteRepository.findAllByBookId(book_id);
    }
    return null;
  }

  @GetMapping("/{book_id}/{note_id}")
  public Note getNote (@AuthenticationPrincipal OAuth2User oAuth2User,@PathVariable Long book_id,@PathVariable Long note_id) {
    User user = userService.getUser(oAuth2User);
    Book book = bookRepository.findById(book_id).orElse(null);
    Note note = noteRepository.findById(note_id).orElse(null);
    if (book == null || note == null) { return null;}

    if (book.getUser().equals(user) && note.getBook().equals(book)) {
      return note;
    }
    return null;
  }

  @PostMapping("/{book_id}")
  public Note createNote(@AuthenticationPrincipal OAuth2User oAuth2User,@PathVariable Long book_id,@RequestBody NoteRequest note) {
    User user = userService.getUser(oAuth2User);
    Book book = bookRepository.findById(book_id).orElse(null);
    if (book == null) { return null;}

    //check if book belongs to user
    if (book.getUser().equals(user)) {
      Note newNote = new Note();
      newNote.setBook(book);
      newNote.setBody(note.body);
      newNote.setTitle(note.title);
      return noteRepository.save(newNote);
    }
    return null;
  }

  @PutMapping("/{book_id}/{note_id}")
  public Note updateNote(@AuthenticationPrincipal OAuth2User oAuth2User, @PathVariable Long book_id,@PathVariable Long note_id,@RequestBody NoteRequest note) {
   User user = userService.getUser(oAuth2User);
   Book book = bookRepository.findById(book_id).orElse(null);
   Note oldNote = noteRepository.findById(note_id).orElse(null);
   if (book == null || oldNote == null) { return null;}

   if (book.getUser().equals(user) && oldNote.getBook().equals(book)) {
     oldNote.setBody(note.body);
     oldNote.setTitle(note.title);
     return noteRepository.save(oldNote);
   }

   return null;
  }

  @DeleteMapping("/{book_id}/{note_id}")
  public void deleteNote(@AuthenticationPrincipal OAuth2User oAuth2User,@PathVariable Long book_id,@PathVariable Long note_id) {
    User user = userService.getUser(oAuth2User);
    Book book = bookRepository.findById(book_id).orElse(null);
    Note note = noteRepository.findById(note_id).orElse(null);
    if (book == null || note == null) { return;}
    if (book.getUser().equals(user) && note.getBook().equals(book)) {
      noteRepository.delete(note);
    }
  }



}
