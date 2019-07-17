package cn.jetchen.steecrserver.controller;

import cn.jetchen.steecrserver.config.STCRResposeData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@Slf4j
public class HelloWorld {


    @GetMapping("/helloworld")
    public String helloWorld(){
        log.info("hello, {}", "jet");
        return "STRC";
    }

    @GetMapping("/helloworld2")
    public STCRResposeData helloWorld2(@RequestParam(value = "test", required = true)String test){
        log.info("hello, {}, param: {}", "jet", test);
        return STCRResposeData.initSuccess(null, null, null);
    }

    @GetMapping("/testError")
    public String testError(){
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            log.error("error log test, {}", "error param", e);
        }
        int a = 1 / 0;

        return "STRC";
    }

}
