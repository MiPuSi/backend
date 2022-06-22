package iexam.studyin.application.member.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthEmailDto {
    private String email;
    private String authKey;
}
