package iexam.studyin.core.validation;

import iexam.studyin.application.member.controller.dto.MemberDto;
import iexam.studyin.application.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class AddMemberValidation implements Validator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return MemberDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberDto memberDto = (MemberDto) target;

        if (memberRepository.findByEmail(memberDto.getEmail()).isPresent()) {
            errors.rejectValue("email", "already");
        }
    }
}

