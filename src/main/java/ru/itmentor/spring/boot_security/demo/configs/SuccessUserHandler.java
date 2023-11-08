package ru.itmentor.spring.boot_security.demo.configs;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(SuccessUserHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        logger.info("Метод onAuthenticationSuccess вызван для пользователя: " + authentication.getName());

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        logger.info("Роли пользователя: " + roles);

        if (roles.contains("USER")) {
            httpServletResponse.sendRedirect("/user");
        } else if (roles.contains("ADMIN")) {
            httpServletResponse.sendRedirect("/admin");
        }
    }

}