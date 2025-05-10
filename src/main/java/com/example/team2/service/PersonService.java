package com.example.team2.service;

import com.example.team2.dto.BlyadskoeFioDTO;
import com.example.team2.model.Person;
import com.example.team2.model.Request;
import com.example.team2.dto.VisitorDTO;
import com.example.team2.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

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

    public Person getPerson(Long id) {
        return personRepository.findById(id).orElse(null);
    }


    public List<String> getPersonFiosInRequest(Long id){
        List<BlyadskoeFioDTO> rawFios =  personRepository.findNameByRequest(id);
        return rawFios.stream()
                .map(dto -> String.join(" ", dto.lastName(), dto.firstName(), dto.middleName()))
                .toList();
    }
}
