package com.example.team2.model;

import com.example.team2.model.email.ValidEmail;
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

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @Column(name = "phone")
//    @Check(name = "phone", constraints = "phone ~ '^\\+7 \\(\\d{3}\\) \\d{3}-\\d{2}-\\d{2}$'")
    private String phone;

    @ValidEmail
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "note", nullable = false)
    private String note;

    @Column(name = "organization")
    private String organization;

    @Column(name = "birth_date", nullable = false)
//    @Check(name = "birth_date",
//            constraints = "birth_date <= (CURRENT_DATE - INTERVAL '16 years')")
    private LocalDate birthDate;

    @Column(name = "passport_sery", nullable = false)
//    @Check(name = "passport_sery", constraints = "passport_sery ~ '\\d{4}'")
    private String passportSery;

    @Column(name = "passport_number", nullable = false)
//    @Check(name = "passport_number", constraints = "passport_number ~ '\\d{6}'")
    private String PassportNumber;

    @Column(name = "photo_id", nullable = false)
    private int photo;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    @Column(name = "black_list", nullable = false)
    private boolean blackList;

}
