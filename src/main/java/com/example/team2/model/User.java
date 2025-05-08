package com.example.team2.model;

import com.example.team2.model.email.ValidEmail;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ValidEmail
    @Column(name = "user_login")
    private String userLogin;

    @Column(name = "user_password")
    private String userPassword;
}
