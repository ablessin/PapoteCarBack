package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.ActionType;
import com.greenGo.greenGo.modele.Notifications;
import com.greenGo.greenGo.modele.ObjectPassager;
import com.greenGo.greenGo.modele.Trajet;
import com.greenGo.greenGo.service.NotificationsService;
import com.greenGo.greenGo.service.ObjectPassagerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/objectPassager")
@AllArgsConstructor
public class ObjectPassagerController {
    private final ObjectPassagerService objectPassagerService;
    private final NotificationsService notificationsService;

    @PostMapping("/create")
    public ObjectPassager create(@RequestBody ObjectPassager objectPassager) {

            Notifications notifications = new Notifications();
            notifications.setActionType(ActionType.newUserTrajet);
            notifications.setMessage("Un nouveau passager voudrait s'ajoutter à votre trajet " +  objectPassager.getTrajet().getName());
            notifications.setActivate(true);
            LocalDate date = LocalDate.now();
            notifications.setDate(date);
            notifications.setCreatedAt(date);
            notifications.setUpdateAt(date);
            notifications.setUser(objectPassager.getTrajet().getDriver());

        notificationsService.creer(notifications);

        return  objectPassagerService.creer(objectPassager);
    }

    @PostMapping("/accepted")
    public ObjectPassager accepted(@PathVariable Long id, @RequestBody ObjectPassager objectPassager) {

        List<Notifications> list = new ArrayList<>();
        objectPassager.getTrajet().getPassagers().stream().map(item -> {
            Notifications notifications = new Notifications();
            notifications.setActionType(ActionType.validUserTrajet);
            notifications.setMessage("Un nouveau passager a été ajouté au trajet " +  objectPassager.getTrajet().getName());
            notifications.setActivate(true);
            LocalDate date = LocalDate.now();
            notifications.setDate(date);
            notifications.setCreatedAt(date);
            notifications.setUpdateAt(date);
            notifications.setUser(item.getUser());

            return list.add(notifications);
        });
        list.stream().map(item -> notificationsService.creer(item));

        return  objectPassagerService.accepted(id);
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
        Optional<ObjectPassager> objectPassager = objectPassagerService.lireUn(id);
        List<Notifications> list = new ArrayList<>();

        objectPassager.get().getTrajet().getPassagers().stream().map(item -> {
            Notifications notifications = new Notifications();
            notifications.setActionType(ActionType.supUserTrajet);
            notifications.setMessage("L'utilisateur " + objectPassager.get().getUser().getFirstName() + " a été supprimé du trajet " + objectPassager.get().getTrajet().getName());
            notifications.setActivate(true);
            LocalDate date = LocalDate.now();
            notifications.setDate(date);
            notifications.setCreatedAt(date);
            notifications.setUpdateAt(date);
            notifications.setUser(item.getUser());

            return list.add(notifications);
        });
        list.stream().map(item -> notificationsService.creer(item));

        return  objectPassagerService.supprimer(id);
    }
}
