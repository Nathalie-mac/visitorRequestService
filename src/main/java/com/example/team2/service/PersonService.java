package com.example.team2.service;

import com.example.team2.model.Person;
import com.example.team2.model.Request;
import com.example.team2.dto.VisitorDTO;
import com.example.team2.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public boolean isAnyPersonInBlackList(Request request) {
        return personRepository.findDistinctBlackListByRequest(request).contains(true);
    }

    public List<Person> findByRequest(Request request) {
        return personRepository.findByRequest(request);
    }

    public List<VisitorDTO> getVisitorsDTOs(Request request) { //TODO: изменить visitor на person
        List<VisitorDTO> visitorDTOS = new ArrayList<>();
        List<Person> persons = findByRequest(request);

        for (Person person : persons) {
            VisitorDTO visitorDTO = new VisitorDTO();

            visitorDTO.setFirstName(person.getFirstName());
            visitorDTO.setLastName(person.getLastName());
            visitorDTO.setMiddleName(person.getMiddleName());
            visitorDTO.setPhoneNumber(person.getPhone());
            visitorDTO.setEmail(person.getEmail());
            visitorDTO.setOrganizationName(person.getOrganization());
            visitorDTO.setNote(person.getNote());
            visitorDTO.setBirthDate(person.getBirthDate());
            visitorDTO.setPassportSeries(person.getPassportSery());
            visitorDTO.setPassportNumber(person.getPassportNumber());
            visitorDTO.setPhoto(person.getPhoto());

            visitorDTOS.add(visitorDTO);
        }

        return visitorDTOS;
    }
}
