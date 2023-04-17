package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.Chat;
import com.greenGo.greenGo.modele.Message;

import java.util.List;

public interface MessageService {
    Message creer(Message message);

    List<Message> lire();

    Message lireUn(Long id);

    List<Message> lireByChat(Chat chat);

    Message modifier(Long id, Message message);

    String supprimer(Long id);
}
