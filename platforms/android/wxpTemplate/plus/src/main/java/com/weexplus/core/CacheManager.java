package com.weexplus.core;
import android.content.Context;

import com.weexplus.core.render.WeexRender;
import com.weexplus.util.Const;
import com.weexplus.util.FileTool;
import com.weexplus.util.Md5;
import com.weexplus.util.Path;
import com.weexplus.util.SDCard;
import com.weexplus.util.SHA1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;


public class CacheManager {

    public static void cache(String url, byte []content, String module, Context context){
        if(!needCache(url)){
            return;
        }
        String ab_path= Path.urlToLocal(url,module,context);
        try {
            File file= new File(ab_path);
            if (!file.exists()) {
                File parent=file.getParentFile();
                String tp=  parent.getAbsolutePath();
                parent.mkdirs();
                file.createNewFile();
            }
            FileOutputStream out =new FileOutputStream(file);
            out.write(content);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public static boolean needDownload(String url, String modulename, Context context){
        if(url.contains(Const.DEBUG_URL_FLAG)){
            return true;
        }
        String path=Path.getPath(url,modulename);
        String ab_path= Path.urlToLocal(url,modulename,context);
        if(!new File(ab_path).exists()){
             return true;
        }
        String md5 = Md5.fileMd5(new File(ab_path));
        Module module= WeexRender.getModule(modulename);
        if(module.hash==null){
            return true;
        }
        if(module.hash.size()==0){
            return false;
        }
        String serverHash=module.hash.get(path)+"";
        return !serverHash.equals(md5);
    }

    public static boolean needCache(String url){

        if(url.startsWith(Const.HTTP)){
            if(!url.contains(Const.DEBUG_URL_FLAG)){
                return true;
            }
        }
        return false;
    }

}
