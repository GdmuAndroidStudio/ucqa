package com.dawnlightning.ucqa.bean.response.account;

import com.google.gson.JsonObject;

/**
 * 作者：Administrator on 2016/5/15 18:26
 * 邮箱：823894716@qq.com
 * 注册返回结果
 */
public class RegisterBean {

    public RegisterBean(){

    }
    /**
     * space : {"uid":"6","sex":"0","email":"test1@ucqa.cn","newemail":"","emailcheck":"0","mobile":"","qq":"","msn":"","msnrobot":"","msncstatus":"0","videopic":"","birthyear":"0","birthmonth":"0","birthday":"0","blood":"","marry":"0","birthprovince":"","birthcity":"","resideprovince":"","residecity":"","note":"","spacenote":"","authstr":"","theme":"","nocss":"0","menunum":"0","css":"","privacy":{"view":{"index":"0","profile":"0","friend":"0","wall":"0","feed":"0","mtag":"0","event":"0","doing":"0","blog":"0","album":"0","share":"0","poll":"0"},"feed":{"doing":1,"blog":1,"upload":1,"share":1,"poll":1,"joinpoll":1,"thread":1,"post":1,"mtag":1,"event":1,"join":1,"friend":1,"comment":1,"show":1,"spaceopen":1,"credit":1,"invite":1,"task":1,"profile":1,"album":1,"click":1}},"friend":"","feedfriend":"","sendmail":"","magicstar":"0","magicexpire":"0","timeoffset":"","groupid":"0","credit":"25","experience":"15","username":"test1","name":"","namestatus":"0","videostatus":"0","domain":"","friendnum":"0","viewnum":"0","notenum":"0","addfriendnum":"0","mtaginvitenum":"0","eventinvitenum":"0","myinvitenum":"0","pokenum":"0","doingnum":"0","blognum":"0","bwztnum":"0","albumnum":"0","threadnum":"0","pollnum":"0","eventnum":"0","sharenum":"0","dateline":"1435161273","updatetime":"0","lastsearch":"0","lastpost":"0","lastlogin":"1435161273","lastsend":"0","attachsize":"0","addsize":"0","addfriend":"0","flag":"0","newpm":"0","avatar":"0","regip":"222.16.97.129","ip":"222016097","mood":"0","self":1,"allnotenum":0}
     * m_auth : 204daENB5ey1olPXDZ1ZSNPUW0%2B4M7rNmMpZ4dM9nhaTrX2FVSZgoIxLrv%2BPSsgwJnZfUEsls6rYoJGQHQog
     */

    private JsonObject space;
    private String m_auth;



    public void setM_auth(String m_auth) {
        this.m_auth = m_auth;
    }

    public JsonObject getSpace() {
        return space;
    }

    public void setSpace(JsonObject space) {
        this.space = space;
    }

    public String getM_auth() {
        return m_auth;
    }

    public static class SpaceEntity {
        @Override
        public String toString() {
            return "SpaceEntity{" +
                    "uid='" + uid + '\'' +
                    ", sex='" + sex + '\'' +
                    ", email='" + email + '\'' +
                    ", newemail='" + newemail + '\'' +
                    ", emailcheck='" + emailcheck + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", qq='" + qq + '\'' +
                    ", msn='" + msn + '\'' +
                    ", birthyear='" + birthyear + '\'' +
                    ", birthmonth='" + birthmonth + '\'' +
                    ", birthday='" + birthday + '\'' +
                    ", blood='" + blood + '\'' +
                    ", birthprovince='" + birthprovince + '\'' +
                    ", birthcity='" + birthcity + '\'' +
                    ", friend='" + friend + '\'' +
                    ", feedfriend='" + feedfriend + '\'' +
                    ", sendmail='" + sendmail + '\'' +
                    ", magicstar='" + magicstar + '\'' +
                    ", magicexpire='" + magicexpire + '\'' +
                    ", groupid='" + groupid + '\'' +
                    ", credit='" + credit + '\'' +
                    ", experience='" + experience + '\'' +
                    ", username='" + username + '\'' +
                    ", name='" + name + '\'' +
                    ", namestatus='" + namestatus + '\'' +
                    ", friendnum='" + friendnum + '\'' +
                    ", viewnum='" + viewnum + '\'' +
                    ", notenum='" + notenum + '\'' +
                    ", dateline='" + dateline + '\'' +
                    ", allnotenum=" + allnotenum +
                    '}';
        }

        /**
         * uid : 6
         * sex : 0
         * email : test1@ucqa.cn
         * newemail :
         * emailcheck : 0
         * mobile :
         * qq :
         * msn :
         * msnrobot :
         * msncstatus : 0
         * videopic :
         * birthyear : 0
         * birthmonth : 0
         * birthday : 0
         * blood :
         * marry : 0
         * birthprovince :
         * birthcity :
         * resideprovince :
         * residecity :
         * note :
         * spacenote :
         * authstr :
         * theme :
         * nocss : 0
         * menunum : 0
         * css :
         * privacy : {"view":{"index":"0","profile":"0","friend":"0","wall":"0","feed":"0","mtag":"0","event":"0","doing":"0","blog":"0","album":"0","share":"0","poll":"0"},"feed":{"doing":1,"blog":1,"upload":1,"share":1,"poll":1,"joinpoll":1,"thread":1,"post":1,"mtag":1,"event":1,"join":1,"friend":1,"comment":1,"show":1,"spaceopen":1,"credit":1,"invite":1,"task":1,"profile":1,"album":1,"click":1}}
         * friend :
         * feedfriend :
         * sendmail :
         * magicstar : 0
         * magicexpire : 0
         * timeoffset :
         * groupid : 0
         * credit : 25
         * experience : 15
         * username : test1
         * name :
         * namestatus : 0
         * videostatus : 0
         * domain :
         * friendnum : 0
         * viewnum : 0
         * notenum : 0
         * addfriendnum : 0
         * mtaginvitenum : 0
         * eventinvitenum : 0
         * myinvitenum : 0
         * pokenum : 0
         * doingnum : 0
         * blognum : 0
         * bwztnum : 0
         * albumnum : 0
         * threadnum : 0
         * pollnum : 0
         * eventnum : 0
         * sharenum : 0
         * dateline : 1435161273
         * updatetime : 0
         * lastsearch : 0
         * lastpost : 0
         * lastlogin : 1435161273
         * lastsend : 0
         * attachsize : 0
         * addsize : 0
         * addfriend : 0
         * flag : 0
         * newpm : 0
         * avatar : 0
         * regip : 222.16.97.129
         * ip : 222016097
         * mood : 0
         * self : 1
         * allnotenum : 0
         */

        private String uid;
        private String sex;
        private String email;
        private String newemail;
        private String emailcheck;
        private String mobile;
        private String qq;
        private String msn;
        private String birthyear;
        private String birthmonth;
        private String birthday;
        private String blood;
        private String birthprovince;
        private String birthcity;
        private String friend;
        private String feedfriend;
        private String sendmail;
        private String magicstar;
        private String magicexpire;
        private String groupid;
        private String credit;
        private String experience;
        private String username;
        private String name;
        private String namestatus;
        private String friendnum;
        private String viewnum;
        private String notenum;
        private String dateline;
        private int allnotenum;

        public void setUid(String uid) {
            this.uid = uid;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setNewemail(String newemail) {
            this.newemail = newemail;
        }

        public void setEmailcheck(String emailcheck) {
            this.emailcheck = emailcheck;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public void setMsn(String msn) {
            this.msn = msn;
        }

        public void setBirthyear(String birthyear) {
            this.birthyear = birthyear;
        }

        public void setBirthmonth(String birthmonth) {
            this.birthmonth = birthmonth;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public void setBlood(String blood) {
            this.blood = blood;
        }

        public void setBirthprovince(String birthprovince) {
            this.birthprovince = birthprovince;
        }

        public void setBirthcity(String birthcity) {
            this.birthcity = birthcity;
        }

        public void setFriend(String friend) {
            this.friend = friend;
        }

        public void setFeedfriend(String feedfriend) {
            this.feedfriend = feedfriend;
        }

        public void setSendmail(String sendmail) {
            this.sendmail = sendmail;
        }

        public void setMagicstar(String magicstar) {
            this.magicstar = magicstar;
        }

        public void setMagicexpire(String magicexpire) {
            this.magicexpire = magicexpire;
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

        public void setDateline(String dateline) {
            this.dateline = dateline;
        }

        public void setAllnotenum(int allnotenum) {
            this.allnotenum = allnotenum;
        }

        public String getUid() {
            return uid;
        }

        public String getSex() {
            return sex;
        }

        public String getEmail() {
            return email;
        }

        public String getNewemail() {
            return newemail;
        }

        public String getEmailcheck() {
            return emailcheck;
        }

        public String getMobile() {
            return mobile;
        }

        public String getQq() {
            return qq;
        }

        public String getMsn() {
            return msn;
        }

        public String getBirthyear() {
            return birthyear;
        }

        public String getBirthmonth() {
            return birthmonth;
        }

        public String getBirthday() {
            return birthday;
        }

        public String getBlood() {
            return blood;
        }

        public String getBirthprovince() {
            return birthprovince;
        }

        public String getBirthcity() {
            return birthcity;
        }

        public String getFriend() {
            return friend;
        }

        public String getFeedfriend() {
            return feedfriend;
        }

        public String getSendmail() {
            return sendmail;
        }

        public String getMagicstar() {
            return magicstar;
        }

        public String getMagicexpire() {
            return magicexpire;
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

        public String getDateline() {
            return dateline;
        }

        public int getAllnotenum() {
            return allnotenum;
        }

    }

    @Override
    public String toString() {
        return "RegisterBean{" +
                "space=" + space +
                ", m_auth='" + m_auth + '\'' +
                '}';
    }
}
