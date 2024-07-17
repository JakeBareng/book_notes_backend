package com.jakebareng.book_notes_app.repository;

import com.jakebareng.book_notes_app.schemas.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
  List<Note> findAllByBookId(Long bookId);
  List<Note> findAllByUserId(String userId);
}
