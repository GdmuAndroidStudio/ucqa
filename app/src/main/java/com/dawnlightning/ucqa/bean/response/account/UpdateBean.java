package com.dawnlightning.ucqa.bean.response.account;

/**
 * 作者：Administrator on 2016/5/28 22:57
 * 邮箱：823894716@qq.com
 */
public class UpdateBean {
    private String note;
    private String url;
    private String name;
    private int versioncode;

    public int getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(int versioncode) {
        this.versioncode = versioncode;
    }

    public UpdateBean(){

    }

    public UpdateBean(String note, String url, String name, int versioncode) {
        this.note = note;
        this.url = url;
        this.name = name;
        this.versioncode = versioncode;
    }

    public String getNote() {

        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
