package com.farwolf.jpush.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.farwolf.weex.bean.Config;
import com.farwolf.weex.core.Page;
import com.farwolf.weex.core.WeexFactory;
import com.farwolf.weex.core.WeexFactory_;
import com.farwolf.weex.core.local.Local;

import java.util.HashMap;

import static cn.jpush.android.api.JPushInterface.ACTION_NOTIFICATION_OPENED;
import static cn.jpush.android.api.JPushInterface.ACTION_NOTIFICATION_RECEIVED;
import static cn.jpush.android.api.JPushInterface.EXTRA_ALERT;
import static cn.jpush.android.api.JPushInterface.EXTRA_EXTRA;

/**
 * Created by zhengjiangrong on 2018/4/21.
 */

public class JPushAdapter  {

   public void onReceive(Context context, Intent intent)
   {


       String action=intent.getAction();
       if(!Local.isDiskExist(context))
       {
           return;
       }
       if(!ACTION_NOTIFICATION_OPENED.equals(action)&&!ACTION_NOTIFICATION_RECEIVED.equals(action))
       {
           return;
       }
       if(ACTION_NOTIFICATION_OPENED.equals(action))
       {
           action="open";
       }
       else
       {
           action="receive";
       }



         String entry= Config.notifyEntry(context);
         entry=entry.replace("root:","app/");
         WeexFactory factory= WeexFactory_.getInstance_(context);
          final HashMap m=new HashMap();

          Bundle bundle = intent.getExtras();
          m.put("msg",bundle.get(EXTRA_ALERT));
          m.put("extra",bundle.get(EXTRA_EXTRA));

          m.put("action",action);
         factory.preRender(entry, new WeexFactory.OnRenderFinishListener() {
         @Override
         public void onRenderFinish(Page p) {

             p.instance.param=m;
            p.instance.firePageInit();

         }

         @Override
         public void onRenderFailed(Page p) {

         }
      });
   }
}
