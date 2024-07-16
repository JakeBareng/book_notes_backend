package com.jakebareng.book_notes_app.schemas;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_gen")
  @SequenceGenerator(name = "users_id_gen", sequenceName = "users_id_seq", allocationSize = 1)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "email", nullable = false, length = 120)
  private String email;

  @Column(name = "name", nullable = false, length = 64)
  private String name;

  @OneToMany(mappedBy = "user")
  private Set<Book> books = new LinkedHashSet<>();

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Book> getBooks() {
    return books;
  }

  public void setBooks(Set<Book> books) {
    this.books = books;
  }

}
