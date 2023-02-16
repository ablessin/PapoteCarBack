package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    Message creer(Message message);

    List<Message> lire();

    Optional<Message> lireUn(Long id);

    Message modifier(Long id, Message message);

    String supprimer(Long id);
}
