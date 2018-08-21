package com.farwolf.weex.module;

import com.farwolf.util.Callback;
import com.farwolf.util.SDCard;
import com.farwolf.util.ZipHelper;
import com.farwolf.weex.base.WXModuleBase;
import com.farwolf.weex.util.Const;
import com.farwolf.weex.util.Weex;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;

import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.farwolf.weex.util.Weex.getSdcardPath;

/**
 * Created by zhengjiangrong on 2018/8/14.
 */

public class WXFileModule extends WXModuleBase {



    @JSMethod
    public void unzip(String path, JSCallback callback)
    {
        try {
             path=path.replace(Const.PREFIX_SDCARD,"");
            FileInputStream fs=new FileInputStream(new File(path));
            String outpath= SDCard.getBasePath(mWXSDKInstance.getContext())+"/path";
            final List l=new ArrayList();
            ZipHelper.unZipFile(fs, outpath, new Callback() {
                @Override
                public void onInvoke(Object o) {

                   l.add(Weex.getSdcardPath(o+""));
                }
            });
            HashMap m=new HashMap();
            m.put("path", l);
            callback.invoke(m);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @JSMethod
    public void ls(String path,JSCallback callback)
    {
        path=path.replace(Const.PREFIX_SDCARD,"");
        File f=new File(path);
        File[] files = f.listFiles();
        JSONArray ja=new JSONArray();
        for(File fl:files)
        {
            HashMap m=new HashMap();
            m.put("path", getSdcardPath(fl.getAbsolutePath()));
            m.put("name",fl.getName());
            m.put("isDirectory",f.isDirectory());
            ja.put(m);
        }
        callback.invoke(ja);
    }

}
