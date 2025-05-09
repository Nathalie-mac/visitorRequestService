package com.example.team2.service;

import com.example.team2.model.Person;
import com.example.team2.model.Request;
import com.example.team2.dto.VisitorDTO;
import com.example.team2.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person createPerson(VisitorDTO visitorDTO, Request request) {
        Person person = new Person();

        person.setLastName(visitorDTO.getLastName());
        person.setFirstName(visitorDTO.getFirstName());
        person.setMiddleName(visitorDTO.getMiddleName());
        person.setPhone(visitorDTO.getPhoneNumber());
        person.setEmail(visitorDTO.getEmail());
        person.setOrganization(visitorDTO.getOrganizationName());
        person.setNote(visitorDTO.getNote());
        person.setBirthDate(visitorDTO.getBirthDate());
        person.setPassportSery(visitorDTO.getPassportSeries());
        person.setPassportNumber(visitorDTO.getPassportNumber());
        person.setRequest(request);

        return save(person);
    }
}
