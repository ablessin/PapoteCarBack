package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.Step;
import com.greenGo.greenGo.modele.Trajet;
import com.greenGo.greenGo.service.StepService;
import com.greenGo.greenGo.service.TrajetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/step")
@AllArgsConstructor
public class StepController {
    private final StepService stepService;

    @PostMapping("/create")
    public Step create(@RequestBody Step step) {
        return  stepService.creer(step);
    }

    @GetMapping("/read")
    public List<Step> read() {
        return stepService.lire();
    }

    @GetMapping("/read/{id}")
    public Optional<Step> read(@PathVariable Long id) { return stepService.lireUn(id);}

    @PutMapping("/update/{id}")
    public Step update(@PathVariable Long id, @RequestBody Step step) {
        return stepService.modifier(id, step);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return  stepService.supprimer(id);
    }
}
