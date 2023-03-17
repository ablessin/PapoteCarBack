package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.Place;
import com.greenGo.greenGo.modele.Step;

import java.util.List;
import java.util.Optional;

public interface StepService {
    Step creer(Step step);

    List<Step> lire();

    Optional<Step> lirePlace(Optional<Place> place);

    Optional<Step> lireUn(Long id);

    Step modifier(Long id, Step step);

    String supprimer(Long id);
}
