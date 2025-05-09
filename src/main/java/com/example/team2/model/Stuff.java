package com.example.team2.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stuff")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stuff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "stuff_code")
    private String userCode;

    @Column(name = "stuff_password")
    private String stuffPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "stuff_role", nullable = false)
    private StuffRoleType stuffRole;

}
