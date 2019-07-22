package cn.jetchen.steecrserver.utils;

import cn.jetchen.steecrserver.entity.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @ClassName: UserUtils
 * @Description: 用户工具类
 * @Author: Jet.Chen
 * @Date: 2019/7/22 14:16
 * @Version: 1.0
 **/
public class UserUtils {

    /**
    * @Description: 获取当前登录人信息
    * @Param: []
    * @return: cn.jetchen.steecrserver.entity.UserInfo
    * @Author: Jet.Chen
    * @Date: 2019/7/22 14:19
    */
    public static UserInfo getLoginUserInfo() {
        UserInfo userInfo = null;
        try {
            userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            // TODO 指定默认的权限人
        }
        return userInfo;
    }

}
