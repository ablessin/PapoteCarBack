package com.greenGo.greenGo.service;

import com.greenGo.greenGo.modele.ObjectPassager;
import com.greenGo.greenGo.repository.ObjectPassagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ObjectPassagerServiceImpl implements ObjectPassagerService{
    private final ObjectPassagerRepository objectPassagerRepository;

    @Override
    public ObjectPassager creer(ObjectPassager objectPassager) {
        return objectPassagerRepository.save(objectPassager);
    }

    @Override
    public List<ObjectPassager> lire() {
        return objectPassagerRepository.findAll();
    }

    @Override
    public ObjectPassager lireUn(Long id) {
        return objectPassagerRepository.findAllById(id);
    }

    @Override
    public ObjectPassager modifier(Long id, ObjectPassager objectPassager) {
        return objectPassagerRepository.findById(id)
                .map(p-> {
//                    p.setEnd(objectPassager.getEnd());
                    p.setUser(objectPassager.getUser());
//                    p.setStart(objectPassager.getStart());
                    return objectPassagerRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Place non trouvé"));
    }

    @Override
    public ObjectPassager accepted(Long id) {
        return objectPassagerRepository.findById(id)
                .map(p-> {
                    p.setIsValided(true);
                    return objectPassagerRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Place non trouvé"));
    }

    @Override
    public String supprimer(Long id) {
        objectPassagerRepository.deleteById(id);
        return "Place supprimé";
    }
}
