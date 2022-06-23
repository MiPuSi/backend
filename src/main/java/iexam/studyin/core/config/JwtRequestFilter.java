package iexam.studyin.core.config;

import iexam.studyin.core.config.auth.PrincipalDetails;
import iexam.studyin.core.config.auth.PrincipalDetailsService;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {


    private final PrincipalDetailsService principalDetailsService;

    private final JwtUtils jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.info("DoFilterCall");

        String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        if (request.getRequestURI().startsWith("/api/exam") || request.getRequestURI().startsWith("/api/myExam")) {
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer"))
                throw new SignatureException("JWT does not match or found");
        }

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer+")) {
            log.info("jwtRequestFilterCall");
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            PrincipalDetails userDetails = (PrincipalDetails) this.principalDetailsService.loadUserByUsername(username);

            //JWT가 유효한지 체킹
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());

            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        chain.doFilter(request, response);
    }

}
