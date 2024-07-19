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
  private final UserService userService;


  public NoteController(BookRepository bookRepository, NoteRepository noteRepository, UserRepository userRepository, UserService userService) {
    this.bookRepository = bookRepository;
    this.noteRepository = noteRepository;
    this.userService = userService;
  }

  /**
   * get all notes that belong to user
   * @return List<Note>
   */
  @GetMapping
  public List<Note> getAllNotes(@AuthenticationPrincipal OAuth2User oAuth2User) {
    User user = userService.getUser(oAuth2User);
    return noteRepository.findByBook_User(user);
  }

  @GetMapping("/note_id/{id}")
  public Note getNoteByNoteId(@AuthenticationPrincipal OAuth2User oAuth2User,@PathVariable Integer id) {
    User user = userService.getUser(oAuth2User);
    return noteRepository.findByBook_UserAndId(user,id);
  }


  @GetMapping("/book_id/{id}")
  public List<Note> getNoteByBook(@AuthenticationPrincipal OAuth2User oAuth2User,@PathVariable Integer id) {
    User user = userService.getUser(oAuth2User);
    return noteRepository.findByBook_IdAndBook_User(id,user);
  }

  @PostMapping("/book_id/{id}")
  public Note createNote(@AuthenticationPrincipal OAuth2User oAuth2User,@PathVariable Integer id,@RequestBody NoteRequest note) {
    User user = userService.getUser(oAuth2User);
    Book book = bookRepository.findById(id).orElse(null);
    if (book == null) {return null;}

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

  @PutMapping("/note_id/{id}")
  public Note updateNote(@AuthenticationPrincipal OAuth2User oAuth2User, @PathVariable Integer id,@PathVariable Integer note_id,@RequestBody NoteRequest noteRequest) {
   User user = userService.getUser(oAuth2User);
   Note note = noteRepository.findByBook_UserAndId(user,id);
   if (note == null) {return null;}
   note.setBody(noteRequest.body);
   note.setTitle(noteRequest.title);
   return noteRepository.save(note);
  }

  @DeleteMapping("/note_id/{id}")
  public void deleteNote(@AuthenticationPrincipal OAuth2User oAuth2User,@PathVariable Integer id) {
    User user = userService.getUser(oAuth2User);
    noteRepository.deleteById(noteRepository.findByBook_UserAndId(user,id).getId());
  }
}
