package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.Message;
import com.greenGo.greenGo.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/message")
@AllArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/create")
    public Message create(@RequestBody Message message) {
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
        return  messageService.supprimer(id);
    }
}
