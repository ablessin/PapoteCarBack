package com.greenGo.greenGo.repository;

import com.greenGo.greenGo.modele.Place;
import com.greenGo.greenGo.modele.Step;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StepRepository extends JpaRepository<Step, Long> {
    Step findStepByPlace(Place place);
    Step findStepsByPlaceRegion(String region);
    Step findStepsByPlaceDepartement(String department);
    Step findStepsByPlaceAdress(String address);
    Step findStepsByPlaceCity(String city);
    Step findStepsByPlaceNumber(Integer number);
    Step findAllById(Long id);
}
