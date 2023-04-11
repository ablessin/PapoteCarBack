package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.*;
import com.greenGo.greenGo.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/message")
@AllArgsConstructor
public class MessageController {
    private final MessageService messageService;
    private final NotificationsService notificationsService;
    private final ChatService chatService;
    private final TrajetService trajetService;
    private final UserService userService;

    @PostMapping("/create")
    public Message create(@RequestBody Message message) {

        List<Notifications> list = new ArrayList<>();
        Optional<Chat> chat = chatService.lireUn(message.getChat().getId());
        Optional<Trajet> trajet = trajetService.lireUn(chat.get().getTrajet().getId());
        Optional<User> user = userService.lireUn(message.getUser().getId());

        if (chat != null && trajet != null && user != null) {
            Notifications notifications = new Notifications();
            notifications.setActionType(ActionType.newMessage.toString());
            notifications.setMessage("Vos avez un nouveau message dans " +  trajet.get().getName());
            notifications.setActivate(true);
            LocalDate date = LocalDate.now();
            notifications.setDate(date);
            notifications.setCreatedAt(date);
            notifications.setUpdateAt(date);
            notifications.setUser(user.get());

            list.add(notifications);
        }

        for (Notifications notifications: list) {
            notificationsService.creer(notifications);
        }

        return  messageService.creer(message);
    }

    @GetMapping("/read")
    public List<Message> read() {
        return messageService.lire();
    }

    @GetMapping("/read/{id}")
    public Optional<Message> read(@PathVariable Long id) { return messageService.lireUn(id);}

    @PutMapping("/update/{id}")
    public Message update(@PathVariable Long id, @RequestBody Message message) {
        return messageService.modifier(id, message);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Optional<Message> message = messageService.lireUn(id);
        List<Notifications> list = new ArrayList<>();
        message.get().getChat().getTrajet().getPassagers().stream().map(item -> {
            Notifications notifications = new Notifications();
            notifications.setActionType(ActionType.supMessage.toString());
            notifications.setMessage("Le message du trajet " + message.get().getChat().getTrajet().getName() + " a été supprimé");
            notifications.setActivate(true);
            LocalDate date = LocalDate.now();
            notifications.setDate(date);
            notifications.setCreatedAt(date);
            notifications.setUpdateAt(date);
            notifications.setUser(item.getUser());

            return list.add(notifications);
        });
        list.stream().map(item -> notificationsService.creer(item));

        return  messageService.supprimer(id);
    }
}
