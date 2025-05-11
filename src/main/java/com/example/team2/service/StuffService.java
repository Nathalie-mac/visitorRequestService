package com.example.team2.service;

import com.example.team2.exceptions.data.ExistingUserWithThatUsernameException;
import com.example.team2.exceptions.data.UserNotFoundException;
import com.example.team2.model.Stuff;
import com.example.team2.model.StuffRoleType;
import com.example.team2.repository.StuffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StuffService {
    private final StuffRepository stuffRepository;

    private Stuff save(Stuff stuff) {
        return stuffRepository.save(stuff);
    }

    public Stuff createStuff(String code, String password, StuffRoleType stuffRole) {

        if (stuffRepository.findStuffByUserCodeAndStuffRole(code, stuffRole) != null) {
            throw new ExistingUserWithThatUsernameException();
        }
        Stuff stuff = new Stuff();
        stuff.setUserCode(code);
        stuff.setStuffPassword(password);
        stuff.setStuffRole(stuffRole);

        return save(stuff);
    }


    public Stuff findStuffByCodeAndRole(String code, StuffRoleType stuffRole) {
        Stuff findStuff = stuffRepository.findStuffByUserCodeAndStuffRole(code, stuffRole);
        if (findStuff == null) {
            throw new UserNotFoundException();
        }
        return stuffRepository.findStuffByUserCodeAndStuffRole(code, stuffRole);
    }
}
