package com.dawnlightning.ucqa.bean.response.operate;

/**
 * 作者：Administrator on 2016/5/15 19:02
 * 邮箱：823894716@qq.com
 * 评论后返回
 */
public class CommentBean {


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
