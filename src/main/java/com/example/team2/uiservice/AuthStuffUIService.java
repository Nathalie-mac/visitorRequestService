package com.example.team2.uiservice;

import com.example.team2.auth.services.AuthService;
import com.example.team2.auth.services.CustomResponse;
import com.example.team2.dto.LoginDTO;
import com.example.team2.dto.request.FilterListDTO;
import com.example.team2.dto.request.RequestsTableDTO;
import com.example.team2.model.StuffRoleType;
import com.example.team2.service.GuardRequestService;
import com.example.team2.service.ManagerConfirmationService;
import com.example.team2.service.RequestService;
import com.example.team2.uiservice.provider.SessionCookieProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class AuthStuffUIService {
    private final AuthService authService;
    private final ManagerConfirmationService managerConfirmationService;
    private final GuardRequestService guardRequestService;

    // Показ общей формы входа для сотрудников
    public String getSignInStuffForm(Model model, StuffRoleType roleType) {
        model.addAttribute("LoginDTO", new LoginDTO());
        if (roleType.equals(StuffRoleType.MANAGER)) {
            model.addAttribute("requestURL", "/auth/manager/sign-in");
        } else {
            model.addAttribute("requestURL", "/auth/guardofficer/sign-in");
        }
        return "stuff_login";
    }

    //Обработка данных формы входа менеджера (POST)
    public String postSignInManager(LoginDTO loginDTO, Model model, HttpServletResponse response) {
        CustomResponse authResponse = authService.signInStuff(loginDTO, StuffRoleType.MANAGER);
        // TODO: редирект на главную страницу менеджера

        SessionCookieProvider.setUpSessionCookie(response, authResponse.getCookieSessionId());

        return "redirect:/manager/main";
    }


    //Обработка данных формы входа охранника (POST)
    public String postSignInGuardOfficer(LoginDTO loginDTO, HttpServletResponse response) {
        CustomResponse authResponse = authService.signInStuff(loginDTO, StuffRoleType.GUARD_OFFICER);
        // TODO: редирект на главную страницу охранника
        if (authResponse.isSuccess()) {
            SessionCookieProvider.setUpSessionCookie(response, authResponse.getCookieSessionId());
        }
        return null;
    }

}
