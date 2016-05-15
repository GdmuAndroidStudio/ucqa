package com.dawnlightning.ucqa.bean.response.account;

/**
 * 作者：Administrator on 2016/5/15 18:26
 * 邮箱：823894716@qq.com
 * 注册返回结果
 */
public class RegisterBean {

    /**
     * code : 0
     * data : {"space":{"uid":"6","sex":"0","email":"test1@ucqa.cn","newemail":"","emailcheck":"0","mobile":"","qq":"","msn":"","msnrobot":"","msncstatus":"0","videopic":"","birthyear":"0","birthmonth":"0","birthday":"0","blood":"","marry":"0","birthprovince":"","birthcity":"","resideprovince":"","residecity":"","note":"","spacenote":"","authstr":"","theme":"","nocss":"0","menunum":"0","css":"","allnotenum":0},"m_auth":"204daENB5ey1olPXDZ1ZSNPUW0%2B4M7rNmMpZ4dM9nhaTrX2FVSZgoIxLrv%2BPSsgwJnZfUEsls6rYoJGQHQog"}
     * msg : 注册成功了，进入个人空间
     * action : registered
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
         * space : {"uid":"6","sex":"0","email":"test1@ucqa.cn","newemail":"","emailcheck":"0","mobile":"","qq":"","msn":"","msnrobot":"","msncstatus":"0","videopic":"","birthyear":"0","birthmonth":"0","birthday":"0","blood":"","marry":"0","birthprovince":"","birthcity":"","resideprovince":"","residecity":"","note":"","spacenote":"","authstr":"","theme":"","nocss":"0","menunum":"0","css":"","allnotenum":0}
         * m_auth : 204daENB5ey1olPXDZ1ZSNPUW0%2B4M7rNmMpZ4dM9nhaTrX2FVSZgoIxLrv%2BPSsgwJnZfUEsls6rYoJGQHQog
         */

        private SpaceEntity space;
        private String m_auth;

        public void setSpace(SpaceEntity space) {
            this.space = space;
        }

        public void setM_auth(String m_auth) {
            this.m_auth = m_auth;
        }

        public SpaceEntity getSpace() {
            return space;
        }

        public String getM_auth() {
            return m_auth;
        }

        public static class SpaceEntity {
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
            private String msnrobot;
            private String msncstatus;
            private String videopic;
            private String birthyear;
            private String birthmonth;
            private String birthday;
            private String blood;
            private String marry;
            private String birthprovince;
            private String birthcity;
            private String resideprovince;
            private String residecity;
            private String note;
            private String spacenote;
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

            public void setMsnrobot(String msnrobot) {
                this.msnrobot = msnrobot;
            }

            public void setMsncstatus(String msncstatus) {
                this.msncstatus = msncstatus;
            }

            public void setVideopic(String videopic) {
                this.videopic = videopic;
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

            public void setMarry(String marry) {
                this.marry = marry;
            }

            public void setBirthprovince(String birthprovince) {
                this.birthprovince = birthprovince;
            }

            public void setBirthcity(String birthcity) {
                this.birthcity = birthcity;
            }

            public void setResideprovince(String resideprovince) {
                this.resideprovince = resideprovince;
            }

            public void setResidecity(String residecity) {
                this.residecity = residecity;
            }

            public void setNote(String note) {
                this.note = note;
            }

            public void setSpacenote(String spacenote) {
                this.spacenote = spacenote;
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

            public String getMsnrobot() {
                return msnrobot;
            }

            public String getMsncstatus() {
                return msncstatus;
            }

            public String getVideopic() {
                return videopic;
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

            public String getMarry() {
                return marry;
            }

            public String getBirthprovince() {
                return birthprovince;
            }

            public String getBirthcity() {
                return birthcity;
            }

            public String getResideprovince() {
                return resideprovince;
            }

            public String getResidecity() {
                return residecity;
            }

            public String getNote() {
                return note;
            }

            public String getSpacenote() {
                return spacenote;
            }

            public int getAllnotenum() {
                return allnotenum;
            }
        }
    }
}
