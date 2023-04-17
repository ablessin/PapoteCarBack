package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.Notifications;
import com.greenGo.greenGo.modele.User;
import com.greenGo.greenGo.repository.NotificationsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationsImpl implements NotificationsService{
    private final NotificationsRepository notificationsRepository;

    @Override
    public Notifications creer(Notifications notification) {
        return notificationsRepository.save(notification);
    }

    @Override
    public List<Notifications> lire() {
        return notificationsRepository.findAll();
    }

    @Override
    public List<Notifications> lireByUser(User user) {
        return notificationsRepository.findAllByUser(user);
    }

    @Override
    public Notifications lireUn(Long id) {
        return notificationsRepository.findAllById(id);
    }

    @Override
    public Notifications modifier(Long id, Notifications notification) {
        return notificationsRepository.findById(id)
                .map(p-> {
                    p.setActivate(notification.getActivate());
                    return notificationsRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("notification non trouvé"));
    }



}
