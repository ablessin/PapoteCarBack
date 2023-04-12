package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.Place;
import com.greenGo.greenGo.modele.PlaceChamp;
import com.greenGo.greenGo.service.PlaceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/place")
@AllArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @PostMapping("/create")
    public Place create(@RequestBody Place place) {
        return  placeService.creer(place);
    }

    @GetMapping("/read/{champs}")
    public List<Place> read(@PathVariable String champs) {
    log.warn(champs.toString());
//        switch (champs) {
//            case city:
//                    placeService.search(PlaceChamp.city, value);
//            case adress:
//                placeService.search(PlaceChamp.adress, value);
//            case number:
//                placeService.search(PlaceChamp.number, value);
//            case region:
//                placeService.search(PlaceChamp.region, value);
//            case departement:
//                placeService.search(PlaceChamp.departement, value);
//            default:
//                return placeService.lire();
//        }

        return placeService.lire();
    }

    @GetMapping("/read/{id}")
    public Place read(@PathVariable Long id) { return placeService.lireUn(id);}

    @PutMapping("/update/{id}")
    public Place update(@PathVariable Long id, @RequestBody Place place) {
        return placeService.modifier(id, place);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return placeService.supprimer(id);
    }
}
