package com.dawnlightning.ucqa.bean.response.account;

/**
 * 作者：Administrator on 2016/5/15 18:35
 * 邮箱：823894716@qq.com
 * 注销返回结果
 */
public class LoginOutBean {

    /**
     * code : 0
     * data : {"unset_session":true,"ucsynlogout":""}
     * msg : 你已经安全退出了\1
     * action : security_exit
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
    }

    @Override
    public String toString() {
        return "LoginOutBean{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
