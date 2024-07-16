package com.jakebareng.book_notes_app.services;

import com.jakebareng.book_notes_app.repository.UserRepository;
import com.jakebareng.book_notes_app.schemas.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserService {

  private UserRepository userRepository;


}
