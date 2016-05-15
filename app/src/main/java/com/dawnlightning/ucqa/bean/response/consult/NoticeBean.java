package com.dawnlightning.ucqa.bean.response.consult;

import java.util.List;

/**
 * 作者：Administrator on 2016/5/15 18:53
 * 邮箱：823894716@qq.com
 * 消息列表
 */
public class NoticeBean {

    /**
     * code : 0
     * data : {"notices":{"pages":1,"count":2,"list":[{"name":"doctor","id":"2","uid":"1","type":"bwztcomment","isnew":"0","authorid":"2","author":"doctor","note":"评论了你的咨询 眼睛有点干","dateline":"1449848160","isfriend":1,"style":"","link":"space.php?uid=1&do=bwzt&id=4&cid=2","message":"没多大问题了吧，看了医生之后。","do":"bwzt","bwztid":"102"},{"name":"doctor","id":"1","uid":"1","type":"bwztcomment","isnew":"0","authorid":"2","author":"doctor","note":"评论了你的咨询 眼睛有点干","dateline":"1449842584","isfriend":1,"style":"","link":"space.php?uid=1&do=bwzt&id=4&cid=1","message":"没多大问题了吧，看了医生之后。","do":"bwzt","bwztid":"102"}]}}
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
         * notices : {"pages":1,"count":2,"list":[{"name":"doctor","id":"2","uid":"1","type":"bwztcomment","isnew":"0","authorid":"2","author":"doctor","note":"评论了你的咨询 眼睛有点干","dateline":"1449848160","isfriend":1,"style":"","link":"space.php?uid=1&do=bwzt&id=4&cid=2","message":"没多大问题了吧，看了医生之后。","do":"bwzt","bwztid":"102"},{"name":"doctor","id":"1","uid":"1","type":"bwztcomment","isnew":"0","authorid":"2","author":"doctor","note":"评论了你的咨询 眼睛有点干","dateline":"1449842584","isfriend":1,"style":"","link":"space.php?uid=1&do=bwzt&id=4&cid=1","message":"没多大问题了吧，看了医生之后。","do":"bwzt","bwztid":"102"}]}
         */

        private NoticesEntity notices;

        public void setNotices(NoticesEntity notices) {
            this.notices = notices;
        }

        public NoticesEntity getNotices() {
            return notices;
        }

        public static class NoticesEntity {
            /**
             * pages : 1
             * count : 2
             * list : [{"name":"doctor","id":"2","uid":"1","type":"bwztcomment","isnew":"0","authorid":"2","author":"doctor","note":"评论了你的咨询 眼睛有点干","dateline":"1449848160","isfriend":1,"style":"","link":"space.php?uid=1&do=bwzt&id=4&cid=2","message":"没多大问题了吧，看了医生之后。","do":"bwzt","bwztid":"102"},{"name":"doctor","id":"1","uid":"1","type":"bwztcomment","isnew":"0","authorid":"2","author":"doctor","note":"评论了你的咨询 眼睛有点干","dateline":"1449842584","isfriend":1,"style":"","link":"space.php?uid=1&do=bwzt&id=4&cid=1","message":"没多大问题了吧，看了医生之后。","do":"bwzt","bwztid":"102"}]
             */

            private int pages;
            private int count;
            private List<ListEntity> list;

            public void setPages(int pages) {
                this.pages = pages;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public void setList(List<ListEntity> list) {
                this.list = list;
            }

            public int getPages() {
                return pages;
            }

            public int getCount() {
                return count;
            }

            public List<ListEntity> getList() {
                return list;
            }

            public static class ListEntity {
                /**
                 * name : doctor
                 * id : 2
                 * uid : 1
                 * type : bwztcomment
                 * isnew : 0
                 * authorid : 2
                 * author : doctor
                 * note : 评论了你的咨询 眼睛有点干
                 * dateline : 1449848160
                 * isfriend : 1
                 * style :
                 * link : space.php?uid=1&do=bwzt&id=4&cid=2
                 * message : 没多大问题了吧，看了医生之后。
                 * do : bwzt
                 * bwztid : 102
                 */

                private String name;
                private String id;
                private String uid;
                private String type;
                private String isnew;
                private String authorid;
                private String author;
                private String note;
                private String dateline;
                private int isfriend;
                private String style;
                private String link;
                private String message;
                private String bwztid;

                public void setName(String name) {
                    this.name = name;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public void setIsnew(String isnew) {
                    this.isnew = isnew;
                }

                public void setAuthorid(String authorid) {
                    this.authorid = authorid;
                }

                public void setAuthor(String author) {
                    this.author = author;
                }

                public void setNote(String note) {
                    this.note = note;
                }

                public void setDateline(String dateline) {
                    this.dateline = dateline;
                }

                public void setIsfriend(int isfriend) {
                    this.isfriend = isfriend;
                }

                public void setStyle(String style) {
                    this.style = style;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public void setBwztid(String bwztid) {
                    this.bwztid = bwztid;
                }

                public String getName() {
                    return name;
                }

                public String getId() {
                    return id;
                }

                public String getUid() {
                    return uid;
                }

                public String getType() {
                    return type;
                }

                public String getIsnew() {
                    return isnew;
                }

                public String getAuthorid() {
                    return authorid;
                }

                public String getAuthor() {
                    return author;
                }

                public String getNote() {
                    return note;
                }

                public String getDateline() {
                    return dateline;
                }

                public int getIsfriend() {
                    return isfriend;
                }

                public String getStyle() {
                    return style;
                }

                public String getLink() {
                    return link;
                }

                public String getMessage() {
                    return message;
                }

                public String getBwztid() {
                    return bwztid;
                }
            }
        }
    }
}
