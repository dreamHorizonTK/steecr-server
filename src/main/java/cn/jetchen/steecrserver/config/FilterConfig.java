package cn.jetchen.steecrserver.config;

import cn.jetchen.steecrserver.annotation.SelfRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: FilterConfig
 * @Description:
 * @Author: Jet.Chen
 * @Date: 2019/7/17 14:41
 * @Version: 1.0
 **/
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SelfRequestFilter());
        registration.addUrlPatterns("/*");
        registration.setName("selfRequestFilter");
        registration.setOrder(1);
        return registration;
    }

}
