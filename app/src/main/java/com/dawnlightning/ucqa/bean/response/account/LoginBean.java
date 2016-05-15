package com.dawnlightning.ucqa.bean.response.account;

/**
 * 作者：Administrator on 2016/5/15 18:32
 * 邮箱：823894716@qq.com
 * 登陆返回结果
 */
public class LoginBean {

    /**
     * code : 0
     * data : {"m_auth":"4e64L1WXZV%2B0TDmRgCsOe050x1WCD5EGhnZpxlUx7RcoTEA5w10e9dVL1wSLvnSCuv4nFRqBGKOCmf%2F37AKP","uhash":"e8fee782f2e7ddce363edd0979dbd4f8","formhash":"31bce83a","space":{"uid":"1","groupid":"1","credit":"85","experience":"75","username":"root","name":"李四","namestatus":"1","videostatus":"0","domain":"","friendnum":"0","viewnum":"2","notenum":"0","addfriendnum":"0","mtaginvitenum":"0","eventinvitenum":"0","myinvitenum":"0","pokenum":"0","doingnum":"0","blognum":"0","albumnum":"0","threadnum":"0","pollnum":"0","eventnum":"0","sharenum":"0","dateline":"1447915083","updatetime":"1447927867","lastsearch":"0","lastpost":"1447927756","lastlogin":"1448159933","lastsend":"0","attachsize":"0","addsize":"0","addfriend":"0","flag":"1","newpm":"0","avatar":"0","regip":"unknown","ip":"0","mood":"0","bwztnum":"3","avatar_url":"http://localhost/uc/ucenter/images/noavatar_small.gif","sex_org":"2","sex":"女","age":15}}
     * msg : 登录成功了，现在引导您进入登录前页面 \1
     * action : login_success
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
            private String domain;
            private String friendnum;
            private String viewnum;
            private String notenum;
            private String addfriendnum;
            private String mtaginvitenum;
            private String eventinvitenum;
            private String myinvitenum;
            private String pokenum;
            private String doingnum;
            private String blognum;
            private String albumnum;
            private String threadnum;
            private String pollnum;
            private String eventnum;
            private String sharenum;
            private String dateline;
            private String updatetime;
            private String lastsearch;
            private String lastpost;
            private String lastlogin;
            private String lastsend;
            private String attachsize;
            private String addsize;
            private String addfriend;
            private String flag;
            private String newpm;
            private String avatar;
            private String regip;
            private String ip;
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

            public void setDomain(String domain) {
                this.domain = domain;
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

            public void setMtaginvitenum(String mtaginvitenum) {
                this.mtaginvitenum = mtaginvitenum;
            }

            public void setEventinvitenum(String eventinvitenum) {
                this.eventinvitenum = eventinvitenum;
            }

            public void setMyinvitenum(String myinvitenum) {
                this.myinvitenum = myinvitenum;
            }

            public void setPokenum(String pokenum) {
                this.pokenum = pokenum;
            }

            public void setDoingnum(String doingnum) {
                this.doingnum = doingnum;
            }

            public void setBlognum(String blognum) {
                this.blognum = blognum;
            }

            public void setAlbumnum(String albumnum) {
                this.albumnum = albumnum;
            }

            public void setThreadnum(String threadnum) {
                this.threadnum = threadnum;
            }

            public void setPollnum(String pollnum) {
                this.pollnum = pollnum;
            }

            public void setEventnum(String eventnum) {
                this.eventnum = eventnum;
            }

            public void setSharenum(String sharenum) {
                this.sharenum = sharenum;
            }

            public void setDateline(String dateline) {
                this.dateline = dateline;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }

            public void setLastsearch(String lastsearch) {
                this.lastsearch = lastsearch;
            }

            public void setLastpost(String lastpost) {
                this.lastpost = lastpost;
            }

            public void setLastlogin(String lastlogin) {
                this.lastlogin = lastlogin;
            }

            public void setLastsend(String lastsend) {
                this.lastsend = lastsend;
            }

            public void setAttachsize(String attachsize) {
                this.attachsize = attachsize;
            }

            public void setAddsize(String addsize) {
                this.addsize = addsize;
            }

            public void setAddfriend(String addfriend) {
                this.addfriend = addfriend;
            }

            public void setFlag(String flag) {
                this.flag = flag;
            }

            public void setNewpm(String newpm) {
                this.newpm = newpm;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public void setRegip(String regip) {
                this.regip = regip;
            }

            public void setIp(String ip) {
                this.ip = ip;
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

            public String getDomain() {
                return domain;
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

            public String getMtaginvitenum() {
                return mtaginvitenum;
            }

            public String getEventinvitenum() {
                return eventinvitenum;
            }

            public String getMyinvitenum() {
                return myinvitenum;
            }

            public String getPokenum() {
                return pokenum;
            }

            public String getDoingnum() {
                return doingnum;
            }

            public String getBlognum() {
                return blognum;
            }

            public String getAlbumnum() {
                return albumnum;
            }

            public String getThreadnum() {
                return threadnum;
            }

            public String getPollnum() {
                return pollnum;
            }

            public String getEventnum() {
                return eventnum;
            }

            public String getSharenum() {
                return sharenum;
            }

            public String getDateline() {
                return dateline;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public String getLastsearch() {
                return lastsearch;
            }

            public String getLastpost() {
                return lastpost;
            }

            public String getLastlogin() {
                return lastlogin;
            }

            public String getLastsend() {
                return lastsend;
            }

            public String getAttachsize() {
                return attachsize;
            }

            public String getAddsize() {
                return addsize;
            }

            public String getAddfriend() {
                return addfriend;
            }

            public String getFlag() {
                return flag;
            }

            public String getNewpm() {
                return newpm;
            }

            public String getAvatar() {
                return avatar;
            }

            public String getRegip() {
                return regip;
            }

            public String getIp() {
                return ip;
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
        }
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
