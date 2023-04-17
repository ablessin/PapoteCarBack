package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.Notifications;
import com.greenGo.greenGo.modele.User;
import com.greenGo.greenGo.service.NotificationsService;
import com.greenGo.greenGo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@AllArgsConstructor
public class NotificationsController {
    private final NotificationsService notificationsservice;
    private final UserService userService;

    @PostMapping("/create")
    public Notifications create(@RequestBody Notifications notification) {
        return  notificationsservice.creer(notification);
    }

    @GetMapping("/read")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Notifications> read() {
        return notificationsservice.lire();
    }

    @GetMapping("/read/user/{userId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Notifications> readByUser(@PathVariable Long userId) {
        User user = userService.lireUn(userId);
        return notificationsservice.lireByUser(user);
    }

    @GetMapping("/read/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Notifications read(@PathVariable Long id) { return notificationsservice.lireUn(id);}

    @PutMapping("/update/{id}")
    public Notifications update(@PathVariable Long id, @RequestBody Notifications notifications) {
        return notificationsservice.modifier(id, notifications );
    }



}
