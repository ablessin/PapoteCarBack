package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.*;
import com.greenGo.greenGo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/update/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.modifier(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return  userService.supprimer(id);
    }
}
