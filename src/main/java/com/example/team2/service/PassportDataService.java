package com.example.team2.service;

import com.example.team2.model.PassportData;
import com.example.team2.model.Request;
import com.example.team2.repository.PassportDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassportDataService {
    private final PassportDataRepository passportDataRepository;

    public PassportData save(PassportData passportData) {
        return passportDataRepository.save(passportData);
    }

    public PassportData createPassportData(Integer passportId, Request request) {
        PassportData passportData = new PassportData();

        passportData.setPassportId(passportId);
        passportData.setRequest(request);

        return save(passportData);
    }

    public List<Integer> findPassportIdByRequest(Request request) {
        List<PassportData> passportData = passportDataRepository.findPassportIdByRequest(request);
        return passportData.stream().map(PassportData::getPassportId).toList();
    }
}
