package com.greenGo.greenGo.modele;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Table(name = "MESSAGE")
@Getter
@Setter
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @Column(length = 150)
    @NonNull
    private String description;
    @ManyToOne
    @JoinColumn(name="chat_id", nullable=false)
    private Chat chat;
    private Date createdAt;
    private Date updateAt;
}
