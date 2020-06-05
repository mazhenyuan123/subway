package com.xy.subway.bean;

public class Point {
    private Integer id;
    private String name;
    private Sort sort;
    private Double cvalue;
    private Double crate;
    private Data data;
    private Double ivalue;
    private String idate;

    public String getIdate() {
        return idate;
    }

    public void setIdate(String idate) {
        this.idate = idate;
    }


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

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Double getCvalue() {
        return cvalue;
    }

    public void setCvalue(Double cvalue) {
        this.cvalue = cvalue;
    }

    public Double getCrate() {
        return crate;
    }

    public void setCrate(Double crate) {
        this.crate = crate;
    }

    public Double getIvalue() {
        return ivalue;
    }

    public void setIvalue(Double ivalue) {
        this.ivalue = ivalue;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
