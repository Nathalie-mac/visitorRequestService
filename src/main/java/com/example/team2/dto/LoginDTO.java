package com.example.team2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//на и с фронта
//данные для входа и регистрации
public class LoginDTO {
    private String login;       //e-mail посетителя или код сотрудника
    private String password;

}
