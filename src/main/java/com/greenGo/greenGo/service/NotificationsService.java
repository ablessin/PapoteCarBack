package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.Chat;
import com.greenGo.greenGo.modele.Notifications;
import com.greenGo.greenGo.modele.ObjectPassager;

import java.util.List;
import java.util.Optional;

public interface NotificationsService {

    Notifications creer(Notifications notification);

    List<Notifications> lire();

    Optional<Notifications> lireUn(Long id);

    Notifications modifier(Long id, Notifications notifications);


}
