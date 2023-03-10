package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.ObjectPassager;

import java.util.List;
import java.util.Optional;

public interface ObjectPassagerService {
    ObjectPassager creer(ObjectPassager objectPassager);

    List<ObjectPassager> lire();

    Optional<ObjectPassager> lireUn(Long id);

    ObjectPassager modifier(Long id, ObjectPassager objectPassager);

    String supprimer(Long id);
}
