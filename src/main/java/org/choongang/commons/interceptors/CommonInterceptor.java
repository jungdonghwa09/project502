package org.choongang.commons.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CommonInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        checkDevice(request);
        return true;
    }

    private void checkDevice(HttpServletRequest request){
        //모바일, pc 둘 중 하나면 그에 맞는 뷰를 제공해주는 메서드
        String device = request.getParameter("device");
        if(!StringUtils.hasText(device)){
            return;
        }

        device=device.toUpperCase().equals("MOBILE") ? "MOBILE" : "PC";
        HttpSession session = request.getSession();
        session.setAttribute("device",device);
    }
}
