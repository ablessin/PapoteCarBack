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
@Table(name = "OBJECTPASSAGER")
@Getter
@Setter
@NoArgsConstructor
public class ObjectPassager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @ManyToOne
    @JoinColumn(name="trajet_id", nullable=false)
    private Trajet trajet;
    @ManyToOne
    @JoinColumn(name="start", nullable=false)
    private Place start;
    @ManyToOne
    @JoinColumn(name="end", nullable=false)
    private Place end;
    private Date createdAt;
    private Date updateAt;
}
