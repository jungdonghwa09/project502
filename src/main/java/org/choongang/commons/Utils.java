package org.choongang.commons;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class Utils {
    private final HttpServletRequest request;
    private final HttpSession session;
    public boolean isMobile(){
        //모바일 수동전환 체크
        String device = (String)session.getAttribute("device");
        if(StringUtils.hasText(device)){
            return device.equals("MOBILE");
        }

        //요청헤더 user-agent이용
        String ua = request.getHeader("User_Agent");
        String pattern=".*(iphone|oPod|iPad|BlackBerry|Android|Windows|CE" +
                "|LG|MOT|SAMSUNG|SonyEricsson).*";
        return ua.matches(pattern);

    }

    public String tpl(String path){
        String prefix = isMobile() ? "mobile/" : "front/";

        return prefix + path;
    }
}
