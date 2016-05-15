package com.dawnlightning.ucqa.bean.response.operate;

/**
 * 作者：Administrator on 2016/5/15 19:00
 * 邮箱：823894716@qq.com
 * 采纳咨询
 */
public class AcceptBean {

    /**
     * code : 0
     * data : {"status":1,"uid":"1","username":"root","bwztid":"4"}
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
         * status : 1
         * uid : 1
         * username : root
         * bwztid : 4
         */

        private int status;
        private String uid;
        private String username;
        private String bwztid;

        public void setStatus(int status) {
            this.status = status;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setBwztid(String bwztid) {
            this.bwztid = bwztid;
        }

        public int getStatus() {
            return status;
        }

        public String getUid() {
            return uid;
        }

        public String getUsername() {
            return username;
        }

        public String getBwztid() {
            return bwztid;
        }
    }
}
