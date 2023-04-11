package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.*;
import com.greenGo.greenGo.service.NotificationsService;
import com.greenGo.greenGo.service.TrajetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Trajet> read(@PathVariable Long id) { return trajetService.lireUn(id);}

    @PutMapping("/update/{id}")
    public Trajet update(@PathVariable Long id, @RequestBody Trajet trajet) {
        return trajetService.modifier(id, trajet);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Optional<Trajet> trajet = trajetService.lireUn(id);
//        Optional<User> user = userService.lireUn()
        List<Notifications> list = new ArrayList<>();

        Notifications notifications = new Notifications();
        notifications.setActionType(ActionType.supTrajet.toString());
        notifications.setMessage("Le chat du trajet " + trajet.get().getName() + " a été supprimé");
        notifications.setActivate(true);
        LocalDate date = LocalDate.now();
        notifications.setDate(date);
        notifications.setCreatedAt(date);
        notifications.setUpdateAt(date);
        notifications.setUser(item.getUser());

            list.add(notifications);
        list.stream().map(item -> notificationsService.creer(item));

        return  trajetService.supprimer(id);
    }
}
