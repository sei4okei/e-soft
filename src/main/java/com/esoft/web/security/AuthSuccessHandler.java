package com.esoft.web.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.type.descriptor.sql.internal.Scale6IntervalSecondDdlType;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import static com.esoft.web.security.SecurityUtil.getSessionRole;
@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        String role = getSessionRole();

        switch (role) {
            case "[MANAGER]":
                return "/implementer?success";
            default:
                return "/task?success";
        }
    }
}
