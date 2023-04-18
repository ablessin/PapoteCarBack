package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.*;
import com.greenGo.greenGo.service.ChatService;
import com.greenGo.greenGo.service.NotificationsService;
import com.greenGo.greenGo.service.TrajetService;
import com.greenGo.greenGo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/chat")
@AllArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final NotificationsService notificationsService;
    private final TrajetService trajetService;
    private final UserService userService;

    @PostMapping("/create")
    public Chat create(@RequestBody Chat chat) {
        return  chatService.creer(chat);
    }

    @GetMapping("/read")
//    @PreAuthorize("hasRole('USER')")
    public List<Chat> read() {
        return chatService.lire();
    }

    @GetMapping("/read/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Chat read(@PathVariable Long id) {
        return chatService.lireUn(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/read/trajet/{trajetId}")
    public Chat readTrajet(@PathVariable Long trjetId) {
        Trajet trajet = trajetService.lireUn(trjetId);
        return chatService.lireByTrajet(trajet);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/read/user/{userId}")
    public List<Chat> readByUser(@PathVariable Long userId) {
        List<Chat> chatList = new ArrayList<>();

        User user = userService.lireUn(userId);

        if (user.getTrajets().size() > 0) {
            Map<Set<Chat>, List<Trajet>> groups = user.getTrajets().stream()
                    .collect(Collectors.groupingBy(Trajet::getChats));

            log.warn(groups.toString());
            for (Map.Entry<Set<Chat>, List<Trajet>> entry: groups.entrySet()) {
                for (Chat test: entry.getValue().get(0).getChats()) {
                    Chat chat = chatService.lireUn(test.getId());
                    chatList.add(chat);
                }
            }
        }
        return chatList;
    }

    @PutMapping("/update/{id}")
    public Chat update(@PathVariable Long id, @RequestBody Chat chat) {
        return chatService.modifier(id, chat);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        List<Notifications> list = new ArrayList<>();
        Chat chat = chatService.lireUn(id);
        Set<Message> messages = chat.getMessages();
        Map<User, List<Message>> userListMap = messages.stream()
                .collect(Collectors.groupingBy(Message::getUser));

        for (Map.Entry<User, List<Message>> user: userListMap.entrySet()) {
            Notifications notifications = new Notifications();
            notifications.setActionType(ActionType.supChat.toString());
            notifications.setMessage("Le chat du trajet " + chat.getTrajet().getName() + " a été supprimé");
            notifications.setActivate(true);
            LocalDate date = LocalDate.now();
            notifications.setDate(date);
            notifications.setCreatedAt(date);
            notifications.setUpdateAt(date);
            notifications.setUser(user.getKey());

            list.add(notifications);
        }

        for (Notifications notifications: list) {
            notificationsService.creer(notifications);
        }

        return  chatService.supprimer(id);
    }
}
