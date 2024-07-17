package com.jakebareng.book_notes_app.schemas;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

  @Id
  @Column(name = "id", nullable = false)
  private String id;

  @Column(name = "email", nullable = false, length = 120)
  private String email;

  @Column(name = "name", nullable = false, length = 64)
  private String name;

  public String getId() {
    return id;
  }

  public void setId(String id) {
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

}
