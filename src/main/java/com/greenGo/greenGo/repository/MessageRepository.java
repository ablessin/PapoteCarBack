package com.greenGo.greenGo.repository;

import com.greenGo.greenGo.modele.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Message findAllById(Long id);
}
