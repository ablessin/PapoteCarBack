package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.Place;
import com.greenGo.greenGo.modele.Step;
import com.greenGo.greenGo.service.PlaceService;
import com.greenGo.greenGo.service.StepService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/step")
@AllArgsConstructor
public class StepController {
    private final StepService stepService;
    private final PlaceService placeService;

    @PostMapping("/create")
    public Step create(@RequestBody Step step) {
        return  stepService.creer(step);
    }

    @GetMapping("/read")
    public List<Step> read() {
        return stepService.lire();
    }

    @GetMapping("/read/{id}")
    public Step read(@PathVariable Long id) { return stepService.lireUn(id);}

    @GetMapping("read/bestSearch")
    public Map<Place, List<Step>> readGroupByPlace() {
        List<Step> steps = stepService.lire();
        Map<Place, List<Step>> groups = steps.stream()
                .collect(Collectors.groupingBy(Step::getPlace));

        return groups;
    }
    @GetMapping("/read/{placeId}")
    public Step readPlace(@PathVariable Long placeId) {
        Place place = placeService.lireUn(placeId);
        return stepService.lirePlace(place);
    }
    @PutMapping("/update/{id}")
    public Step update(@PathVariable Long id, @RequestBody Step step) {
        return stepService.modifier(id, step);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return  stepService.supprimer(id);
    }
}
