package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.Trajet;
import com.greenGo.greenGo.service.TrajetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trajet")
@AllArgsConstructor
public class TrajetController {
    private final TrajetService trajetService;

    @PostMapping("/create")
    public Trajet create(@RequestBody Trajet trajet) {
        return  trajetService.creer(trajet);
    }

    @GetMapping("/read")
    public List<Trajet> read() {
        return trajetService.lire();
    }

    @GetMapping("/read/{id}")
    public Optional<Trajet> read(@PathVariable Long id) { return trajetService.lireUn(id);}

    @PutMapping("/update/{id}")
    public Trajet update(@PathVariable Long id, @RequestBody Trajet trajet) {
        return trajetService.modifier(id, trajet);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return  trajetService.supprimer(id);
    }
}
