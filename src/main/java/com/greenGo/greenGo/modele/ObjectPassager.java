package com.greenGo.greenGo.modele;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Data
@Table(name = "OBJECTPASSAGER")
@Getter
@Setter
@NoArgsConstructor
public class ObjectPassager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user;

    @OneToMany(mappedBy = "start")
    public Set<Place> start;

    @OneToMany(mappedBy = "end")
    private Set<Place> end;

}
