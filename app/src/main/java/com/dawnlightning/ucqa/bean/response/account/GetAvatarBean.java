package com.dawnlightning.ucqa.bean.response.account;

/**
 * 作者：Administrator on 2016/5/15 18:23
 * 邮箱：823894716@qq.com
 * 获取头像返回结果
 */
public class GetAvatarBean {

    public  GetAvatarBean(){

    }
    /**
     * avatar_url : http://localhost/uc/ucenter/data/avatar/000/00/00/01_avatar_small.jpg
     */

    private String avatar_url;

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    @Override
    public String toString() {
        return "GetAvatarBean{" +
                "avatar_url='" + avatar_url + '\'' +
                '}';
    }
}
