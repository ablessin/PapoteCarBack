package com.greenGo.greenGo.repository;

import com.greenGo.greenGo.modele.Place;
import com.greenGo.greenGo.modele.Step;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StepRepository extends JpaRepository<Step, Long> {
    Step findStepByPlace(Place place);

    Step findAllById(Long id);
}
