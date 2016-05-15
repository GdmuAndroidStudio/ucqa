package com.dawnlightning.ucqa.bean.response.account;

/**
 * 作者：Administrator on 2016/5/15 18:36
 * 邮箱：823894716@qq.com
 * 修改个人资料的返回结果
 */
public class ProfileBean {

    /**
     * code : 0
     * msg : 个人资料更新成功了
     * action : update_on_successful_individuals
     */

    private int code;
    private String msg;
    private String action;

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
}
