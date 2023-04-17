package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.Place;
import com.greenGo.greenGo.modele.PlaceChamp;
import com.greenGo.greenGo.service.PlaceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/search/{champs}/{value}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Place> read2(@PathVariable String champs, @PathVariable String value) {

        switch (champs) {
            case "city":
                placeService.search(PlaceChamp.city, value);
            case "adress":
                placeService.search(PlaceChamp.adress, value);
            case "number":
                placeService.search(PlaceChamp.number, value);
            case "region":
                placeService.search(PlaceChamp.region, value);
            case "departement":
                placeService.search(PlaceChamp.departement, value);
            default:
                return placeService.lire();
        }
    }

    @GetMapping("/read/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
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
