package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.Place;
import com.greenGo.greenGo.modele.Step;
import com.greenGo.greenGo.modele.Trajet;

import java.util.List;

public interface StepService {
    Step creer(Step step);

    List<Step> lire();

    List<Step> lirePlace(Place place);

    List<Step> lireByTrajet(Trajet trajet);

    Step lireUn(Long id);

    Step modifier(Long id, Step step);

    String supprimer(Long id);
}
