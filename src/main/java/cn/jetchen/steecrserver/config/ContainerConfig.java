package cn.jetchen.steecrserver.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @ClassName: ContainerConfig
 * @Description: 配置嵌入式的 servlet 容器
 * @Author: Jet.Chen
 * @Date: 2019/7/17 22:39
 * @Version: 1.0
 **/
@Configuration
public class ContainerConfig {

    /** 下面是 springboot 2.x 系列的写法 */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
        return factory -> {
            factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
            factory.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400"));
            factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
        };
    }

    /** 下面是 springboot 1.x 系列的写法 */
    /*@Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return new EmbeddedServletContainerCustomizer(){
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
            }
        };
    }*/
}