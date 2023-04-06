package com.greenGo.greenGo.repository;

import com.greenGo.greenGo.modele.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findPlaceByAdress(String adress);
    Optional<Place> findPlaceByNumber(int number);
    Optional<Place> findPlaceByRegion(String region);
    Optional<Place> findPlaceByDepartement(String departement);
    Optional<Place> findPlaceByCity(String city);

}
