package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.ActionType;
import com.greenGo.greenGo.modele.Chat;
import com.greenGo.greenGo.modele.Notifications;
import com.greenGo.greenGo.modele.Trajet;
import com.greenGo.greenGo.service.ChatService;
import com.greenGo.greenGo.service.NotificationsService;
import com.greenGo.greenGo.service.TrajetService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/chat")
@AllArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final NotificationsService notificationsService;
    private final TrajetService trajetService;

    @PostMapping("/create")
   // @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Chat create(@RequestBody Chat chat) {
        return  chatService.creer(chat);
    }

    @GetMapping("/read")
    @PreAuthorize("hasRole('USER')")
    public List<Chat> read() {
        return chatService.lire();
    }

    @GetMapping("/read/{id}")
    public Optional<Chat> read(@PathVariable Long id) { return chatService.lireUn(id);}

    @GetMapping("/read/{trajetId}")
    public Optional<Chat> readTrajet(@PathVariable Long trjetId) {
        Optional<Trajet> trajet = trajetService.lireUn(trjetId);
        return chatService.lireByTrajet(trajet);
    }
    @PutMapping("/update/{id}")
    public Chat update(@PathVariable Long id, @RequestBody Chat chat) {
        return chatService.modifier(id, chat);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        Optional<Chat> chat = chatService.lireUn(id);
        List<Notifications> list = new ArrayList<>();
        chat.get().getTrajet().getPassagers().stream().map(item -> {
            Notifications notifications = new Notifications();
            notifications.setActionType(ActionType.supChat.toString());
            notifications.setMessage("Le chat du trajet " + chat.get().getTrajet().getName() + " a été supprimé");
            notifications.setActivate(true);
            LocalDate date = LocalDate.now();
            notifications.setDate(date);
            notifications.setCreatedAt(date);
            notifications.setUpdateAt(date);
            notifications.setUser(item.getUser());

            return list.add(notifications);
        });
        list.stream().map(item -> notificationsService.creer(item));

        return  chatService.supprimer(id);
    }
}
