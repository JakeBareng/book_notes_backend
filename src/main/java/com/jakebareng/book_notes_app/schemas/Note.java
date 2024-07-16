package com.jakebareng.book_notes_app.schemas;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "notes")
public class Note {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notes_id_gen")
  @SequenceGenerator(name = "notes_id_gen", sequenceName = "notes_id_seq", allocationSize = 1)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "title", length = 100)
  private String title;

  @Column(name = "body", length = Integer.MAX_VALUE)
  private String body;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "book_id", nullable = false)
  private Book book;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

}
