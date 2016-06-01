package com.dawnlightning.ucqa.bean.others;

import java.io.Serializable;

/**
 * Created by Kyo on 2016/5/28.
 */
public class BannerBean implements Serializable {

    int id;//banner中item的id
    String uri;//跳转网页的uri
    String img;//banner的图片地址

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


}
