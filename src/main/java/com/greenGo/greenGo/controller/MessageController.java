package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.ActionType;
import com.greenGo.greenGo.modele.Chat;
import com.greenGo.greenGo.modele.Message;
import com.greenGo.greenGo.modele.Notifications;
import com.greenGo.greenGo.service.MessageService;
import com.greenGo.greenGo.service.NotificationsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/message")
@AllArgsConstructor
public class MessageController {
    private final MessageService messageService;
    private final NotificationsService notificationsService;


    @PostMapping("/create")
    public Message create(@RequestBody Message message) {

        List<Notifications> list = new ArrayList<>();
        message.getChat().getTrajet().getPassagers().stream().map(item -> {
            Notifications notifications = new Notifications();
            notifications.setActionType(ActionType.newMessage);
            notifications.setMessage("Vos avez un nouveau message dans " +  message.getChat().getTrajet().getName());
            notifications.setActivate(true);
            LocalDate date = LocalDate.now();
            notifications.setDate(date);
            notifications.setCreatedAt(date);
            notifications.setUpdateAt(date);
            notifications.setUser(item.getUser());

            return list.add(notifications);
        });
        list.stream().map(item -> notificationsService.creer(item));

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
            notifications.setActionType(ActionType.supMessage);
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
