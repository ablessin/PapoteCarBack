package com.greenGo.greenGo.modele;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="place_id", nullable=false)
    private Place place;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "trajet_id", nullable = false)
    private Trajet trajet;
    @Column(nullable = false)
    private Integer position;
    private LocalDate createdAt;
    private LocalDate updateAt;
}
