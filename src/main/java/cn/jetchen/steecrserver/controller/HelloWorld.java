package cn.jetchen.steecrserver.controller;

import cn.jetchen.steecrserver.exception.STCRException;
import cn.jetchen.steecrserver.pojo.STCRResposeData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
        return STCRResposeData.ok();
    }

    @GetMapping("/testError")
    public String testError(){

        int a = 1 / 0;

        return "STRC";
    }

    @GetMapping("/testError2")
    public String testCustomizedError(){
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            throw new STCRException("msg", "desc");
        }
        return "testError2";
    }


    //局部异常处理（ps：对于参数必填的400异常也会被此异常处理器捕获）
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String exHandler(Exception e){
        if(e instanceof ArithmeticException){
            return "除0异常-局部";
        }
        if(e instanceof STCRException){
            return "自定义异常-局部";
        }
        // 未知的异常做出响应
        return "未知异常-局部";
    }



}
