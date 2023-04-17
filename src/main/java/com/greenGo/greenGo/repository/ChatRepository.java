package com.greenGo.greenGo.repository;

import com.greenGo.greenGo.modele.Chat;
import com.greenGo.greenGo.modele.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    Chat findChatByTrajet(Trajet trajet);

    Chat findAllById(Long id);
}
