package com.jakebareng.book_notes_app.repository;

import com.jakebareng.book_notes_app.schemas.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
  List<Book> findAllByUser_Id(String user_id);
  Book findByUser_IdAndId(String user_id, long book_id);

}
