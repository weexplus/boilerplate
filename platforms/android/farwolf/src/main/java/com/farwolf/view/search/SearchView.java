package com.farwolf.view.search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

import com.farwolf.base.ViewBase;
import com.farwolf.libary.R;
import com.farwolf.util.KeyBoardTool;
import com.farwolf.util.StringUtil;
import com.farwolf.view.SearchEditText;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by zhengjiangrong on 2017/3/21.
 */
@EViewGroup
public class SearchView extends ViewBase {



    OnSearchListener listener;


    @Bean
    KeyBoardTool tool;
    @ViewById
    public SearchEditText searchEditText;

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchView(Context context) {
        super(context);
    }

    @Override
    public int getViewId() {
        return R.layout.api_searchview;
    }

    public void setText(String txt)
    {
        searchEditText.setText(txt);
    }

    @Override
    public void init() {
        searchEditText.setOnSearchClickListener(new SearchEditText.OnSearchClickListener() {
            @Override
            public void onSearchClick(View view) {
                String content = searchEditText.getText().toString().trim();
                tool.dismiss();
                searchEditText.clearFocus();
                if(StringUtil.isNullOrEmpty(content))
                {
                    searchEditText.setText("");
                }
                else
                {
                    if (listener != null)
                        listener.onSearch(content);
                }


            }
        });

//        Drawable right=getResources().getDrawable(R.drawable.ic_edit_input_clear);
//        right.setBounds(0,0,-10,0);
//        Drawable left=getResources().getDrawable(R.drawable.search_bar_icon_normal);
//        left.setBounds(-10,0,0,0);
//        searchEditText.setCompoundDrawables(left,null,right,null);

    }




    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(KeyEvent.KEYCODE_BACK==event.getKeyCode())
        {
            if(searchEditText.hasFocus())
            {
                searchEditText.setText("");
                tool.dismiss();
                searchEditText.clearFocus();
                return true;
            }
        }



        return super.dispatchKeyEvent(event);
    }

    public void setListener(OnSearchListener listener) {
        this.listener = listener;
    }

    public  static interface OnSearchListener
    {
        public void onSearch(String content);
    }
}
