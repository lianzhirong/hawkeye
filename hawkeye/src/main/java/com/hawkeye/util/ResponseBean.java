package com.hawkeye.util;

import com.alibaba.fastjson.JSONObject;
import com.hawkeye.common.error.BaseErrorInfoInterface;
import com.hawkeye.common.error.CommonCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseBean<T> {

    private int code;
    private String message;
    private T data;


    /**
     * 成功
     * @return
     */
    public static ResponseBean success() {
        ResponseBean rb = new ResponseBean();
        rb.setCode(CommonCodeEnum.SUCCESS.getResultCode());
        rb.setMessage(CommonCodeEnum.SUCCESS.getResultMsg());
        return rb;
    }


    /**
     * 成功
     * @param data
     * @return
     */
    public static ResponseBean success(Object data) {
        ResponseBean rb = new ResponseBean();
        rb.setCode(CommonCodeEnum.SUCCESS.getResultCode());
        rb.setMessage(CommonCodeEnum.SUCCESS.getResultMsg());
        rb.setData(data);
        return rb;
    }


    /**
     * 失败
     */
    public static ResponseBean error(BaseErrorInfoInterface errorInfo) {
        ResponseBean rb = new ResponseBean();
        rb.setCode(errorInfo.getResultCode());
        rb.setMessage(errorInfo.getResultMsg());
        rb.setData(null);
        return rb;
    }

    /**
     * 失败
     */
    public static ResponseBean error(int code, String message) {
        ResponseBean rb = new ResponseBean();
        rb.setCode(code);
        rb.setMessage(message);
        rb.setData(null);
        return rb;
    }


    /**
     * 失败
     */
    public static ResponseBean error( String message) {
        ResponseBean rb = new ResponseBean();
        rb.setCode(-1);
        rb.setMessage(message);
        rb.setData(null);
        return rb;
    }

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }

}
