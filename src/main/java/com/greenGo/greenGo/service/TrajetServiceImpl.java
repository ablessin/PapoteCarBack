package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.Trajet;
import com.greenGo.greenGo.repository.TrajetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TrajetServiceImpl implements TrajetService{
    private final TrajetRepository trajetRepository;

    @Override
    public Trajet creer(Trajet trajet) {
        return trajetRepository.save(trajet);
    }

    @Override
    public List<Trajet> lire() {
        return trajetRepository.findAll();
    }

    @Override
    public Optional<Trajet> lireUn(Long id) {
        return trajetRepository.findById(id);
    }

    @Override
    public Trajet modifier(Long id, Trajet trajet) {
        return trajetRepository.findById(id)
                .map(p-> {
                    p.setName(trajet.getName());
                    p.setDriver(trajet.getDriver());
                    p.setPassagers(trajet.getPassagers());
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
