package com.greenGo.greenGo.modele;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="trajet_id", nullable=false)
    private Trajet trajet;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="start", nullable=false)
    private Place start;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="end", nullable=false)
    private Place end;
    private Date createdAt;
    private Date updateAt;
    @Column(nullable = false)
    private Boolean isValided;
}
