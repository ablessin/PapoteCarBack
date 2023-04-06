package com.greenGo.greenGo.modele;

import jakarta.persistence.*;
import lombok.*;

import javax.swing.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "USER",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "username")
        })
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 150)
    @NonNull
    private String firstName;
    @Column(length = 150)
    @NonNull
    private String username;
    @Column(length = 150)
    @NonNull
    private String surname;
    @Column(length = 150)
    @NonNull
    private String email;
    @Column(length = 150)
    @NonNull
    private String password;
    @Column(length = 150)
    @NonNull
    private String gender;
    @NonNull
    private String role;
    @OneToMany(mappedBy = "user")
    private Set<Notifications> notifications;
    @OneToMany(mappedBy = "user")
    private Set<ObjectPassager> objectPassager;
    @OneToMany(mappedBy = "user")
    private Set<Message> messages;
//    @OneToMany(mappedBy = "driver")
//    private Set<Trajet> trajets;
//    private ImageIcon profilPicture;
    private Date createdAt;
    private Date updateAt;
}
