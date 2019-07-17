package cn.jetchen.steecrserver.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: NetUtil
 * @Description: 网络工具类 util
 * @Author: Jet.Chen
 * @Date: 2019/7/17 14:45
 * @Version: 1.0
 **/
public class NetUtil {


    /**
    * @Description: 拿请求ip
    * @Param: [request]
    * @return: java.lang.String
    * @Author: Jet.Chen
    * @Date: 2019/7/17 14:33
    */
    public static String getHttpIp(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
    * @Description: 获取用户浏览器信息
    * @Param: [request]
    * @return: java.lang.String
    * @Author: Jet.Chen
    * @Date: 2019/7/17 14:35
    */
    public static String getUserAgent(HttpServletRequest request){
        return request.getHeader("user-agent");
    }

}
