package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.PlaceChamp;
import com.greenGo.greenGo.modele.Trajet;
import com.greenGo.greenGo.modele.User;

import java.util.List;

public interface TrajetService {
    Trajet creer(Trajet trajet);

    List<Trajet> lire();

    List<Trajet> search(PlaceChamp champ, String value);

    List<Trajet> lireByUser(User user);

    Trajet lireUn(Long id);

    Trajet modifier(Long id, Trajet trajet);

    String supprimer(Long id);
}
