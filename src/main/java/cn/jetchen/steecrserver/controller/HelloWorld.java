package cn.jetchen.steecrserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: HelloWorld
 * @Description:
 * @Author: Jet.Chen
 * @Date: 2019/7/15 16:48
 * @Version: 1.0
 **/
@RestController
@RequestMapping
public class HelloWorld {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorld.class);

    @GetMapping("/helloworld")
    public String helloWorld(){
        LOGGER.info("hello, {}", "jet");
        return "STRC";
    }

    @GetMapping("/testError")
    public String testError(){
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            LOGGER.error("error log test, {}", "error param", e);
        }
        int a = 1 / 0;

        return "STRC";
    }

}
