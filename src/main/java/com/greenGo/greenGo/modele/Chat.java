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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trajet_id", referencedColumnName = "id")
    private Trajet trajet;

    private Date createdAt;
    private Date updateAt;
}
