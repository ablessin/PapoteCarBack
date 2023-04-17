package com.greenGo.greenGo.repository;

import com.greenGo.greenGo.modele.Notifications;
import com.greenGo.greenGo.modele.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationsRepository extends  JpaRepository<Notifications, Long> {
    Notifications findAllById(Long id);

    List<Notifications> findAllByUser(User user);
}
