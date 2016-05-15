package com.dawnlightning.ucqa.bean.response.account;

/**
 * 作者：Administrator on 2016/5/15 18:32
 * 邮箱：823894716@qq.com
 * 登陆返回结果
 */
public class LoginBean {

    /**
     * code : 0
     * data : {"m_auth":"4e64L1WXZV%2B0TDmRgCsOe050x1WCD5EGhnZpxlUx7RcoTEA5w10e9dVL1wSLvnSCuv4nFRqBGKOCmf%2F37AKP","uhash":"e8fee782f2e7ddce363edd0979dbd4f8","formhash":"31bce83a","space":{"uid":"1","groupid":"1","credit":"85","experience":"75","username":"root","name":"李四","namestatus":"1","friendnum":"0","viewnum":"2","notenum":"0","bwztnum":"3","avatar_url":"http://localhost/uc/ucenter/images/noavatar_small.gif","sex_org":"2","sex":"女","age":15}}
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
         * space : {"uid":"1","groupid":"1","credit":"85","experience":"75","username":"root","name":"李四","namestatus":"1","friendnum":"0","viewnum":"2","notenum":"0","bwztnum":"3","avatar_url":"http://localhost/uc/ucenter/images/noavatar_small.gif","sex_org":"2","sex":"女","age":15}
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
             * friendnum : 0
             * viewnum : 2
             * notenum : 0
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
            private String friendnum;
            private String viewnum;
            private String notenum;
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

            public void setFriendnum(String friendnum) {
                this.friendnum = friendnum;
            }

            public void setViewnum(String viewnum) {
                this.viewnum = viewnum;
            }

            public void setNotenum(String notenum) {
                this.notenum = notenum;
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

            public String getFriendnum() {
                return friendnum;
            }

            public String getViewnum() {
                return viewnum;
            }

            public String getNotenum() {
                return notenum;
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
}
