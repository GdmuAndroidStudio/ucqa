package com.dawnlightning.ucqa.bean.response.consult;

/**
 * 作者：Administrator on 2016/5/15 18:39
 * 邮箱：823894716@qq.com
 * 咨询分类接口返回
 */
public class ClassifyBean {
    private   String  bwztclassarrname;//症状
    private int bwztclassarrid;//症状
    private     String bwztdivisionarrname;//科室
    private int bwztdivisionarrid;
    public String getBwztclassarrname() {
        return bwztclassarrname;
    }

    public void setBwztclassarrname(String bwztclassarrname) {
        this.bwztclassarrname = bwztclassarrname;
    }

    public int getBwztclassarrid() {
        return bwztclassarrid;
    }

    public void setBwztclassarrid(String bwztclassarrid) {
        this.bwztclassarrid = Integer.parseInt(bwztclassarrid);
    }

    public String getBwztdivisionarrname() {
        return bwztdivisionarrname;
    }

    public void setBwztdivisionarrname(String bwztdivisionarrname) {
        this.bwztdivisionarrname = bwztdivisionarrname;
    }

    public int getBwztdivisionarrid() {
        return bwztdivisionarrid;
    }

    public void setBwztdivisionarrid(String bwztdivisionarrid) {
        this.bwztdivisionarrid = Integer.parseInt(bwztdivisionarrid);
    }

    public ClassifyBean(String bwztclassarrname, String bwztclassarrid, String bwztdivisionarrname, String bwztdivisionarrid) {

        this.bwztclassarrname = bwztclassarrname;
        this.bwztclassarrid = Integer.parseInt(bwztclassarrid);
        this.bwztdivisionarrname = bwztdivisionarrname;
        this.bwztdivisionarrid = Integer.parseInt(bwztdivisionarrid);
    }
    public ClassifyBean(){

    }

    @Override
    public String toString() {
        return "ConsultClassifyBean{" +
                "bwztclassarrname='" + bwztclassarrname + '\'' +
                ", bwztclassarrid=" + bwztclassarrid +
                ", bwztdivisionarrname='" + bwztdivisionarrname + '\'' +
                ", bwztdivisionarrid=" + bwztdivisionarrid +
                '}';
    }
}
