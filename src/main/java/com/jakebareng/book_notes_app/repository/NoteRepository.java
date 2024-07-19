package com.jakebareng.book_notes_app.repository;

import com.jakebareng.book_notes_app.schemas.Book;
import com.jakebareng.book_notes_app.schemas.Note;
import com.jakebareng.book_notes_app.schemas.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {
  List<Note> findAllByBook(Book book);
  List<Note> findByBook_UserAndBookId(User book_user, Integer book_id);
  List<Note> findByBook_User(User user);
  List<Note> findByBook_IdAndBook_User(Integer book, User user);
  Note findByBook_UserAndId (User book_user, Integer id);
}
