package cn.jetchen.steecrserver.config;

import cn.jetchen.steecrserver.utils.NetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: SuccessLoginHandler
 * @Description: 成功登录后的操作
 * @Author: Jet.Chen
 * @Date: 2019/7/22 11:24
 * @Version: 1.0
 **/
@Service
@Slf4j
public class SuccessLoginHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        /*Cookie cookie = new Cookie("name", "eric");
        cookie.setMaxAge(-1);
        httpServletResponse.addCookie(cookie);*/
        log.info("success login, UA: {}", NetUtil.getUserAgent(httpServletRequest));
        httpServletResponse.sendRedirect("/");
    }
}
