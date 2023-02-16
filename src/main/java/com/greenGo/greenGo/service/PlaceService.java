package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceService {
    Place creer(Place place);

    List<Place> lire();

    Optional<Place> lireUn(Long id);

    Place modifier(Long id, Place place);

    String supprimer(Long id);
}
