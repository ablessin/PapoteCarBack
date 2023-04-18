package com.greenGo.greenGo.modele;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Column(length = 50, nullable=false)
    private String name;

    @Column(nullable=false)
    private LocalDateTime startDateTime;

    @Column(nullable=false)
    private LocalDate endPrevisionalDateTime;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
