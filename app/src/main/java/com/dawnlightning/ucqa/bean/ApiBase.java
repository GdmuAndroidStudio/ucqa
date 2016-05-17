package com.dawnlightning.ucqa.bean;

import com.google.gson.JsonObject;

/**
 * 作者：Administrator on 2016/5/16 09:36
 * 邮箱：823894716@qq.com
 */
public class ApiBase {



    private int code;
    private JsonObject data;
    private String msg;
    private String action;

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }



    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setAction(String action) {
        this.action = action;
    }
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
    public String getAction() {
        return action;
    }
    @Override
    public String toString() {
        return "ApiBase{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
