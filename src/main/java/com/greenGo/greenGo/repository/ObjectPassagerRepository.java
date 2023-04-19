package com.greenGo.greenGo.repository;

import com.greenGo.greenGo.modele.ObjectPassager;
import com.greenGo.greenGo.modele.Place;
import com.greenGo.greenGo.modele.Trajet;
import com.greenGo.greenGo.modele.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObjectPassagerRepository extends JpaRepository<ObjectPassager, Long> {
    ObjectPassager findAllById(Long id);

    List<ObjectPassager> findAllByTrajet(Trajet trajet);

    List<ObjectPassager> findAllByUser(User user);

    int countObjectPassagersByStartAndTrajetAndIsValided(Place place, Trajet trajet, boolean isValided);
}
