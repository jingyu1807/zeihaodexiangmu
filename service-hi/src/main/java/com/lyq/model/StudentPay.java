package com.lyq.model;

import javax.persistence.*;


public class StudentPay {

    private  Integer id;

    private String name;

    private String city;

    private String  education;

    private Integer aogpay;

    private Integer laterpay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Integer getAogpay() {
        return aogpay;
    }

    public void setAogpay(Integer aogpay) {
        this.aogpay = aogpay;
    }

    public Integer getLaterpay() {
        return laterpay;
    }

    public void setLaterpay(Integer laterpay) {
        this.laterpay = laterpay;
    }
}
