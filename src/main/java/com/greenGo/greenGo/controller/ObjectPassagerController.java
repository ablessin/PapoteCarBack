package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.*;
import com.greenGo.greenGo.service.NotificationsService;
import com.greenGo.greenGo.service.ObjectPassagerService;
import com.greenGo.greenGo.service.TrajetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/objectPassager")
@AllArgsConstructor
public class ObjectPassagerController {
    private final ObjectPassagerService objectPassagerService;
    private final NotificationsService notificationsService;
    private final TrajetService trajetService;

    @PostMapping("/create")
    public ObjectPassager create(@RequestBody ObjectPassager objectPassager) {
        Trajet trajet = trajetService.lireUn(objectPassager.getTrajet().getId());
        Boolean canCreate = verifNbPlace(trajet, objectPassager);

        if (canCreate) {
            return objectPassagerService.creer(objectPassager);
        }
        return null;
    }

    @PutMapping("/accepted/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ObjectPassager accepted(@PathVariable Long id) {
        ObjectPassager objectPassager = objectPassagerService.lireUn(id);

        List<Notifications> list = new ArrayList<>();
        Trajet trajet = objectPassager.getTrajet();
        Set<ObjectPassager> objectPassagers = trajet.getPassagers();

        for (ObjectPassager user: objectPassagers) {
            if (user.getIsValided()) {
                Notifications notifications = new Notifications();
                notifications.setActionType(ActionType.validUserTrajet.toString());
                notifications.setMessage("Un nouveau passager a été ajouté au trajet " +
                        trajet.getName());
                notifications.setActivate(true);
                LocalDate date = LocalDate.now();
                notifications.setDate(date);
                notifications.setCreatedAt(date);
                notifications.setUpdateAt(date);
                notifications.setUser(user.getUser());

                list.add(notifications);
            }
        }

        list.stream().map(item -> notificationsService.creer(item));

        return  objectPassagerService.accepted(id);
    }

    @GetMapping("/read")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<ObjectPassager> read() {
        return objectPassagerService.lire();
    }

    @GetMapping("/read/trajet/{trajetId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<ObjectPassager> readByTrajet(@PathVariable Long trajetId) {
        return objectPassagerService.lireByTrajet(trajetId);
    }

    @GetMapping("/read/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ObjectPassager read(@PathVariable Long id) {
        return objectPassagerService.lireUn(id);
    }

    @PutMapping("/update/{id}")
    public ObjectPassager update(@PathVariable Long id, @RequestBody ObjectPassager objectPassager) {
        return objectPassagerService.modifier(id, objectPassager);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        List<Notifications> list = new ArrayList<>();

        ObjectPassager objectPassager = objectPassagerService.lireUn(id);

        Trajet trajet = objectPassager.getTrajet();
        Set<ObjectPassager> objectPassagers = trajet.getPassagers();

        for (ObjectPassager user: objectPassagers) {
            Notifications notifications = new Notifications();

            notifications.setActionType(ActionType.supUserTrajet.toString());
            notifications.setMessage("L'utilisateur " + user.getUser().getFirstName() +
                    " a été supprimé du trajet " + trajet.getName());
            notifications.setActivate(true);
            LocalDate date = LocalDate.now();
            notifications.setDate(date);
            notifications.setCreatedAt(date);
            notifications.setUpdateAt(date);
            notifications.setUser(user.getUser());

            list.add(notifications);
        }

        list.stream().map(item -> notificationsService.creer(item));

        return  objectPassagerService.supprimer(id);
    }

    public Boolean verifNbPlace(Trajet trajet, ObjectPassager newPassager) {
        Place start = newPassager.getStart();
        Place end = newPassager.getEnd();

        int nbPlace = trajet.getPlaceMax();

        int countStart = objectPassagerService.countStart(start, trajet);
        int countEnd = objectPassagerService.countEnd(end, trajet);

        if (countStart < nbPlace && countEnd < nbPlace) {
            return true;
        } else {
            return false;
        }
    }
}
