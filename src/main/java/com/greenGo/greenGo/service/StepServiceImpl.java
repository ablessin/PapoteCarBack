package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.Place;
import com.greenGo.greenGo.modele.Step;
import com.greenGo.greenGo.modele.Trajet;
import com.greenGo.greenGo.repository.StepRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StepServiceImpl implements StepService{
    private final StepRepository stepRepository;

    @Override
    public Step creer(Step step) {
        return stepRepository.save(step);
    }

    @Override
    public List<Step> lire() {
        return stepRepository.findAll();
    }

    @Override
    public List<Step> lireByTrajet(Trajet trajet) {
        return stepRepository.findAllByTrajet(trajet);
    }

    @Override
    public Step lireUn(Long id) {
        return stepRepository.findAllById(id);
    }

    @Override
    public List<Step> lirePlace(Place place) { return stepRepository.findStepByPlace(place);}

    @Override
    public Step modifier(Long id, Step step) {
        return stepRepository.findById(id)
                .map(p-> {
                    p.setPlace(step.getPlace());
                    p.setPosition(step.getPosition());
                    return stepRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Step non trouvé"));
    }

    @Override
    public String supprimer(Long id) {
        stepRepository.deleteById(id);
        return "Step supprimé";
    }
}
