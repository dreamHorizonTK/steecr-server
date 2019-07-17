package cn.jetchen.steecrserver.config;

import cn.jetchen.steecrserver.STCRTimeUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: STCRResposeData
 * @Description: 自定义标准的请求返回值
 * 数据结构为：
 * {
 *     "successFlag": 1,                        // 标识是否成功，1：成功，0：失败
 *     "msg": "test",                           // 错误提示，可能为空
 *     "result": {                              // 结果集
 *         "requestTime": "2019-7-17 14:09:00", // 请求时间
 *         "code": "1201",                      // 请求状态码，
 *         "data": {} 或 []                     // 返回的数据集，有可能是 list，有可能是 map
 *     }
 * }
 * @Author: Jet.Chen
 * @Date: 2019/7/17 13:57
 * @Version: 1.0
 **/
@Data
public class STCRResposeData {

    private String msg;

    private byte successFlag = 1;

    private Map<String, Object> result = new HashMap<>(){{
        put("code", "1201");
        put("data", null);
    }};

    public STCRResposeData(){
        result.put("requestTime", STCRTimeUtils.formateDateTime(null));
    }

    public STCRResposeData(Byte successFlag, String code, String errorMsg, Object data){
        result.put("requestTime", STCRTimeUtils.formateDateTime(null));
        if(StringUtils.isNotBlank(errorMsg)) this.msg = errorMsg;
        if(StringUtils.isNotBlank(code)) result.put("code", code);
        if (successFlag != null) {
            if (1 != successFlag) this.successFlag = 0;
        }
        if (data != null) result.put("data", data);
    }
}
