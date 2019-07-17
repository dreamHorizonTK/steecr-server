package cn.jetchen.steecrserver.config;

import lombok.Data;

/**
 * @ClassName: STCRException
 * @Description: 自定义异常
 * @Author: Jet.Chen
 * @Date: 2019/7/17 13:52
 * @Version: 1.0
 **/
@Data
public class STCRException extends RuntimeException {

    /**异常对应的返回码*/
    private String retCd = "1500";

    /**异常对应的描述信息*/
    private String msgDes;

    public STCRException() {
        super();
    }

    public STCRException(String msgDes) {
        super(msgDes);
        this.msgDes = msgDes;
    }

    public STCRException(String retCd, String msgDes) {
        super();
        this.retCd = retCd;
        this.msgDes = msgDes;
    }

}
