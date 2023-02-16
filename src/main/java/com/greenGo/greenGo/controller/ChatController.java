package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.Chat;
import com.greenGo.greenGo.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chat")
@AllArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/create")
    public Chat create(@RequestBody Chat chat) {
        return  chatService.creer(chat);
    }

    @GetMapping("/read")
    public List<Chat> read() {
        return chatService.lire();
    }

    @GetMapping("/read/{id}")
    public Optional<Chat> read(@PathVariable Long id) { return chatService.lireUn(id);}

    @PutMapping("/update/{id}")
    public Chat update(@PathVariable Long id, @RequestBody Chat chat) {
        return chatService.modifier(id, chat);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return  chatService.supprimer(id);
    }
}
