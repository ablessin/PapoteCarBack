package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.ObjectPassager;
import com.greenGo.greenGo.modele.Place;
import com.greenGo.greenGo.modele.Trajet;
import com.greenGo.greenGo.modele.User;

import java.util.List;

public interface ObjectPassagerService {
    ObjectPassager creer(ObjectPassager objectPassager);

    List<ObjectPassager> lire();

    List<ObjectPassager> lireByUser(User user);

    List<ObjectPassager> lireByTrajet(Long id);

    ObjectPassager lireUn(Long id);

    int countStart(Place place, Trajet trajet);

    int countEnd(Place place, Trajet trajet);

    ObjectPassager modifier(Long id, ObjectPassager objectPassager);

    ObjectPassager accepted(Long id);

    String supprimer(Long id);
}
