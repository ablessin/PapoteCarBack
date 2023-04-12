package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User creer(User user);

    List<User> lire();

    User lireUn(Long id);

    Boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    User modifier(Long id, User user);

    String supprimer(Long id);
}
