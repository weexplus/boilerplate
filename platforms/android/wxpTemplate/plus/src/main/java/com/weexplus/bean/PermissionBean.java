package com.weexplus.bean;

public class PermissionBean {


    public String key;
    public String name;

    public PermissionBean(String key, String name, String des) {
        this.key = key;
        this.name = name;
        this.des = des;
    }




    String des;

    public String getKey() {
        return key;
    }

    public PermissionBean(String key, String des) {
        this.key = key;
        this.des = des;
    }


    public String getName() {
        return name;
    }

    public String getDes() {
        return des;
    }


}
