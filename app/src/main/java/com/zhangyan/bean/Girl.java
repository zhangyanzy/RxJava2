package com.zhangyan.bean;

/**
 * Created by Administrator on 2018/3/29.
 */

public class Girl {
    private String name;
    private String start;


    public Girl() {
    }

    public Girl(String name, String start) {
        this.name = name;
        this.start = start;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }



    @Override
    public String toString() {
        return "Girl{" +
                "name='" + name + '\'' +
                ", start='" + start + '\'' +
                '}';
    }
}
