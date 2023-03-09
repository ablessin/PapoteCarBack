package com.greenGo.greenGo.modele;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(name="place_id", nullable=false)
    private Place place;
    @ManyToOne
    @JoinColumn(name = "trajet_id", nullable = false)
    private Trajet trajet;
    private Integer position;
    private Date createdAt;
    private Date updateAt;
}
