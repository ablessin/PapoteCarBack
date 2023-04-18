package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.*;
import com.greenGo.greenGo.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        Chat chat = chatService.lireUn(message.getChat().getId());
        Trajet trajet = trajetService.lireUn(chat.getTrajet().getId());
        User user = userService.lireUn(message.getUser().getId());

        if (chat != null && trajet != null && user != null) {
            Notifications notifications = new Notifications();
            notifications.setActionType(ActionType.newMessage.toString());
            notifications.setMessage("Vos avez un nouveau message dans " +  trajet.getName());
            notifications.setActivate(true);
            LocalDate date = LocalDate.now();
            notifications.setDate(date);
            notifications.setCreatedAt(date);
            notifications.setUpdateAt(date);
            notifications.setUser(user);

            list.add(notifications);
        }

        for (Notifications notifications: list) {
            notificationsService.creer(notifications);
        }

        return  messageService.creer(message);
    }

    @GetMapping("/read")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Message> read() {
        return messageService.lire();
    }

    @GetMapping("/read/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Message read(@PathVariable Long id) { return messageService.lireUn(id);}

    @GetMapping("/read/chat/{chatId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Message> readByChat(@PathVariable Long chatId) {
        Chat chat = chatService.lireUn(chatId);
        return messageService.lireByChat(chat);
    }

    @PutMapping("/update/{id}")
    public Message update(@PathVariable Long id, @RequestBody Message message) {
        return messageService.modifier(id, message);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        List<Notifications> list = new ArrayList<>();

        Message message = messageService.lireUn(id);
        Chat chat = message.getChat();
        Trajet trajet = chat.getTrajet();

        Set<ObjectPassager> userList = trajet.getPassagers();

        for (ObjectPassager objectPassager: userList) {
            Notifications notifications = new Notifications();
            notifications.setActionType(ActionType.supMessage.toString());
            notifications.setMessage("Le message du trajet " + message.getChat().getTrajet().getName() + " a été supprimé");
            notifications.setActivate(true);
            LocalDate date = LocalDate.now();
            notifications.setDate(date);
            notifications.setCreatedAt(date);
            notifications.setUpdateAt(date);
            notifications.setUser(objectPassager.getUser());

            list.add(notifications);
        }

        for (Notifications notifications: list) {
            notificationsService.creer(notifications);
        }

        return  messageService.supprimer(id);
    }
}
