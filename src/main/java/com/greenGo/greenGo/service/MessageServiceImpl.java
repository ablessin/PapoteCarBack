package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.Message;
import com.greenGo.greenGo.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Override
    public Message creer(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> lire() {
        return messageRepository.findAll();
    }

    @Override
    public Message lireUn(Long id) {
        return messageRepository.findAllById(id);
    }

    @Override
    public Message modifier(Long id, Message message) {
        return messageRepository.findById(id)
                .map(p-> {
                    p.setDescription(message.getDescription());
                    p.setUser(message.getUser());
                    return messageRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Message non trouvé"));
    }

    @Override
    public String supprimer(Long id) {
        messageRepository.deleteById(id);
        return "Message supprimé";
    }
}
