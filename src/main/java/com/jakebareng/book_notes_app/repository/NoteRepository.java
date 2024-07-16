package com.jakebareng.book_notes_app.repository;

import com.jakebareng.book_notes_app.schemas.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
