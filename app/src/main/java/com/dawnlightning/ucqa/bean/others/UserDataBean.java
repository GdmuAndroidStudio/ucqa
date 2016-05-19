package com.dawnlightning.ucqa.bean.others;

/**
 * 作者：Administrator on 2016/5/18 19:12
 * 邮箱：823894716@qq.com
 */
public class UserDataBean {
//    profilesubmit -- 必须为true
//    formhash -- formhash
//    name -- doctor
//    sex -- 性别代码，性别一旦设定将不能修改，1:男, 2:女
//    birthyear -- 出生年份
//    birthmonth -- 出生月份
//    birthday -- 出生日期
   private String prfilesubmit="true";
    private String formhash;
    private String name;
    private int sex;

    public UserDataBean(String formhash, String name, int sex, int birthyear, int birthmonth, int birthday) {
        this.formhash = formhash;
        this.name = name;
        this.sex = sex;
        this.birthyear = birthyear;
        this.birthmonth = birthmonth;
        this.birthday = birthday;
    }
    public UserDataBean(){

    }
    public String getFormhash() {

        return formhash;
    }

    public void setFormhash(String formhash) {
        this.formhash = formhash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(int birthyear) {
        this.birthyear = birthyear;
    }

    public int getBirthmonth() {
        return birthmonth;
    }

    public void setBirthmonth(int birthmonth) {
        this.birthmonth = birthmonth;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    private int birthyear;
    private int birthmonth;
    private int birthday;

}
