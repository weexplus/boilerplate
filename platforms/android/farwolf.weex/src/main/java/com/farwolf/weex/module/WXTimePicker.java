package com.farwolf.weex.module;

import com.farwolf.util.DateTool;
import com.farwolf.view.CustomDatePicker;
import com.farwolf.weex.base.WXModuleBase;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by zhengjiangrong on 2018/5/7.
 */

public class WXTimePicker extends WXModuleBase {


    @JSMethod
    public void pickTime(HashMap param, final JSCallback callback)
    {


        Date d=new Date();
        Calendar ca=Calendar.getInstance();
        ca.add(Calendar.YEAR,10);

        CustomDatePicker customDatePicker2 = new CustomDatePicker(getContext(), new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间

                HashMap res=new HashMap();
                res.put("result","success");
                res.put("data",time);
                callback.invoke(res);
            }
        }, "1900-01-01 00:00",  DateTool.Pattern(ca.getTime(),"yyyy-MM-dd HH:mm")); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker2.showSpecificTime(true); // 显示时和分
        customDatePicker2.setIsLoop(true); // 允许循环滚动
        customDatePicker2.show( DateTool.Pattern(new Date(),"yyyy-MM-dd HH:mm"));

    }

}
