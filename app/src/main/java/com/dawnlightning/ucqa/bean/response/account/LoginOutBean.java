package com.dawnlightning.ucqa.bean.response.account;

/**
 * 作者：Administrator on 2016/5/15 18:35
 * 邮箱：823894716@qq.com
 * 注销返回结果
 */
public class LoginOutBean {


    /**
     * unset_session : true
     * ucsynlogout :
     */

    private boolean unset_session;
    private String ucsynlogout;

    public void setUnset_session(boolean unset_session) {
        this.unset_session = unset_session;
    }

    public void setUcsynlogout(String ucsynlogout) {
        this.ucsynlogout = ucsynlogout;
    }

    public boolean getUnset_session() {
        return unset_session;
    }

    public String getUcsynlogout() {
        return ucsynlogout;
    }

    @Override
    public String toString() {
        return "LoginOutBean{" +
                "unset_session=" + unset_session +
                ", ucsynlogout='" + ucsynlogout + '\'' +
                '}';
    }
    public LoginOutBean(){

    }
}
