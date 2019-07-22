package cn.jetchen.steecrserver.exception;

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

    /**异常对应的简要描述*/
    private String msg;

    /**异常对应的详细信息*/
    private String desc;

    public STCRException() {
        super();
    }

    public STCRException(String msg, String desc) {
        super(msg);
        this.msg = msg;
        this.desc = desc;
    }

    public STCRException(String retCd, String msg, String desc) {
        super(msg);
        this.retCd = retCd;
        this.msg = msg;
        this.desc = desc;
    }

}
