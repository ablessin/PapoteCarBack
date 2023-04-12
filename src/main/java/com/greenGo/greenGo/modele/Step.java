package com.greenGo.greenGo.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "STEP")
@Getter
@Setter
@NoArgsConstructor
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="place_id", nullable=false)
    private Place place;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "trajet_id", nullable = false)
    private Trajet trajet;
    @Column(nullable = false)
    private Integer position;
    private LocalDate createdAt;
    private LocalDate updateAt;
}
