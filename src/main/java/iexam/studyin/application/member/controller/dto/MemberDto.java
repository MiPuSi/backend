package iexam.studyin.application.member.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class MemberDto {

    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    private String password;
    private String authKey;
}
