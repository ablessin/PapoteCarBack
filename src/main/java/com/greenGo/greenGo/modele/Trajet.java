package com.greenGo.greenGo.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "TRAJET",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "name")
        })
@Getter
@Setter
@NoArgsConstructor
public class Trajet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String name;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="driver", nullable=false)
    private User driver;
    @OneToMany(mappedBy = "trajet")
    private Set<Step> steps;
    @OneToMany(mappedBy = "trajet")
    private Set<ObjectPassager> passagers;
    @OneToMany(mappedBy = "trajet")
    private Set<Chat> chats;
    @Column(nullable = false)
    private Integer placeMax;
    private LocalDate createdAt;
    private LocalDate updateAt;
}
