package iexam.studyin.application.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import iexam.studyin.application.member.controller.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthKeyService {

    private final MemberService memberService;
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    public void saveAuth(MemberDto memberDto) throws JsonProcessingException {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(memberDto.getEmail(), objectMapper.writeValueAsString(memberDto), 30, TimeUnit.MINUTES);
    }

    public void deleteAuth(String email) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.getAndDelete(email);
    }

    public void checkAuth(String email, String authKey) throws JsonProcessingException {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        MemberDto memberDto = objectMapper.readValue(valueOperations.get(email), MemberDto.class);
        if (memberDto.getAuthKey() != null && memberDto.getAuthKey().equals(authKey)) {
            valueOperations.getAndDelete(email);
            memberService.signUp(memberDto);
        }
    }
}
