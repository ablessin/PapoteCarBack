package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.Message;
import com.greenGo.greenGo.modele.Notifications;
import com.greenGo.greenGo.repository.NotificationsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Notifications> lireUn(Long id) {
        return notificationsRepository.findById(id);
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
