package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.ObjectPassager;
import com.greenGo.greenGo.modele.PlaceChamp;
import com.greenGo.greenGo.modele.Trajet;
import com.greenGo.greenGo.modele.User;
import com.greenGo.greenGo.repository.StepRepository;
import com.greenGo.greenGo.repository.TrajetRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class TrajetServiceImpl implements TrajetService{
    private final TrajetRepository trajetRepository;
    private final StepRepository stepRepository;

    @Override
    public Trajet creer(Trajet trajet) {
        return trajetRepository.save(trajet);
    }

    @Override
    public List<Trajet> lire() {
        return trajetRepository.findAll();
    }

    @Override
    public List<Trajet> lireByUser(User user) {
        return trajetRepository.findTrajetsByDriver(user);
    }

    @Override
    public Trajet lireUn(Long id) {
        return trajetRepository.findAllById(id);
    }

    @Override
    public List<Trajet> search(PlaceChamp champ, String value) {
        switch (champ) {
            case city:
                stepRepository.findStepsByPlaceCity(value);
            case adress:
                stepRepository.findStepsByPlaceAdress(value);
            case number:
                stepRepository.findStepsByPlaceNumber(Integer.parseInt(value));
            case region:
                stepRepository.findStepsByPlaceRegion(value);
            case departement:
                stepRepository.findStepsByPlaceDepartement(value);
            default:
                return trajetRepository.findAll();
        }
    }

    @Override
    public Trajet modifier(Long id, Trajet trajet) {
        return trajetRepository.findById(id)
                .map(p-> {
                    p.setName(trajet.getName());
                    p.setDriver(trajet.getDriver());
                    p.setStartDateTime(trajet.getStartDateTime());
                    p.setEndPrevisionalDateTime(trajet.getEndPrevisionalDateTime());
                    p.setPlaceMax(trajet.getPlaceMax());
                    return trajetRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Trajet non trouvé"));
    }

    @Override
    public String supprimer(Long id) {
        trajetRepository.deleteById(id);
        return "Trajet supprimé";
    }

}
