package com.farwolf.view;

import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

import com.farwolf.view.imgae.crop.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengjiangrong on 2017/5/13.
 */

public abstract class OnMultiTouchListener implements View.OnTouchListener {



    int type=2;

    public OnMultiTouchListener(int type) {
        this.type = type;
    }


    List temp=new ArrayList();
    int delt=300;




    @Override
    public boolean onTouch(final View v, final MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            final long now = System.currentTimeMillis();
            if(temp.size()==0)
            {
                temp.add(now);
            }
            else
            {

                long last=  (long)temp.get(temp.size()-1);

                if(now-last>1000)
                {
                    Log.e("count="+temp.size()+",delt="+(now-last)+"清除");
                    temp.clear();
                    temp.add(now);
                    return true;
                }



                    if(now-last<=delt)
                    {
                        temp.add(now);

                         if(type==4)
                         {
                            if(temp.size()==2)
                            {
                                  new Thread(new Runnable() {
                                      @Override
                                      public void run() {
                                          try {
                                              Thread.sleep(delt+20);
                                              if(temp.size()==2)
                                                  handler.sendEmptyMessage(0);
                                          }
                                          catch (InterruptedException e)
                                          {
                                              e.printStackTrace();
                                          }

                                      }
                                  }).start();

                            }
                            else if(temp.size()==3)
                            {
                                onMultiTouch( v, 3,  event);
                                temp.clear();
                            }
                         }
                         else
                         {
                             if(temp.size()>=type)
                             {
                                 onMultiTouch( v, type,  event);
                                 temp.clear();

                             }
                             else
                             {
                                 temp.add(now);

                             }
                         }
                        return true;
                    }
                    else
                    {
                        temp.clear();
                        temp.add(now);
                    }

            }
        }
        return false;
    }


    Handler handler=new Handler()
    {
        @Override
        public void dispatchMessage(Message msg) {
            onMultiTouch( null, 2,  null);
        }
    };

    public abstract void onMultiTouch(View v,int count, MotionEvent event);
}
