package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.*;
import com.greenGo.greenGo.service.NotificationsService;
import com.greenGo.greenGo.service.ObjectPassagerService;
import com.greenGo.greenGo.service.TrajetService;
import com.greenGo.greenGo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/trajet")
@AllArgsConstructor
public class TrajetController {
    private final TrajetService trajetService;
    private final NotificationsService notificationsService;
    private final UserService userService;
    private final ObjectPassagerService objectPassagerService;

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:3000")
    public Trajet create(@RequestBody Trajet trajet) {
        return  trajetService.creer(trajet);
    }

    @GetMapping("/read")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Trajet> read() {
        return trajetService.lire();
    }

    @PostMapping("/read/isPossible/user/{userId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Boolean readPossibility(@RequestBody Trajet trajet, @PathVariable Long userId) {
        User user = userService.lireUn(userId);
        if (user.getTrajets().size() > 0) {
            for (Trajet entry: user.getTrajets()) {
                Long hours = getDHoursBetween(entry.getStartDateTime(), entry.getEndPrevisionalDateTime());

                for (int i = 0; i <= hours; i++) {
                    LocalDateTime startDate = entry.getStartDateTime().minusHours(2);
                    startDate = startDate.plusHours(i);
                    if (startDate.getHour() == trajet.getStartDateTime().getHour()) {
                        return false;
                    } else if (startDate.getHour() == trajet.getEndPrevisionalDateTime().getHour()) {
                        return false;
                    }
                }
            }
        }
        List<ObjectPassager> objectPassagers = objectPassagerService.lireByUser(user);

        if (objectPassagers.size() > 0) {
            for (ObjectPassager objectPassager: objectPassagers) {
                Trajet trajet1 = objectPassager.getTrajet();

                Long hours = getDHoursBetween(trajet1.getStartDateTime(), trajet1.getEndPrevisionalDateTime());

                for (int i = 0; i <= hours; i++) {
                    LocalDateTime startDate = trajet1.getStartDateTime().minusHours(2);
                    startDate = startDate.plusHours(i);
                    if (startDate.getHour() == trajet.getStartDateTime().getHour()) {
                        return false;
                    }
                    if (startDate.getHour() == trajet.getEndPrevisionalDateTime().getHour()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @GetMapping("/read/user/{userId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Trajet> readByUser(@PathVariable Long userId) {
        User user = userService.lireUn(userId);
        return trajetService.lireByUser(user);
    }

    @GetMapping("/read/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Trajet read(@PathVariable Long id) {
         Trajet trajet = trajetService.lireUn(id);
         log.warn(trajet.toString());
        return trajet;
    }

    @GetMapping("/search/{champs}/{value}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Trajet> read2(@PathVariable String champs, @PathVariable String value) {

        switch (champs) {
            case "city":
                trajetService.search(PlaceChamp.city, value);
            case "adress":
                trajetService.search(PlaceChamp.adress, value);
            case "number":
                trajetService.search(PlaceChamp.number, value);
            case "region":
                trajetService.search(PlaceChamp.region, value);
            case "departement":
                trajetService.search(PlaceChamp.departement, value);
            default:
                return trajetService.lire();
        }

    }

    @PutMapping("/update/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Trajet update(@PathVariable Long id, @RequestBody Trajet trajet) {
        return trajetService.modifier(id, trajet);
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
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

        for (Notifications notifications: list) {
            notificationsService.creer(notifications);
        }

        return  trajetService.supprimer(id);
    }

    public static long getDHoursBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return (endDate.getHour() - startDate.getHour());
    }
}
