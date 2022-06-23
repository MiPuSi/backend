package iexam.studyin.application.advice.service;

import iexam.studyin.application.advice.domain.Advice;
import iexam.studyin.application.advice.repository.AdviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdviceService {

    private final AdviceRepository adviceRepository;


    public Advice randOne() {
        int count = (int)adviceRepository.count();
        Random random = new Random();

        int value = random.nextInt(count + 1);
        Advice advice = adviceRepository.findById((long) value)
                .orElse(Advice.builder()
                        .content("세상에 명언이 없잖아?")
                        .adviser("-개발자")
                        .build());
        return advice;
    }
}
