package com.greenGo.greenGo.repository;

import com.greenGo.greenGo.modele.Chat;
import com.greenGo.greenGo.modele.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    Optional<Chat> findChatByTrajet(Optional<Trajet> trajet);
}
