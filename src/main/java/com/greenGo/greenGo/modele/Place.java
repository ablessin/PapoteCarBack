package com.greenGo.greenGo.modele;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String name;
    @Column(length = 150)
    private String departement;
    @Column(length = 150)
    private String region;
    @ManyToOne
    @JoinColumn(name="step_id", nullable=false)
    private Step step;

    @ManyToOne
    @JoinColumn(name="objectpassager_start_id", nullable=false)
    private ObjectPassager start;

    @ManyToOne
    @JoinColumn(name="objectpassager_end_id", nullable=false)
    private ObjectPassager end;
}
