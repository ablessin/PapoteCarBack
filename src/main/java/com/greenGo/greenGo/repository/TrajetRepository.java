package com.greenGo.greenGo.repository;

import com.greenGo.greenGo.modele.Trajet;
import com.greenGo.greenGo.modele.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrajetRepository extends JpaRepository<Trajet, Long> {
    Trajet findAllById(Long id);

    List<Trajet> findTrajetsByDriver(User user);

}
