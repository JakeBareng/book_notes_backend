package com.jakebareng.book_notes_app.schemas;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_id_gen")
  @SequenceGenerator(name = "books_id_gen", sequenceName = "books_id_seq", allocationSize = 1)
  @Column(name = "book_id", nullable = false)
  private Integer id;

  @Column(name = "title")
  private String title;

  @Column(name = "author")
  private String author;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "user_id", nullable = false)
  @JsonBackReference
  private User user;

  @OneToMany(mappedBy = "book")
  @JsonManagedReference
  private Set<Note> notes;

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

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Set<Note> getNotes() {
    return notes;
  }

  public void setNotes(Set<Note> notes) {
    this.notes = notes;
  }

}
