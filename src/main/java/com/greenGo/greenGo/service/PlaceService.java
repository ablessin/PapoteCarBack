package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.Place;
import com.greenGo.greenGo.modele.PlaceChamp;

import java.util.List;
import java.util.Optional;

public interface PlaceService {
    Place creer(Place place);

    List<Place> lire();

    List<Place> search(PlaceChamp champ, String value);

    Optional<Place> lireUn(Long id);

    Place modifier(Long id, Place place);

    String supprimer(Long id);
}
