package com.dawnlightning.ucqa.bean.response.issue;

/**
 * 作者：Administrator on 2016/5/15 18:57
 * 邮箱：823894716@qq.com
 * 发布咨询后的返回
 */
public class SendConsult {

    /**
     * code : 0
     * data : {"url":"space.php?uid=1&do=bwzt&id=7"}
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
         * url : space.php?uid=1&do=bwzt&id=7
         */

        private String url;

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }
}
