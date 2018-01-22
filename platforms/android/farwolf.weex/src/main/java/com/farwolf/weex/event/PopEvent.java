package com.farwolf.weex.event;

import java.util.HashMap;

/**
 * Created by zhengjiangrong on 2018/1/3.
 */

public class PopEvent {


    public String type;

    public HashMap param;

    public PopEvent(String type) {
        this.type = type;
    }
    public PopEvent(String type, HashMap param) {
        this.type = type;
        this.param = param;
    }



}
