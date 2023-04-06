package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.Chat;
import com.greenGo.greenGo.modele.Trajet;

import java.util.List;
import java.util.Optional;

public interface ChatService {
    Chat creer(Chat chat);

    List<Chat> lire();

    Optional<Chat> lireUn(Long id);
    Optional<Chat> lireByTrajet(Optional<Trajet> trajet);

    Chat modifier(Long id, Chat chat);

    String supprimer(Long id);
}
