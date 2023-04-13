package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.*;
import com.greenGo.greenGo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return  userService.creer(user);
    }

    @GetMapping("/read")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<User> read() {
        return userService.lire();
    }

    @GetMapping("/read/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public User read(@PathVariable Long id) {
        return userService.lireUn(id);
    }

    @GetMapping("/read/{email}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Optional<User> findByEmail(@PathVariable  String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/read/{username}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Optional<User> findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @PutMapping("/update/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.modifier(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return  userService.supprimer(id);
    }
}
