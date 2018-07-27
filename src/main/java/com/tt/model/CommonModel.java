package com.tt.model;

import com.tt.utils.ConstantUtils;

/**
 * 封装了状态码和状态信息
 */
public class CommonModel {

    /**
     * 0 请求接口成功, 有数据返回
     * 1 请求接口成功, 无数据返回
     */
    private int code;

    private String msg;

    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 成功
     */
    public void setSuccess() {
        setCode(ConstantUtils.CODE_SUCCESS);
        setMsg(ConstantUtils.MSG_SUCCESS);
    }

    /**
     * 失败
     */
    public void setFail() {
        setCode(ConstantUtils.CODE_FAIL);
        setMsg(ConstantUtils.MSG_FAIL);
    }
}
