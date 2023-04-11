package com.greenGo.greenGo.controller;

import com.greenGo.greenGo.modele.*;
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

        Boolean canCreate = verifNbPlace(objectPassager.getTrajet(), objectPassager);

        if (canCreate) {
            Notifications notifications = new Notifications();
            notifications.setActionType(ActionType.newUserTrajet.toString());
            notifications.setMessage("Un nouveau passager voudrait s'ajoutter à votre trajet " +
                    objectPassager.getTrajet().getName());
            notifications.setActivate(true);
            LocalDate date = LocalDate.now();
            notifications.setDate(date);
            notifications.setCreatedAt(date);
            notifications.setUpdateAt(date);
            notifications.setUser(objectPassager.getTrajet().getDriver());

            notificationsService.creer(notifications);

            return objectPassagerService.creer(objectPassager);
        }
        return null;
    }

    @PostMapping("/accepted/{id}")
    public ObjectPassager accepted(@PathVariable Long id, @RequestBody ObjectPassager objectPassager) {

        List<Notifications> list = new ArrayList<>();
        objectPassager.getTrajet().getPassagers().stream().map(item -> {
            Notifications notifications = new Notifications();
            notifications.setActionType(ActionType.validUserTrajet.toString());
            notifications.setMessage("Un nouveau passager a été ajouté au trajet " +
                    objectPassager.getTrajet().getName());
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
            notifications.setActionType(ActionType.supUserTrajet.toString());
            notifications.setMessage("L'utilisateur " + objectPassager.get().getUser().getFirstName() +
                    " a été supprimé du trajet " + objectPassager.get().getTrajet().getName());
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

    public Boolean verifNbPlace(Trajet trajet, ObjectPassager newPassager) {
        List<Step> stepsList = trajet.getSteps().stream().toList();
        List<ObjectPassager> passager = trajet.getPassagers().stream().toList();

        List<ObjectPlace> objectPlaces = new ArrayList();

        stepsList.stream().map(item -> {
            ObjectPlace object = new ObjectPlace();
            object.setPosition(item.getPosition());
            object.setNbPlace(0);

            return objectPlaces.add(object);
        });

        for (ObjectPassager item: passager) {
            Long startPlaceId = item.getStart().getId();
            Long endPlaceId = item.getEnd().getId();

            int positionStart = stepsList.stream().filter(x -> x.getPlace().getId().equals(startPlaceId)).findFirst().get().getPosition();
            int positionEnd = stepsList.stream().filter(x -> x.getPlace().getId().equals(endPlaceId)).findFirst().get().getPosition();

            addPersonne(positionStart, positionEnd, objectPlaces, trajet);
//            objectPlaces.clear();
//            places.stream().map(y -> objectPlaces.add(y));
        }

         int start = stepsList.stream().filter(item -> item.getPlace().equals(newPassager.getStart())).findFirst().get().getPosition();
         int end = stepsList.stream().filter(item -> item.getPlace().equals(newPassager.getStart())).findFirst().get().getPosition();

        return addPersonne(start, end, objectPlaces, trajet);
    }

    public Boolean addPersonne(int start, int end, List<ObjectPlace> objectPlaces, Trajet trajet) {
        for (int i = start; i < end; i++) {
            ObjectPlace objectPlace = objectPlaces.stream().filter(y -> y.getPosition().equals(i)).findFirst().get();
            Integer nbPlace = objectPlace.getNbPlace();

            if (nbPlace < trajet.getPlaceMax()) {
                objectPlace.setNbPlace(objectPlace.getNbPlace() + 1);
                return true;
            } else {
                return false;
            }
        }
        return null;
    }
}
