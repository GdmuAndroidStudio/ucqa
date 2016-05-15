package com.dawnlightning.ucqa.bean.response.account;

/**
 * 作者：Administrator on 2016/5/15 18:24
 * 邮箱：823894716@qq.com
 * 获取注册验证码
 */
public class GetSeccodeBean {

    /**
     * code : 0
     * data : {"seccode_auth":"134aqUzRfCZE4so67mO%2FsLOS0Cy8ZmQYuQ5vM06Ll4cm","seccode":"CQ9B"}
     * msg : rest_success
     * action : rest_success
     */

    private int code;
    private DataEntity data;
    private String msg;
    private String action;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
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

    public DataEntity getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public String getAction() {
        return action;
    }

    public static class DataEntity {
        /**
         * seccode_auth : 134aqUzRfCZE4so67mO%2FsLOS0Cy8ZmQYuQ5vM06Ll4cm
         * seccode : CQ9B
         */

        private String seccode_auth;
        private String seccode;

        public void setSeccode_auth(String seccode_auth) {
            this.seccode_auth = seccode_auth;
        }

        public void setSeccode(String seccode) {
            this.seccode = seccode;
        }

        public String getSeccode_auth() {
            return seccode_auth;
        }

        public String getSeccode() {
            return seccode;
        }
    }
}
