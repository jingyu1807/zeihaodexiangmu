package com.lyq.model;

import javax.persistence.*;

//目录管理
@Entity
@Table(name="t_mu")
public class Mu {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer mid; //主键Id

    private String mname;//目录名称

    private Integer kid; //对应课程id

    //
    @Transient
    private String kname;//课程名称

    public String getKname() {
        return kname;
    }

    public void setKname(String kname) {
        this.kname = kname;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public Integer getKid() {
        return kid;
    }

    public void setKid(Integer kid) {
        this.kid = kid;
    }
}
