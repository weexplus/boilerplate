package com.farwolf.weex.fragment;

import android.content.Intent;

import com.farwolf.util.RegexBase;
import com.farwolf.weex.pref.WeexPref_;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.sharedpreferences.Pref;


/**
 * Created by zhengjiangrong on 2017/4/19.
 */
@EFragment
public class QrFragment extends  com.farwolf.fragment.QrFragment{


    @Pref
    WeexPref_ pref;


    public void onResult(String content)
    {
        String s=RegexBase.regexOne(content,"http://",":");
        pref.edit().ip().put(s).apply();
        scanner.stopCamera();
        Intent in=new Intent();
        in.putExtra("url",content);
        getActivity().setResult(1,in);
        getActivity().finish();
         scanner.setCornersColor("#66CCCC");

    }





}
