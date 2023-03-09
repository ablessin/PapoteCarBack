package com.greenGo.greenGo.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "NOTIFICATIONS")
@Getter
@Setter
@NoArgsConstructor
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private Boolean activate;
    private String actionType;
    private Date date;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    private String message;
    private Date createdAt;
    private Date updateAt;

}

