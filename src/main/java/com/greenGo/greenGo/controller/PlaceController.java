package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.Place;
import com.greenGo.greenGo.modele.Trajet;
import com.greenGo.greenGo.service.PlaceService;
import com.greenGo.greenGo.service.TrajetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/place")
@AllArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @PostMapping("/create")
    public Place create(@RequestBody Place place) {
        return  placeService.creer(place);
    }

    @GetMapping("/read")
    public List<Place> read() {
        return placeService.lire();
    }

    @GetMapping("/read/{id}")
    public Optional<Place> read(@PathVariable Long id) { return placeService.lireUn(id);}

    @PutMapping("/update/{id}")
    public Place update(@PathVariable Long id, @RequestBody Place place) {
        return placeService.modifier(id, place);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return placeService.supprimer(id);
    }
}
