package com.dawnlightning.ucqa.bean.response.account;

/**
 * 作者：Administrator on 2016/5/15 18:24
 * 邮箱：823894716@qq.com
 * 获取注册验证码
 */
public class GetSeccodeBean {

    public GetSeccodeBean(){

    }
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

    @Override
    public String toString() {
        return "GetSeccodeBean{" +
                "seccode_auth='" + seccode_auth + '\'' +
                ", seccode='" + seccode + '\'' +
                '}';
    }
}
