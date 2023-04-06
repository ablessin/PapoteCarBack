package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.Chat;
import com.greenGo.greenGo.modele.Trajet;
import com.greenGo.greenGo.repository.ChatRepository;
import com.greenGo.greenGo.repository.TrajetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChatMessageImpl implements ChatService{
    private final ChatRepository chatRepository;

    @Override
    public Chat creer(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public List<Chat> lire() {
        return chatRepository.findAll();
    }

    @Override
    public Optional<Chat> lireUn(Long id) {
        return chatRepository.findById(id);
    }

    @Override
    public Optional<Chat> lireByTrajet(Optional<Trajet> trajet) {
        return chatRepository.findChatByTrajet(trajet);
    }

    @Override
    public Chat modifier(Long id, Chat chat) {
        return chatRepository.findById(id)
                .map(p-> {
                    p.setMessages(chat.getMessages());
//                    p.setTrajet(chat.getTrajet());
                    return chatRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Chat non trouvé"));
    }

    @Override
    public String supprimer(Long id) {
        chatRepository.deleteById(id);
        return "Chat supprimé";
    }
}
