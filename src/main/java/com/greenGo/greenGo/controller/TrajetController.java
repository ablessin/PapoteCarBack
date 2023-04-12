package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.*;
import com.greenGo.greenGo.service.NotificationsService;
import com.greenGo.greenGo.service.TrajetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/trajet")
@AllArgsConstructor
public class TrajetController {
    private final TrajetService trajetService;
    private final NotificationsService notificationsService;

    @PostMapping("/create")
    public Trajet create(@RequestBody Trajet trajet) {
        return  trajetService.creer(trajet);
    }

    @GetMapping("/read")
    public List<Trajet> read() {
        return trajetService.lire();
    }

    @GetMapping("/read/{id}")
    public Trajet read(@PathVariable Long id) {
         Trajet trajet = trajetService.lireUn(id);
         log.warn(trajet.toString());
        return trajet;
    }

    @PutMapping("/update/{id}")
    public Trajet update(@PathVariable Long id, @RequestBody Trajet trajet) {
        return trajetService.modifier(id, trajet);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        List<Notifications> list = new ArrayList<>();

        Trajet trajet = trajetService.lireUn(id);
        Set<ObjectPassager> userList = trajet.getPassagers();

        for (ObjectPassager objectPassager: userList) {
            Notifications notifications = new Notifications();

            notifications.setActionType(ActionType.supTrajet.toString());
            notifications.setMessage("Le chat du trajet " + trajet.getName() + " a été supprimé");
            notifications.setActivate(true);
            LocalDate date = LocalDate.now();
            notifications.setDate(date);
            notifications.setCreatedAt(date);
            notifications.setUpdateAt(date);
            notifications.setUser(objectPassager.getUser());

            list.add(notifications);
        }

        list.stream().map(item -> notificationsService.creer(item));

        return  trajetService.supprimer(id);
    }
}
