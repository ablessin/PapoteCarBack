package com.greenGo.greenGo.repository;

import com.greenGo.greenGo.modele.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Place findPlaceByAdress(String adress);
    Place findPlaceByNumber(int number);
    Place findPlaceByRegion(String region);
    Place findPlaceByDepartement(String departement);
    Place findPlaceByCity(String city);

    Place findAllById(Long id);
}
