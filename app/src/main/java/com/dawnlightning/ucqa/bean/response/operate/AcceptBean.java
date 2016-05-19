package com.dawnlightning.ucqa.bean.response.operate;

/**
 * 作者：Administrator on 2016/5/15 19:00
 * 邮箱：823894716@qq.com
 * 采纳咨询
 */
public class AcceptBean {


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
