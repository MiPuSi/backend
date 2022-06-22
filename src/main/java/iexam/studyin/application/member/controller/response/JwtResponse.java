package iexam.studyin.application.member.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
public class JwtResponse {

    private String token;

    @Builder
    public JwtResponse(String email, String token) {
        this.token = "Bearer "+  token;
    }
}
