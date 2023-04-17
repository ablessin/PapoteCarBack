package com.greenGo.greenGo.repository;

import com.greenGo.greenGo.modele.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Place findPlaceByAdress(String adress);
    List<Place> findPlaceByNumber(int number);
    List<Place> findPlaceByRegion(String region);
    List<Place> findPlaceByDepartement(String departement);
    List<Place> findPlaceByCity(String city);

    Place findAllById(Long id);
}
