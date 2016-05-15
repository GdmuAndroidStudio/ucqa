package com.dawnlightning.ucqa.bean.response.issue;

/**
 * 作者：Administrator on 2016/5/15 18:56
 * 邮箱：823894716@qq.com
 * 上传图片的返回
 */
public class UploadBean {

    /**
     * code : 0
     * data : {"pic":{"albumid":2,"uid":"6","username":"test1","dateline":"1435371306","filename":"54a8b87ff3040.png","postip":"222.16.97.91","title":"","type":"image/png","size":84245,"filepath":"201506/27/6_1435371306ShiS.png","thumb":1,"remote":0,"topicid":0,"picid":22,"pic":"attachment/201506/27/6_1435371306ShiS.png.thumb.jpg"}}
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
         * pic : {"albumid":2,"uid":"6","username":"test1","dateline":"1435371306","filename":"54a8b87ff3040.png","postip":"222.16.97.91","title":"","type":"image/png","size":84245,"filepath":"201506/27/6_1435371306ShiS.png","thumb":1,"remote":0,"topicid":0,"picid":22,"pic":"attachment/201506/27/6_1435371306ShiS.png.thumb.jpg"}
         */

        private PicEntity pic;

        public void setPic(PicEntity pic) {
            this.pic = pic;
        }

        public PicEntity getPic() {
            return pic;
        }

        public static class PicEntity {
            /**
             * albumid : 2
             * uid : 6
             * username : test1
             * dateline : 1435371306
             * filename : 54a8b87ff3040.png
             * postip : 222.16.97.91
             * title :
             * type : image/png
             * size : 84245
             * filepath : 201506/27/6_1435371306ShiS.png
             * thumb : 1
             * remote : 0
             * topicid : 0
             * picid : 22
             * pic : attachment/201506/27/6_1435371306ShiS.png.thumb.jpg
             */

            private int albumid;
            private String uid;
            private String username;
            private String dateline;
            private String filename;
            private String postip;
            private String title;
            private String type;
            private int size;
            private String filepath;
            private int thumb;
            private int remote;
            private int topicid;
            private int picid;
            private String pic;

            public void setAlbumid(int albumid) {
                this.albumid = albumid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public void setDateline(String dateline) {
                this.dateline = dateline;
            }

            public void setFilename(String filename) {
                this.filename = filename;
            }

            public void setPostip(String postip) {
                this.postip = postip;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public void setFilepath(String filepath) {
                this.filepath = filepath;
            }

            public void setThumb(int thumb) {
                this.thumb = thumb;
            }

            public void setRemote(int remote) {
                this.remote = remote;
            }

            public void setTopicid(int topicid) {
                this.topicid = topicid;
            }

            public void setPicid(int picid) {
                this.picid = picid;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getAlbumid() {
                return albumid;
            }

            public String getUid() {
                return uid;
            }

            public String getUsername() {
                return username;
            }

            public String getDateline() {
                return dateline;
            }

            public String getFilename() {
                return filename;
            }

            public String getPostip() {
                return postip;
            }

            public String getTitle() {
                return title;
            }

            public String getType() {
                return type;
            }

            public int getSize() {
                return size;
            }

            public String getFilepath() {
                return filepath;
            }

            public int getThumb() {
                return thumb;
            }

            public int getRemote() {
                return remote;
            }

            public int getTopicid() {
                return topicid;
            }

            public int getPicid() {
                return picid;
            }

            public String getPic() {
                return pic;
            }
        }
    }
}
