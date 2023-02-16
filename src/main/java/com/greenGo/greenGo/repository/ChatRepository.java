package com.greenGo.greenGo.repository;

import com.greenGo.greenGo.modele.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
