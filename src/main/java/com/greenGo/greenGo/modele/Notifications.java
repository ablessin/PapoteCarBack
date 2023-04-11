package com.greenGo.greenGo.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

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
    @NonNull
    private Boolean activate;
    @NonNull
    private String actionType;
    @NonNull
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @NonNull
    private String message;
    private LocalDate createdAt;
    private LocalDate updateAt;

}

