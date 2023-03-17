package com.greenGo.greenGo.modele;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "PLACE")
@Getter
@Setter
@NoArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 150)
    private String city;
    @Column(length = 150)
    private String adress;
    @Column(length = 150)
    private String number;
    @Column(length = 150)
    private String departement;
    @Column(length = 150)
    private String region;
    @OneToMany(mappedBy = "place")
    private Set<Step> step;
    @OneToMany(mappedBy = "start")
    private Set<ObjectPassager> start;
    @OneToMany(mappedBy = "end")
    private Set<ObjectPassager> end;
    private LocalDate createdAt;
    private LocalDate updateAt;
}
