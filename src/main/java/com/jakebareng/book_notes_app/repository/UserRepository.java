package com.jakebareng.book_notes_app.repository;

import com.jakebareng.book_notes_app.schemas.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
