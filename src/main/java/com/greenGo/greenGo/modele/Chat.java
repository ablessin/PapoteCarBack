package com.greenGo.greenGo.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "CHAT")
@Getter
@Setter
@NoArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "chat")
    private Set<Message> messages;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="place_id", nullable=false)
    private Trajet trajet;
    private LocalDate createdAt;
    private LocalDate updateAt;
}
