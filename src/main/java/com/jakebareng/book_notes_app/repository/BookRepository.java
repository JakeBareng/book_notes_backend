package com.jakebareng.book_notes_app.repository;

import com.jakebareng.book_notes_app.schemas.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
  List<Book> findAllByUserId(String user_id);
  Book findByUseridAndBookId(String user_id, long book_id);

}
