package com.greenGo.greenGo.modele;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    @Column(length = 50, nullable = false)
    private Boolean activate;
    @Column(nullable = false)
    private String actionType;
    @Column(nullable = false)
    private LocalDate date;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @Column(nullable = false)
    private String message;
    private LocalDate createdAt;
    private LocalDate updateAt;

}

