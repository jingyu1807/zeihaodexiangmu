package com.lyq.model;

import javax.persistence.*;

//章管理
@Entity
@Table(name="t_zhang")
public class Zhang {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer zid; //主键Id

    private String zname;//章名称

    private Integer mid; //对应目录id
    //
    @Transient
    private String mname;//目录名称

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public Integer getZid() {
        return zid;
    }

    public void setZid(Integer zid) {
        this.zid = zid;
    }

    public String getZname() {
        return zname;
    }

    public void setZname(String zname) {
        this.zname = zname;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }
}
