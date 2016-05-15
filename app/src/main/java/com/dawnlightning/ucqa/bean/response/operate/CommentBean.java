package com.dawnlightning.ucqa.bean.response.operate;

/**
 * 作者：Administrator on 2016/5/15 19:02
 * 邮箱：823894716@qq.com
 * 评论后返回
 */
public class CommentBean {

    /**
     * code : 0
     * data : {"commentid":13,"refer":null}
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
         * commentid : 13
         * refer : null
         */

        private int commentid;
        private Object refer;

        public void setCommentid(int commentid) {
            this.commentid = commentid;
        }

        public void setRefer(Object refer) {
            this.refer = refer;
        }

        public int getCommentid() {
            return commentid;
        }

        public Object getRefer() {
            return refer;
        }
    }
}
