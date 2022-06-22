package iexam.studyin.application.member.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {

    private String token;

    public JwtResponse(String email, String token) {
        this.token = "Bearer "+  token;
    }
}
