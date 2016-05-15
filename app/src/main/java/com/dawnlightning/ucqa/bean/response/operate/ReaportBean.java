package com.dawnlightning.ucqa.bean.response.operate;

/**
 * 作者：Administrator on 2016/5/15 19:03
 * 邮箱：823894716@qq.com
 * 举报的返回
 */
public class ReaportBean {

    /**
     * code : 0
     * msg : 感谢您的举报，我们会尽快处理
     * action : report_success
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
