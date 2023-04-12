package com.greenGo.greenGo.repository;


import com.greenGo.greenGo.modele.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationsRepository extends  JpaRepository<Notifications, Long> {
    Notifications findAllById(Long id);
}
