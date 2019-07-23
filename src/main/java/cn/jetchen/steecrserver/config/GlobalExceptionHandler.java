package cn.jetchen.steecrserver.config;

import cn.jetchen.steecrserver.exception.STCRException;
import cn.jetchen.steecrserver.pojo.STCRResposeData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description: 全局异常处理器
 * @Author: Jet.Chen
 * @Date: 2019/7/22 17:54
 * @Version: 1.0
 **/
@ControllerAdvice
public class GlobalExceptionHandler {


    //处理自定义的异常
    @ExceptionHandler(STCRException.class)
    @ResponseBody
    public Object customHandler(STCRException e) {
        return STCRResposeData.error(e);
    }

    //其他未处理的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object exceptionHandler(Exception e) throws Exception {
        // TODO 异常需要特殊处理，比如数据回滚等
        throw e;
    }
}
