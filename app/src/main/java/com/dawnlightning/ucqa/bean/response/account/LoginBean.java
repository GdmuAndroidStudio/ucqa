package com.dawnlightning.ucqa.bean.response.account;

/**
 * 作者：Administrator on 2016/5/15 18:32
 * 邮箱：823894716@qq.com
 * 登陆返回结果
 */
public class LoginBean {


    /**
     * m_auth : 4e64L1WXZV%2B0TDmRgCsOe050x1WCD5EGhnZpxlUx7RcoTEA5w10e9dVL1wSLvnSCuv4nFRqBGKOCmf%2F37AKP
     * uhash : e8fee782f2e7ddce363edd0979dbd4f8
     * formhash : 31bce83a
     * space : {"uid":"1","groupid":"1","credit":"85","experience":"75","username":"root","name":"李四","namestatus":"1","videostatus":"0","domain":"","friendnum":"0","viewnum":"2","notenum":"0","addfriendnum":"0","mtaginvitenum":"0","eventinvitenum":"0","myinvitenum":"0","pokenum":"0","doingnum":"0","blognum":"0","albumnum":"0","threadnum":"0","pollnum":"0","eventnum":"0","sharenum":"0","dateline":"1447915083","updatetime":"1447927867","lastsearch":"0","lastpost":"1447927756","lastlogin":"1448159933","lastsend":"0","attachsize":"0","addsize":"0","addfriend":"0","flag":"1","newpm":"0","avatar":"0","regip":"unknown","ip":"0","mood":"0","bwztnum":"3","avatar_url":"http://localhost/uc/ucenter/images/noavatar_small.gif","sex_org":"2","sex":"女","age":15}
     */

    private String m_auth;
    private String uhash;
    private String formhash;
    private SpaceEntity space;

    public void setM_auth(String m_auth) {
        this.m_auth = m_auth;
    }

    public void setUhash(String uhash) {
        this.uhash = uhash;
    }

    public void setFormhash(String formhash) {
        this.formhash = formhash;
    }

    public void setSpace(SpaceEntity space) {
        this.space = space;
    }

    public String getM_auth() {
        return m_auth;
    }

    public String getUhash() {
        return uhash;
    }

    public String getFormhash() {
        return formhash;
    }

    public SpaceEntity getSpace() {
        return space;
    }

    public static class SpaceEntity {
        /**
         * uid : 1
         * groupid : 1
         * credit : 85
         * experience : 75
         * username : root
         * name : 李四
         * namestatus : 1
         * videostatus : 0
         * domain :
         * friendnum : 0
         * viewnum : 2
         * notenum : 0
         * addfriendnum : 0
         * mtaginvitenum : 0
         * eventinvitenum : 0
         * myinvitenum : 0
         * pokenum : 0
         * doingnum : 0
         * blognum : 0
         * albumnum : 0
         * threadnum : 0
         * pollnum : 0
         * eventnum : 0
         * sharenum : 0
         * dateline : 1447915083
         * updatetime : 1447927867
         * lastsearch : 0
         * lastpost : 1447927756
         * lastlogin : 1448159933
         * lastsend : 0
         * attachsize : 0
         * addsize : 0
         * addfriend : 0
         * flag : 1
         * newpm : 0
         * avatar : 0
         * regip : unknown
         * ip : 0
         * mood : 0
         * bwztnum : 3
         * avatar_url : http://localhost/uc/ucenter/images/noavatar_small.gif
         * sex_org : 2
         * sex : 女
         * age : 15
         */

        private String uid;
        private String groupid;
        private String credit;
        private String experience;
        private String username;
        private String name;
        private String namestatus;
        private String videostatus;
        private String friendnum;
        private String viewnum;
        private String notenum;
        private String addfriendnum;
        private String blognum;
        private String albumnum;
        private String dateline;
        private String updatetime;
        private String mood;
        private String bwztnum;
        private String avatar_url;
        private String sex_org;
        private String sex;
        private int age;

        public void setUid(String uid) {
            this.uid = uid;
        }

        public void setGroupid(String groupid) {
            this.groupid = groupid;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setNamestatus(String namestatus) {
            this.namestatus = namestatus;
        }

        public void setVideostatus(String videostatus) {
            this.videostatus = videostatus;
        }

        public void setFriendnum(String friendnum) {
            this.friendnum = friendnum;
        }

        public void setViewnum(String viewnum) {
            this.viewnum = viewnum;
        }

        public void setNotenum(String notenum) {
            this.notenum = notenum;
        }

        public void setAddfriendnum(String addfriendnum) {
            this.addfriendnum = addfriendnum;
        }

        public void setBlognum(String blognum) {
            this.blognum = blognum;
        }

        public void setAlbumnum(String albumnum) {
            this.albumnum = albumnum;
        }

        public void setDateline(String dateline) {
            this.dateline = dateline;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public void setMood(String mood) {
            this.mood = mood;
        }

        public void setBwztnum(String bwztnum) {
            this.bwztnum = bwztnum;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public void setSex_org(String sex_org) {
            this.sex_org = sex_org;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getUid() {
            return uid;
        }

        public String getGroupid() {
            return groupid;
        }

        public String getCredit() {
            return credit;
        }

        public String getExperience() {
            return experience;
        }

        public String getUsername() {
            return username;
        }

        public String getName() {
            return name;
        }

        public String getNamestatus() {
            return namestatus;
        }

        public String getVideostatus() {
            return videostatus;
        }

        public String getFriendnum() {
            return friendnum;
        }

        public String getViewnum() {
            return viewnum;
        }

        public String getNotenum() {
            return notenum;
        }

        public String getAddfriendnum() {
            return addfriendnum;
        }

        public String getBlognum() {
            return blognum;
        }

        public String getAlbumnum() {
            return albumnum;
        }

        public String getDateline() {
            return dateline;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public String getMood() {
            return mood;
        }

        public String getBwztnum() {
            return bwztnum;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public String getSex_org() {
            return sex_org;
        }

        public String getSex() {
            return sex;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "SpaceEntity{" +
                    "uid='" + uid + '\'' +
                    ", groupid='" + groupid + '\'' +
                    ", credit='" + credit + '\'' +
                    ", experience='" + experience + '\'' +
                    ", username='" + username + '\'' +
                    ", name='" + name + '\'' +
                    ", namestatus='" + namestatus + '\'' +
                    ", videostatus='" + videostatus + '\'' +
                    ", friendnum='" + friendnum + '\'' +
                    ", viewnum='" + viewnum + '\'' +
                    ", notenum='" + notenum + '\'' +
                    ", addfriendnum='" + addfriendnum + '\'' +
                    ", blognum='" + blognum + '\'' +
                    ", albumnum='" + albumnum + '\'' +
                    ", dateline='" + dateline + '\'' +
                    ", updatetime='" + updatetime + '\'' +
                    ", mood='" + mood + '\'' +
                    ", bwztnum='" + bwztnum + '\'' +
                    ", avatar_url='" + avatar_url + '\'' +
                    ", sex_org='" + sex_org + '\'' +
                    ", sex='" + sex + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "m_auth='" + m_auth + '\'' +
                ", uhash='" + uhash + '\'' +
                ", formhash='" + formhash + '\'' +
                ", space=" + space +
                '}';
    }
    public LoginBean(){

    }
}
