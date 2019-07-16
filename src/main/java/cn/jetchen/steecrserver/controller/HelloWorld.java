package cn.jetchen.steecrserver.controller;

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

    @GetMapping("/helloworld")
    public String helloWorld(){
        return "STRC";
    }

    @GetMapping("/testError")
    public String testError(){
        int a = 1 / 0;
        return "STRC";
    }

}
