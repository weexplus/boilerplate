package com.taobao.weex.event;

/**
 * Created by zhengjiangrong on 2018/3/30.
 */

public class ErrorEvent {

    public String msg;
    public String type;
    public String level;

    public ErrorEvent(String msg) {
        this.msg = msg;
    }
}
