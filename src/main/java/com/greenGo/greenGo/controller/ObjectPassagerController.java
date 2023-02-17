package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.ObjectPassager;
import com.greenGo.greenGo.service.ObjectPassagerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/objectPassager")
@AllArgsConstructor
public class ObjectPassagerController {
    private final ObjectPassagerService objectPassagerService;

    @PostMapping("/create")
    public ObjectPassager create(@RequestBody ObjectPassager objectPassager) {
        return  objectPassagerService.creer(objectPassager);
    }

    @GetMapping("/read")
    public List<ObjectPassager> read() {
        return objectPassagerService.lire();
    }

    @GetMapping("/read/{id}")
    public Optional<ObjectPassager> read(@PathVariable Long id) { return objectPassagerService.lireUn(id);}

    @PutMapping("/update/{id}")
    public ObjectPassager update(@PathVariable Long id, @RequestBody ObjectPassager objectPassager) {
        return objectPassagerService.modifier(id, objectPassager);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return  objectPassagerService.supprimer(id);
    }
}
