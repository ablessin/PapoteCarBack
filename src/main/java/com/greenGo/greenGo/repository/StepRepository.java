package com.greenGo.greenGo.repository;

import com.greenGo.greenGo.modele.Place;
import com.greenGo.greenGo.modele.Step;
import com.greenGo.greenGo.modele.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StepRepository extends JpaRepository<Step, Long> {
    List<Step> findStepByPlace(Place place);
    List<Step> findStepsByPlaceRegion(String region);
    List<Step> findStepsByPlaceDepartement(String department);
    List<Step> findStepsByPlaceAdress(String address);
    List<Step> findStepsByPlaceCity(String city);
    List<Step> findStepsByPlaceNumber(Integer number);
    Step findAllById(Long id);

    List<Step> findAllByTrajet(Trajet trajet);

}
