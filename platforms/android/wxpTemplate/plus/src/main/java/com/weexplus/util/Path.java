package com.weexplus.util;

import android.content.Context;

import com.weexplus.app.WxpApplication;
import com.weexplus.core.Module;
import com.weexplus.core.render.WeexRender;

import java.io.File;
import java.nio.file.Paths;
import java.util.Map;

public class Path {

    public static String getUrl(String url, String bundleUrl, String module){
        if(url==null){
            return null;
        }
        String baseUrl=getBaseUrl(bundleUrl,module);
        if(url.startsWith(Const.ROOT)){
            if(bundleUrl.startsWith(Const.HTTP))
            return baseUrl+url.replace(Const.ROOT,"");
            else{
                return url.replace(Const.ROOT,"");
            }
        }
        if(url.startsWith(Const.SDCARD)){
            return url.replace(Const.SDCARD,"");
        }
        if(url.startsWith(Const.HTTP)){
            return url;
        }
        String tmpUrl=getTranslateUrl(url,module);
        if(tmpUrl!=null){
            return baseUrl+tmpUrl;
        }
        if(StringUtil.isNullOrEmpty(bundleUrl)){
            tmpUrl=getRelativeUrl(url,bundleUrl);
            if(tmpUrl!=null){
                return tmpUrl;
            }
        }
        return null;
    }
    public static String getRelativeUrl(String url,String bundleUrl){
        if(url.startsWith("./"))
        {
            url=url.substring(2);
        }
        String base= bundleUrl;
        String q[]=url.split("\\.\\.\\/");
        String x[]= base.split("\\/");
        String p="";
        for(int i=0;i<x.length-q.length;i++)
        {
            p+=x[i]+"/";
        }
        p+=q[q.length-1];
        return p;
    }

    public static String getTranslateUrl(String url,String module){
        Module module1=    WeexRender.getModule(module);
        if(module1.navDic!=null&&module1.navDic.containsKey(url)){
            return module1.navDic.get(url)+"";
        }
        return null;
    }


    public static String getBaseUrl(String bundleUrl,String module){
        if(bundleUrl.startsWith(Const.HTTP)){
            if(bundleUrl.contains(Const.DEBUG_URL_FLAG)){
                String q[]= bundleUrl.split(Const.DEBUG_URL_FLAG);
                return q[0]+Const.DEBUG_URL_FLAG;
            }else{
                String q[]= bundleUrl.split("/"+module+"/");
                return q[0]+"/"+module+"/";
            }
        }else{

          String path=   SDCard.getBasePath(WxpApplication.getApplication())+"/modules/"+module+"/";
          if(new File(path).exists()){
              return path;
          }
          return "modules/"+module+"/";
        }
    }


    public  static String getPath(String url,String module){
        if(url.contains("?")){
            url=url.split("\\?")[0];
        }
        int index= url.indexOf(module);
        return url.substring(index+module.length()+1);

    }



    public  static String urlToLocal(String url, String module, Context context){
        String path=Path.getPath(url,module);
        return  SDCard.getBasePath(context)+File.separator+"modules"+File.separator+module+File.separator+path;

    }



    public static String getSDCardPath(String url){
       return Const.SDCARD+url;
    }


    public static String getPossiableModuleName(String url){
        if(url.contains("?")){
            if(url.contains("module=")){
                url=url.split("\\?")[1];
                String q[]=  url.split("&");
                for(String p:q){
                    String key=p.split("=")[0];
                    if("module".equals(key)){
                        return p.split("=")[1];
                    }
                }
            }
        }
        if(url.contains("views")){
            url= url.split("views")[0];
            String q[]= url.split("/");
            return q[q.length-1];
        }
        return null;

    }




}
