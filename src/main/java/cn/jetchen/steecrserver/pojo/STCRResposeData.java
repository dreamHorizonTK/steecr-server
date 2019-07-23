package cn.jetchen.steecrserver.pojo;

import cn.jetchen.steecrserver.exception.STCRException;
import lombok.*;

/**
 * @ClassName: STCRResposeData
 * @Description: 自定义标准的请求返回值
 * 数据结构为：
 * {
 *     "code": 1,                               // 请求状态码，标识是否成功，1：成功，0：失败
 *     "bizId": "1201",                         // 业务状态码，
 *     "msg": "test",                           // 简短的错误提示，可能为空
 *     "desc": "test",                          // 具体的错误说明，如错误发生的数据，位置等
 *     "data": {} 或 []                          // 返回的数据集，有可能是 list，有可能是 map
 * }
 * @Author: Jet.Chen
 * @Date: 2019/7/17 13:57
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class STCRResposeData<T> {

    private byte code = 1; // 请求状态码，标识是否成功，1：成功，0：失败
    private String bizId = "1201"; // 业务状态码，
    private String msg; // 简短的错误信息
    private String desc; // 具体的错误说明，如错误发生的数据，位置等
    private T data; // 响应参数

    public static STCRResposeData ok() {
        return new STCRResposeData<>((byte)1, "1201", null, null, null);
    }

    public static <T> STCRResposeData<T> ok(T data) {
        return new STCRResposeData<>((byte)1, "1201", null, null, data);
    }

    public static <T> STCRResposeData<T> error(@NonNull String bizId, @NonNull String msg, String desc, T data) {
        return new STCRResposeData<>((byte)0, bizId, msg, desc, data);
    }

    public static <T> STCRResposeData<T> error(@NonNull STCRException exception) {
        return error(exception.getRetCd(), exception.getMsg(), exception.getDesc(), null);
    }
}
