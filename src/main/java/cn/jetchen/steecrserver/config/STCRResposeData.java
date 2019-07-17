package cn.jetchen.steecrserver.config;

import cn.jetchen.steecrserver.utils.STCRTimeUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: STCRResposeData
 * @Description: 自定义标准的请求返回值
 * 数据结构为：
 * {
 *     "code": 1,                               // 请求状态码，标识是否成功，1：成功，0：失败
 *     "msg": "test",                           // 错误提示，可能为空
 *     "result": {                              // 结果集
 *         "timeStamp": "2019-7-17 14:09:00",   // 请求时间
 *         "bizId": "1201",                     // 业务状态码，
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

    private byte code = 1;

    private Map<String, Object> result = new HashMap<>(){{
        put("bizId", "1201");
        put("data", null);
    }};

    private STCRResposeData(){
        result.put("timeStamp", STCRTimeUtils.formateDateTime(null));
    }

    public static STCRResposeData initSuccess(String bizId, String msg, Object data){
        STCRResposeData stcrResposeData = new STCRResposeData();
        if(StringUtils.isNotBlank(msg)) stcrResposeData.setMsg(msg);
        if(StringUtils.isNotBlank(bizId)) stcrResposeData.getResult().put("bizId", bizId);
        if (data != null) stcrResposeData.getResult().put("data", data);
        return stcrResposeData;
    }

    public static STCRResposeData initError(String bizId, String msg, Object data){
        STCRResposeData stcrResposeData = initSuccess(bizId, msg, data);
        stcrResposeData.setCode((byte) 0);
        return stcrResposeData;
    }
}
