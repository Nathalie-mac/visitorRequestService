package com.example.team2.uiservice;

import com.example.team2.auth.services.AuthService;
import com.example.team2.dto.LoginDTO;
import com.example.team2.model.StuffRoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class AuthStuffUIService {
     private final AuthService authService;

    // Показ общей формы входа для сотрудников
    public String getSignInStuffForm(Model model) {
        //TODO: отображение формы авторизации
        return null;
    }
    //Обработка данных формы входа менеджера (POST)
    public String postSignInManager(LoginDTO loginDTO) {
        ResponseEntity<?> response = authService.signInStuff(loginDTO, StuffRoleType.MANAGER);
        // TODO: редирект на главную страницу менеджера
        return null;
    }


    //Обработка данных формы входа охранника (POST)
    public String postSignInGuardOfficer(LoginDTO loginDTO) {
        ResponseEntity<?> response = authService.signInStuff(loginDTO, StuffRoleType.GUARD_OFFICER);
        // TODO: редирект на главную страницу охранника
        return null;
    }

}
