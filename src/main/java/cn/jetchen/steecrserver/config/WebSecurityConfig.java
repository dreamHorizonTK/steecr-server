package cn.jetchen.steecrserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.util.StringUtils;


/**
 * @ClassName: WebSecurityConfig
 * @Description: Web Security 配置
 * @Author: Jet.Chen
 * @Date: 2019/7/22 11:00
 * @Version: 1.0
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SuccessLoginHandler successLoginHandler;

    @Autowired
    Environment environment;


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                    //所有静态资源都忽略权限控制
                    .antMatchers("/resources/**", "/**").permitAll()
                    //.antMatchers("/admin/**").hasRole("ADMIN")
                    //.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                    //任意请求角色必须为用户或者管理员
                    .anyRequest().authenticated()

                .and().formLogin()
                    //配置表单登陆
                    //配置登陆页面路径并允许所有人访问登陆页面
                    .loginPage("/login").permitAll()
                    //登陆成功时的处理类
                    .successHandler(successLoginHandler)
                    // 登陆失败时跳转
                    .failureUrl("/loginError")

                .and().logout()
                    //配置登出页面路径并允许所有人访问登陆页面
                    .logoutUrl("/logout").logoutSuccessUrl("/index").permitAll()

                .and()
                    .rememberMe()
                    .rememberMeParameter("remember-me")
                    .tokenValiditySeconds(120*60*1000) // cookie有效期是 2小时
                    .rememberMeCookieName("RM_COOKIE")

                .and()
                    //.addFilterBefore(new SelfRequestFilter(), ChannelProcessingFilter.class) // 保证跨域的过滤器首先触发
                    .headers().frameOptions().disable()
                    .httpStrictTransportSecurity().disable()

                .and()
                    //不用进行CSRF验证
                    .csrf().disable();

    }


    /**
     * @param auth
     * @throws Exception 权限配置管理
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 临时使用
        auth
            .inMemoryAuthentication()
            .withUser("admin").password("admin").roles("USER");
        /*String encrypt = environment.getProperty("wailian.password.encrypt");
        String strength = environment.getProperty("wailian.password.secret");
        PasswordEncoder passwordEncoder = null;
        switch (encrypt.toUpperCase()) {
            case "SCRYPT":
                passwordEncoder = new SCryptPasswordEncoder();
                break;
            case "BCRYPT":
                int sha_strength = 1;
                if (!StringUtils.isEmpty(strength)) {
                    try {
                        sha_strength = Integer.parseInt(strength);
                    } catch (NumberFormatException e) {

                    }
                }
                passwordEncoder = new BCryptPasswordEncoder(sha_strength);
                break;
            case "SHA-256":
                passwordEncoder = new StandardPasswordEncoder(strength);
                break;
            case "PBKDF2":
                if (!StringUtils.isEmpty(strength)) {
                    passwordEncoder = new Pbkdf2PasswordEncoder(strength);
                }
                break;
            default:
                passwordEncoder = NoOpPasswordEncoder.getInstance();

        }
        // 数据库密码匹配查询
        auth.userDetailsService(userAuthenticationService).passwordEncoder(passwordEncoder);*/
    }


}
