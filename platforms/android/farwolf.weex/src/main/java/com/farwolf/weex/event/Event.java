package com.farwolf.weex.event;

import java.util.HashMap;

/**
 * Created by zhengjiangrong on 2017/5/18.
 */

public class Event {


    public Event(String key, HashMap param) {
        this.param = param;
        this.key = key;
    }

    public HashMap param;

    public String key;



}
