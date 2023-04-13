package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.Place;
import com.greenGo.greenGo.modele.PlaceChamp;
import com.greenGo.greenGo.modele.Trajet;

import java.util.List;
import java.util.Optional;

public interface TrajetService {
    Trajet creer(Trajet trajet);

    List<Trajet> lire();

    List<Trajet> search(PlaceChamp champ, String value);

    Trajet lireUn(Long id);

    Trajet modifier(Long id, Trajet trajet);

    String supprimer(Long id);
}
