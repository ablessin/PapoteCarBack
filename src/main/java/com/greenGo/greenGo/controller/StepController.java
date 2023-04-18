package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.Chat;
import com.greenGo.greenGo.modele.Place;
import com.greenGo.greenGo.modele.Step;
import com.greenGo.greenGo.modele.Trajet;
import com.greenGo.greenGo.service.PlaceService;
import com.greenGo.greenGo.service.StepService;
import com.greenGo.greenGo.service.TrajetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/step")
@AllArgsConstructor
public class StepController {
    private final StepService stepService;
    private final PlaceService placeService;
    private final TrajetService trajetService;

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:3000")
    public Step create(@RequestBody Step step) {
        return  stepService.creer(step);
    }

    @GetMapping("/read")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Step> read() {
        return stepService.lire();
    }

    @GetMapping("/read/trajet/{trajetId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Step> readByTrajet(@PathVariable Long trajetId) {
        Trajet trajet = trajetService.lireUn(trajetId);
        return stepService.lireByTrajet(trajet);
    }

    @GetMapping("/read/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Step read(@PathVariable Long id) { return stepService.lireUn(id);}

    @GetMapping("read/bestSearch")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Step> readGroupByPlace() {
        List<Step> stepList = new ArrayList<>();
        List<Step> steps = stepService.lire();

        Map<Place, List<Step>> groups = steps.stream()
                .collect(Collectors.groupingBy(Step::getPlace));

        for (Map.Entry<Place, List<Step>> entry: groups.entrySet()) {
            for (Step value: entry.getValue()) {
                Step step = stepService.lireUn(value.getId());
                stepList.add(step);
            }
        }
        return stepList;
    }
    @GetMapping("/read/{placeId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Step> readPlace(@PathVariable Long placeId) {
        Place place = placeService.lireUn(placeId);
        return stepService.lirePlace(place);
    }
    @PutMapping("/update/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Step update(@PathVariable Long id, @RequestBody Step step) {
        return stepService.modifier(id, step);
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public String delete(@PathVariable Long id) {
        return  stepService.supprimer(id);
    }
}
