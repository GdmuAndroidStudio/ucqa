package com.dawnlightning.ucqa.bean.response.account;

/**
 * 作者：Administrator on 2016/5/15 18:23
 * 邮箱：823894716@qq.com
 * 获取头像返回结果
 */
public class GetAvatarBean {

    /**
     * code : 0
     * data : {"avatar_url":"http://localhost/uc/ucenter/data/avatar/000/00/00/01_avatar_small.jpg"}
     * msg : 进行的操作完成了
     * action : do_success
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
         * avatar_url : http://localhost/uc/ucenter/data/avatar/000/00/00/01_avatar_small.jpg
         */

        private String avatar_url;

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getAvatar_url() {
            return avatar_url;
        }
    }
}
