package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.Message;
import com.greenGo.greenGo.modele.Notifications;
import com.greenGo.greenGo.service.NotificationsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notifications")
@AllArgsConstructor
public class NotificationsController {
    private final NotificationsService notificationsservice;

    @PostMapping("/create")
    public Notifications create(@RequestBody Notifications notification) {
        return  notificationsservice.creer(notification);
    }

    @GetMapping("/read")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Notifications> read() {
        return notificationsservice.lire();
    }

    @GetMapping("/read/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Notifications read(@PathVariable Long id) { return notificationsservice.lireUn(id);}

    @PutMapping("/update/{id}")
    public Notifications update(@PathVariable Long id, @RequestBody Notifications notifications) {
        return notificationsservice.modifier(id, notifications );
    }



}
