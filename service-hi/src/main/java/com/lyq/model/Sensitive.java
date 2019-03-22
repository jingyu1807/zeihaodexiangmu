package com.lyq.model;

import javax.persistence.*;

//敏感词管理
@Entity
@Table(name="t_sensitive")
public class Sensitive {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id; //主键Id
    private String code;
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
