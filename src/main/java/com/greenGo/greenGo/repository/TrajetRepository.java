package com.greenGo.greenGo.repository;

import com.greenGo.greenGo.modele.Chat;
import com.greenGo.greenGo.modele.Trajet;
import com.greenGo.greenGo.modele.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TrajetRepository extends JpaRepository<Trajet, Long> {
    Trajet findAllById(Long id);

    List<Trajet> findTrajetsByDriver(User user);

    Trajet findByChats(Chat chat);
//    List<Trajet> findAllByStartDateTime(LocalDate date);

}
