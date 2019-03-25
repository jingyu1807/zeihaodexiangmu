package com.lyq.model;


import javax.persistence.*;

//小节管理
@Entity
@Table(name="t_jie")
public class Jie {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer jid; //主键Id

    private String jname;//小节名称

    private Integer zid; //对应章id

    private Integer ynvip; //是否vip

    @Transient
    private String zname;//章名称

    public String getZname() {
        return zname;
    }

    public void setZname(String zname) {
        this.zname = zname;
    }

    public Integer getJid() {
        return jid;
    }

    public void setJid(Integer jid) {
        this.jid = jid;
    }

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }

    public Integer getZid() {
        return zid;
    }

    public void setZid(Integer zid) {
        this.zid = zid;
    }

    public Integer getYnvip() {
        return ynvip;
    }

    public void setYnvip(Integer ynvip) {
        this.ynvip = ynvip;
    }
}
