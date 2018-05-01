package com.farwolf.weex.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.farwolf.weex.R;
import com.farwolf.weex.activity.WeexActivity;
import com.farwolf.weex.util.EventEnum;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengjiangrong on 2017/8/17.
 */
@EViewGroup
public class WXTabView extends WeexView {

    public List<String> urls=new ArrayList<>();


    @ViewById
    LinearLayout root;
    List<WXPageView>pages=new ArrayList<>();


    public WXTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WXTabView(Context context) {
        super(context);
    }


    @Override
    public int getViewId() {
        return R.layout.tabcomponent;
    }

    @Override
    public void init() {

//         this.setBackgroundColor(Color.RED);

    }



    public void setChildContext(Context c)
    {
        for(WXPageView p:pages)
        {
            p.instance.setContext(c);
        }
    }





    public void setItems(List<String> l)
    {
        if(this.urls.size()>0)
            return;
        this.urls=l;
        root.removeAllViews();
        pages.clear();
        WeexActivity a= (WeexActivity)getActivity();
        final Map param=a.getIntent().getParcelableExtra("param");
        for(String q:urls)
        {
            WXPageView p=WXPageView_.build(getContext());
//            p.setBackgroundColor(Color.GREEN);
            p.setParentInstance(instance);
            p.setVisibility(View.VISIBLE);
            p.setSrc(q,getContext(),param);
            ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            p.setLayoutParams(lp);
            pages.add(p);
            this.root.addView(p);


        }
//        a.addRenderListener(new RenderListener() {
//            @Override
//            public void onRenderSuccess(HashMap param) {
//                for(WXPageView px:pages)
//                {
//                    px.firePageInit();
//                }
//            }
//        });

        setIndex(0);
    }


    public void setIndex(int index)
    {
        for(WXPageView p:pages)
        {
            if(pages.indexOf(p)==index)
            {
                p.setVisibility(View.VISIBLE);
                p.fireResume();
            }
            else
            {
                p.setVisibility(View.GONE);
                p.fireLeave();
            }
        }
    }


    public void onEventInvoke(EventEnum event, int requestcode, int resultcode, Intent data)
    {
        for(WXPageView p:pages)
        {
            switch (event)
            {
                case OnActivityCreate:
                    p.instance.onActivityCreate();
                    break;
                case OnActivityStart:
                    p.instance.onActivityStart();
                    break;
                case OnActivityResume:
                    p.instance.onActivityResume();
                    break;
                case OnActivityPause:
                    p.instance.onActivityPause();
                    break;
                case OnActivityStop:
                    p.instance.onActivityStop();
                    break;
                case OnActivityDestroy:
                    p.instance.onActivityDestroy();
                    break;
                case OnActivityBack:
                    p.instance.onActivityBack();
                    break;
                case OnActivityResult:
                    p.instance.onActivityResult(requestcode,resultcode,data);

            }

        }
    }











}
