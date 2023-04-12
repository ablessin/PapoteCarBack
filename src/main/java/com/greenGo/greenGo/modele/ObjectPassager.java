package com.greenGo.greenGo.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="trajet_id", nullable=false)
    private Trajet trajet;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="start", nullable=false)
    private Place start;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="end", nullable=false)
    private Place end;
    private Date createdAt;
    private Date updateAt;
    private Boolean isValided;
}
