package org.choongang.member.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.choongang.commons.Utils;
import org.choongang.member.MemberUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        //세션 로그인 실패 메세지 일괄 삭제
        MemberUtil.clearLoginData(session);

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        session.setAttribute("username",username);
        if(!StringUtils.hasText(username)){
            session.setAttribute("NotBlank_userId",
                    Utils.getMessage("NotBlank.userId"));
        }
        if(!StringUtils.hasText(password)){
            session.setAttribute("NotBlank_password",
                    Utils.getMessage("NotBlank.password"));
        }
        if(StringUtils.hasText(username) && StringUtils.hasText(password)){
            //아이디, 비번이 있지만 실패-아이디로 조회가 안되거나, 비번이 일치하지 않는 경우
            session.setAttribute("Global_error",Utils.getMessage
                    ("Fail.login","errors"));
        }
        response.sendRedirect(request.getContextPath()+"/member/login");

    }
}
