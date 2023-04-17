package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.Notifications;
import com.greenGo.greenGo.modele.User;

import java.util.List;

public interface NotificationsService {

    Notifications creer(Notifications notification);

    List<Notifications> lire();

    List<Notifications> lireByUser(User user);

    Notifications lireUn(Long id);

    Notifications modifier(Long id, Notifications notifications);


}
