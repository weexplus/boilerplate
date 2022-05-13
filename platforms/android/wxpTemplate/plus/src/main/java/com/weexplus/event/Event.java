package com.weexplus.event;

import java.util.HashMap;

/**
 * Created by zhengjiangrong on 2017/5/18.
 */

public class Event {




    public HashMap param;

    public String key;

    public Event(String key, String module,HashMap param ) {
        this.param = param;
        this.key = key;
        this.module = module;
    }

    public String module;



}
