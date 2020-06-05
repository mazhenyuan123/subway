package com.xy.subway.bean;


import com.alibaba.excel.annotation.ExcelProperty;

public class Data {
    private Integer id;
    @ExcelProperty("测点编号")
    private Integer cid;
    private String personnel;

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    private String time;
    @ExcelProperty("测量值")
    private Double value;
    private Integer safe;
    private Double rate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getSafe() {
        return safe;
    }

    public void setSafe(Integer safe) {
        this.safe = safe;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", cid=" + cid +
                ", personnel='" + personnel + '\'' +
                ", time='" + time + '\'' +
                ", value='" + value + '\'' +
                ", safe='" + safe + '\'' +
                ", rate='" + rate + '\'' +
                '}';
    }
}
