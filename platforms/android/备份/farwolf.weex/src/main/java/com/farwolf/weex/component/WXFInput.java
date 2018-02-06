package com.farwolf.weex.component;

import android.view.ViewGroup;
import android.widget.EditText;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXInput;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.view.WXEditText;

import java.util.List;

/**
 * Created by zhengjiangrong on 2017/12/13.
 */

public class WXFInput extends WXInput {


    public WXFInput(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, boolean isLazy) {
        super(instance, dom, parent, isLazy);
    }


    @Override
    protected void onHostViewInitialized(WXEditText host) {
//        super.onHostViewInitialized(host);
//        host.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                if(i== KeyEvent.KEYCODE_MEDIA_NEXT)
//                {
//                    WeexActivity a=  (WeexActivity)getContext();
//                    List<EditText> l=new ArrayList<>();
//                    find(a.root,l);
//                    int index=l.indexOf(WXFInput.this);
//                    index++;
//                    if(index<l.size())
//                    {
//                        l.get(index).setFocusable(true);
//                        l.get(index).setFocusableInTouchMode(true);
//                    }
//
//                }
//                return false;
//            }
//        });



    }

    public void find(ViewGroup v , List l)
    {
        for(int i=0;i<v.getChildCount();i++)
        {
            if(v.getChildAt(i) instanceof EditText)
            {
                l.add(v.getChildAt(i));
            }
            if(v.getChildAt(i) instanceof  ViewGroup)
            {
                find((ViewGroup) v.getChildAt(i),l);
            }

        }
    }
}
