package com.example.team2.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "person")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "note")
    private String note;

    @Column(name = "organization")
    private String organization;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "passport_sery")
    private String passportSery;

    @Column(name = "passport_number")
    private String PassportNumber;

    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "passport_pdf")
    private byte[] passportPdf;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

}
