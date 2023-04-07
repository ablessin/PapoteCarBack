package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.Notifications;
import com.greenGo.greenGo.modele.Place;
import com.greenGo.greenGo.modele.Trajet;
import com.greenGo.greenGo.modele.User;
import com.greenGo.greenGo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return userService.creer(user);
    }

    @GetMapping("/read")
    public List<User> read() {
        return userService.lire();
    }

    @GetMapping("/read/{id}")
    public Optional<User> read(@PathVariable Long id) {
        return userService.lireUn(id);
    }

    @GetMapping("/read/{email}")
    Boolean existsByEmail(@PathVariable  String email)
    {
        return  userService.existsByEmail(email);
    }

    @GetMapping("/read/{username}")
    public Optional<User> findByUsername(@PathVariable String username) {
        return  userService.findByUsername(username);
    }

    @GetMapping("/read/{username}")
    public Boolean existsByUsername(@PathVariable String username) {
        return userService.existsByUsername(username);
    }

    @PutMapping("/update/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.modifier(id, user);
    }

    @DeleteMapping
    public String delete(@PathVariable Long id) {
        return userService.supprimer(id);
    }
}