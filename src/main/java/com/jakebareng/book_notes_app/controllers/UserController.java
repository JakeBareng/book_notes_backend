package com.jakebareng.book_notes_app.controllers;

import com.jakebareng.book_notes_app.schemas.User;
import com.jakebareng.book_notes_app.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/")
  public User getUser(@AuthenticationPrincipal OAuth2User user) {
    return userService.getUser(user);
  }
}
