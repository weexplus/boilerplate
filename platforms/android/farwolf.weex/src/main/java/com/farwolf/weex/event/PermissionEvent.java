package com.farwolf.weex.event;

/**
 * Created by zhengjiangrong on 2018/2/12.
 */

public class PermissionEvent {


    public final  static int   CAMREA=1011;

    public PermissionEvent(int type) {
        this.type = type;
    }

    public int type;


}
