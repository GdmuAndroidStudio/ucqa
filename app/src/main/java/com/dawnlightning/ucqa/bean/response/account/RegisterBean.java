package com.dawnlightning.ucqa.bean.response.account;

/**
 * 作者：Administrator on 2016/5/15 18:26
 * 邮箱：823894716@qq.com
 * 注册返回结果
 */
public class RegisterBean {


    /**
     * code : 0
     * data : {"space":{"uid":"6","sex":"0","email":"test1@ucqa.cn","newemail":"","emailcheck":"0","mobile":"","qq":"","msn":"","msnrobot":"","msncstatus":"0","videopic":"","birthyear":"0","birthmonth":"0","birthday":"0","blood":"","marry":"0","birthprovince":"","birthcity":"","resideprovince":"","residecity":"","note":"","spacenote":"","authstr":"","theme":"","nocss":"0","menunum":"0","css":"","privacy":{"view":{"index":"0","profile":"0","friend":"0","wall":"0","feed":"0","mtag":"0","event":"0","doing":"0","blog":"0","album":"0","share":"0","poll":"0"},"feed":{"doing":1,"blog":1,"upload":1,"share":1,"poll":1,"joinpoll":1,"thread":1,"post":1,"mtag":1,"event":1,"join":1,"friend":1,"comment":1,"show":1,"spaceopen":1,"credit":1,"invite":1,"task":1,"profile":1,"album":1,"click":1}},"friend":"","feedfriend":"","sendmail":"","magicstar":"0","magicexpire":"0","timeoffset":"","groupid":"0","credit":"25","experience":"15","username":"test1","name":"","namestatus":"0","videostatus":"0","domain":"","friendnum":"0","viewnum":"0","notenum":"0","addfriendnum":"0","mtaginvitenum":"0","eventinvitenum":"0","myinvitenum":"0","pokenum":"0","doingnum":"0","blognum":"0","bwztnum":"0","albumnum":"0","threadnum":"0","pollnum":"0","eventnum":"0","sharenum":"0","dateline":"1435161273","updatetime":"0","lastsearch":"0","lastpost":"0","lastlogin":"1435161273","lastsend":"0","attachsize":"0","addsize":"0","addfriend":"0","flag":"0","newpm":"0","avatar":"0","regip":"222.16.97.129","ip":"222016097","mood":"0","self":1,"allnotenum":0},"m_auth":"204daENB5ey1olPXDZ1ZSNPUW0%2B4M7rNmMpZ4dM9nhaTrX2FVSZgoIxLrv%2BPSsgwJnZfUEsls6rYoJGQHQog"}
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
        private String m_auth;

        public void setM_auth(String m_auth) {
            this.m_auth = m_auth;
        }

        public String getM_auth() {
            return m_auth;
        }
    }
}
