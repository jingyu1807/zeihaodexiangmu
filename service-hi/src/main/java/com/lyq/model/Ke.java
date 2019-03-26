package com.lyq.model;

import javax.persistence.*;

//课程管理
@Entity
@Table(name="t_ke")
public class Ke {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer kid; //主键Id

    private Integer kvip; //VIP

    private String kimg; //图片

    private String kname;//课程名称

    private String kinfo; //课程信息


    public Integer getKvip() {
        return kvip;
    }

    public void setKvip(Integer kvip) {
        this.kvip = kvip;
    }

    public String getKimg() {
        return kimg;
    }

    public void setKimg(String kimg) {
        this.kimg = kimg;
    }

    public Integer getKid() {
        return kid;
    }

    public void setKid(Integer kid) {
        this.kid = kid;
    }

    public String getKname() {
        return kname;
    }

    public void setKname(String kname) {
        this.kname = kname;
    }

    public String getKinfo() {
        return kinfo;
    }

    public void setKinfo(String kinfo) {
        this.kinfo = kinfo;
    }
}
