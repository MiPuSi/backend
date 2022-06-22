package iexam.studyin.application.member.controller;

import iexam.studyin.application.member.controller.dto.LoginDto;
import iexam.studyin.application.member.controller.response.JwtResponse;
import iexam.studyin.core.config.JwtUtils;
import iexam.studyin.core.config.auth.PrincipalDetails;
import iexam.studyin.core.config.auth.PrincipalDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class loginController {

    private final AuthenticationManager authenticationManager;
    private final PrincipalDetailsService principalDetailsService;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginDto loginDto){
        log.info("input = {}",loginDto);

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("존재하지 않는 유저 ",e);
        }

        final PrincipalDetails principalDetails = (PrincipalDetails) principalDetailsService.loadUserByUsername(loginDto.getEmail());
        final String jwt = jwtUtils.generateToken(principalDetails);

        JwtResponse jwtResponse = JwtResponse.builder()
                .token(jwt)
                .email(principalDetails.getUsername())
                .build();

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(HttpServletRequest request,
                       @AuthenticationPrincipal PrincipalDetails member){
        String authorization = request.getHeader("Authorization");
        if(authorization==null || !authorization.startsWith("Bearer ")){
            return "유저정보 다시 확인";
        }
        log.info("jwt = {} ",authorization);
        log.info("member = {}",member.getUsername());
        Boolean flag = jwtUtils.validateToken(authorization.substring(7), member);
        log.info("isValidate ={}",flag);
        return "pass";
    }
}
