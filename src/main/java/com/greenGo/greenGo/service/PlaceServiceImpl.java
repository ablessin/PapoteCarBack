package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.Chat;
import com.greenGo.greenGo.modele.Place;
import com.greenGo.greenGo.modele.PlaceChamp;
import com.greenGo.greenGo.repository.ChatRepository;
import com.greenGo.greenGo.repository.PlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlaceServiceImpl implements PlaceService{
    private final PlaceRepository placeRepository;

    @Override
    public Place creer(Place place) {
        return placeRepository.save(place);
    }

    @Override
    public List<Place> lire() {
        return placeRepository.findAll();
    }

    @Override
    public List<Place> search(PlaceChamp champ, String value) {
        switch (champ) {
            case city:
                placeRepository.findPlaceByCity(value);
            case adress:
                placeRepository.findPlaceByAdress(value);
            case number:
                placeRepository.findPlaceByNumber(Integer.parseInt(value));
            case region:
                placeRepository.findPlaceByRegion(value);
            case departement:
                placeRepository.findPlaceByDepartement(value);
            default:
                return placeRepository.findAll();
        }
    }

    @Override
    public Optional<Place> lireUn(Long id) {
        return placeRepository.findById(id);
    }

    @Override
    public Place modifier(Long id, Place place) {
        return placeRepository.findById(id)
                .map(p-> {
                    p.setDepartement(place.getDepartement());
                    p.setAdress(place.getAdress());
                    p.setNumber(place.getNumber());
                    p.setCity(place.getCity());
                    p.setRegion(place.getRegion());
                    return placeRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Place non trouvé"));
    }

    @Override
    public String supprimer(Long id) {
        placeRepository.deleteById(id);
        return "Place supprimé";
    }
}
