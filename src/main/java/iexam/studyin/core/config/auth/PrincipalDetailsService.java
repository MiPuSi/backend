package iexam.studyin.core.config.auth;

import iexam.studyin.application.member.domain.Member;
import iexam.studyin.application.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> byEmail = memberRepository.findByEmail(username);

        if(byEmail.isEmpty())
            throw new UsernameNotFoundException("존재하지 않는 유저" + username);

        Member member = byEmail.get();

        return new PrincipalDetails(member);
    }
}
