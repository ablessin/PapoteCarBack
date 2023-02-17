package com.greenGo.greenGo.modele;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @OneToMany(mappedBy = "step")
    private Set<Place> place;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trajet_id", referencedColumnName = "id")
    private Trajet trajet;
    private Integer position;
}
