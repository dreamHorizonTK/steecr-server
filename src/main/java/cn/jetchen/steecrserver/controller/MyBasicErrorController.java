package cn.jetchen.steecrserver.controller;

import cn.jetchen.steecrserver.pojo.STCRResposeData;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MyBasicErrorController
 * @Description: 自定义的 error 处理
 * @Author: Jet.Chen
 * @Date: 2019/7/17 22:39
 * @Version: 1.0
 **/
@Controller
public class MyBasicErrorController extends BasicErrorController {

    public MyBasicErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    /**
     * @Description: 定义500的ModelAndView  
    * @Param: [request, response]
     * @return: org.springframework.web.servlet.ModelAndView  
    * @Author: Jet.Chen
     * @Date: 2019-07-17 21:56 
    */
    @RequestMapping(produces = "text/html",value = "/500")
    public ModelAndView errorHtml500(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        model.put("msg","自定义错误信息");
        return new ModelAndView("error/500", model);
    }


    /**
     * @Description: 定义500、400、404 的错误JSON信息  
    * @Param: [request]
     * @return: org.springframework.http.ResponseEntity<cn.jetchen.steecrserver.config.STCRResposeData>  
    * STCRResposeData 为全局统一的接口数据结构
    * @Author: Jet.Chen
     * @Date: 2019-07-17 23:13 
    */
    @RequestMapping(value = {"/500", "/400", "/404"})
    @ResponseBody
    public ResponseEntity<STCRResposeData> error500(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        HttpStatus status = getStatus(request);
        Object messageTemp;
        // stcrResposeData 为返回的数据
        String msg = null;
        if (body.get("error") != null && body.get("message") != null) {
            msg = String.format("%s, %s", body.get("error"), body.get("message"));
        } else if (body.get("error") != null) {
            msg = body.get("error").toString();
        } else if (body.get("message") != null) {
            msg = body.get("message").toString();
        }
        STCRResposeData stcrResposeData = STCRResposeData.initError(
                String.format("%d%d", 1, status.value()),
                msg,
                new HashMap<String, Object>() {{
                    put("error", body.get("error"));
                    put("message", body.get("message"));
                }});
        return new ResponseEntity<>(stcrResposeData, status);
    }


    /**
     * @Description: 定义404的ModelAndView  
    * @Param: [request, response]
     * @return: org.springframework.web.servlet.ModelAndView  
    * @Author: Jet.Chen
     * @Date: 2019-07-17 23:13 
    */
    @RequestMapping(produces = "text/html",value = "/404")
    public ModelAndView errorHtml400(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        model.put("msg","自定义错误信息");
        return new ModelAndView("error/404", model);
    }
}