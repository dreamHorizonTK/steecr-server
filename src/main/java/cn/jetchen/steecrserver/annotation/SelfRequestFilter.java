package cn.jetchen.steecrserver.annotation;

import cn.jetchen.steecrserver.utils.NetUtil;
import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @ClassName: SelfRequestFilter
 * @Description: 访问过滤器
 * @Author: Jet.Chen
 * @Date: 2019/1/31 17:03
 * @Version: 1.0
 **/
@Slf4j
public class SelfRequestFilter implements Filter {

//    private static final Logger LOGGER = LoggerFactory.getLogger(SelfRequestFilter.class);

    public static final String SPLIT_STRING_M = "=";

    public static final String SPLIT_STRING_DOT = ", ";

    // IP 黑名单
    public ArrayList<String> ipBlockList = new ArrayList<>();

    {
        // TODO 初始化黑名单，作用是直接差内存比较快，不用再请求数据库层的黑名单了
        ipBlockList.add("192.168.154.7777");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long t = System.currentTimeMillis();

        //日志trade
        MDC.clear();
        MDC.put("trade_id", UUID.randomUUID().toString().replaceAll("-",""));

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        ContentCachingRequestWrapper wrapperRequest = new ContentCachingRequestWrapper(req);
        ContentCachingResponseWrapper wrapperResponse = new ContentCachingResponseWrapper(res);

        String requestURI = req.getRequestURI();
        String httpIp = NetUtil.getHttpIp(req);

        boolean printHandleTime = false;
        // 打印请求路径
        if (!requestURI.matches(".+springfox-swagger-ui/.+") &&
                !requestURI.matches(".+swagger-resources/.+") &&
                !requestURI.matches(".+favicon.ico")) {
            printHandleTime = true;
            // 打印请求参数
            // TODO 数据脱敏处理
            String urlParams = getRequestParams(req);
            String requestBodyStr = getRequestBody(wrapperRequest);
            log.info("==============>> request, ip: {}, URI: {}, URIParams: {}, bodyParams: {}", httpIp, requestURI, urlParams, requestBodyStr);

        }
        // TODO 查询ip是否被加入黑名单

        // TODO 同一个IP在1分钟中登陆次数过多，则加入黑名单

//        chain.doFilter(request, res);
        chain.doFilter(wrapperRequest, wrapperResponse);


        if (printHandleTime) {
            String responseBodyStr = getResponseBody(wrapperResponse);
            log.info("==============>> response, status: {}, body: {}", wrapperResponse.getStatusCode(), responseBodyStr);
        }

        // request 的inputStream和response 的outputStream默认情况下是只能读一次， 不可重复读
        // 所以要用包裹类，最后要再回写一下
        wrapperResponse.copyBodyToResponse();
    }

    @Override
    public void destroy() {
    }


    /**
    * @Description: 获取请求地址上的参数
    * @Param: [request]
    * @return: java.lang.String
    * @Author: Jet.Chen
    * @Date: 2019/7/17 14:58
    */
    private String getRequestParams(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        Enumeration<String> enu = request.getParameterNames();
        //获取请求参数
        while (enu.hasMoreElements()) {
            String name = enu.nextElement();
            sb.append(name).append(SPLIT_STRING_M).append(request.getParameter(name));
            if(enu.hasMoreElements()) {
                sb.append(SPLIT_STRING_DOT);
            }
        }
        return sb.toString();
    }

    /**
    * @Description: 获取请求body中的参数
    * @Param: [request]
    * @return: java.lang.String
    * @Author: Jet.Chen
    * @Date: 2019/7/17 14:57
    */
    private String getRequestBody(ContentCachingRequestWrapper request) {
        if (request.getContentType() != null && (request.getContentType().contains("json") || request.getContentType().contains("text"))) {
            ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
            if (wrapper != null) {
                byte[] buf = wrapper.getContentAsByteArray();
                if (buf.length > 0) {
                    String payload;
                    try {
                        payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                    } catch (UnsupportedEncodingException e) {
                        payload = "[unknown]";
                    }
                    return payload.replaceAll("\\n", "");
                }
            }
        }
        return "";
    }

    /**
    * @Description: 打印响应参数
    * @Param: [response]
    * @return: java.lang.String
    * @Author: Jet.Chen
    * @Date: 2019/7/17 14:59
    */
    private String getResponseBody(ContentCachingResponseWrapper response) {
        if (response.getContentType() != null && (response.getContentType().contains("json") || response.getContentType().contains("text"))) {
            ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
            if(wrapper != null) {
                byte[] buf = wrapper.getContentAsByteArray();
                if(buf.length > 0) {
                    String payload;
                    try {
                        payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                    } catch (UnsupportedEncodingException e) {
                        payload = "[unknown]";
                    }
                    return payload;
                }
            }
        }
        return "";
    }

}