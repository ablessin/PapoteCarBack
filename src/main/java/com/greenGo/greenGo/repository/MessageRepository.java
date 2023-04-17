package com.greenGo.greenGo.repository;

import com.greenGo.greenGo.modele.Chat;
import com.greenGo.greenGo.modele.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Message findAllById(Long id);

    List<Message> findAllByChat(Chat chat);
}
