package com.example.team2.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "passport_data")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassportData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "passport_id", nullable = false)
    private int passportId;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

}
