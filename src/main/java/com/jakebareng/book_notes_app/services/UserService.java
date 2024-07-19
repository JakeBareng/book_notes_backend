package com.jakebareng.book_notes_app.services;

import com.jakebareng.book_notes_app.repository.UserRepository;
import com.jakebareng.book_notes_app.schemas.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User getUser(OAuth2User oauth2User) {
    String sub = oauth2User.getAttribute("sub");
    assert sub != null;
    return userRepository.findById(sub).orElseGet(() -> createUser(oauth2User));
  }

  public User createUser(OAuth2User oAuth2User) {
    Map<String, Object> attributes = oAuth2User.getAttributes();
    String sub = (String) attributes.get("sub");
    String email = (String) attributes.get("email");
    String name = (String) attributes.get("name");

    User user = new User();
    user.setId(sub);
    user.setName(name);
    user.setEmail(email);

    return userRepository.save(user);
  }

  public String getPicture(OAuth2User oauth2User) {
    return oauth2User.getAttribute("picture");
  }
}
