package com.farwolf.photoplus.module;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;

import com.farwolf.perssion.Perssion;
import com.farwolf.perssion.PerssionCallback;
import com.farwolf.weex.annotation.WeexModule;
import com.farwolf.weex.base.WXModuleBase;
import com.farwolf.weex.util.Weex;
import com.luck.picture.lib.PictureSelectionModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WeexModule(name="photoplus")
public class WXPhotoPlusModule extends WXModuleBase {

    JSCallback callback;

    @JSMethod
    public void open(final HashMap param, JSCallback callback){

        this.callback=callback;
        Perssion.check((Activity) mWXSDKInstance.getContext(), Manifest.permission.CAMERA,new PerssionCallback(){


            @Override
            public void onGranted() {

                Perssion.check((Activity) mWXSDKInstance.getContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE , new PerssionCallback() {
                    @Override
                    public void onGranted() {

                        PictureSelector ps= PictureSelector.create(getActivity());
                        PictureSelectionModel psm=null;
                        String action="choose";
                        if(param.containsKey("action")){
                            action=param.get("action")+"";
                        }
                        String type="photo";
                        if(param.containsKey("type")){
                            type=param.get("type")+"";
                        }
                        if("choose".equals(action)){
                            if ("photo".equals(type)) {
                                psm=ps.openGallery(PictureMimeType.ofImage());
                            }else if ("video".equals(type)){
                                psm=ps.openGallery(PictureMimeType.ofVideo());
                            }else if ("audio".equals(type)){
                                psm=ps.openGallery(PictureMimeType.ofAudio());
                            }
                        }else{
                            if ("photo".equals(type)) {
                                psm=ps.openCamera(PictureMimeType.ofImage());
                            }else if ("video".equals(type)){
                                psm=ps.openCamera(PictureMimeType.ofVideo());
                            }else if ("audio".equals(type)){
                                psm=ps.openCamera(PictureMimeType.ofAudio());
                            }
                        }


                        if(param.containsKey("aspX")&&param.containsKey("aspY")){
                            String aspX=param.get("aspX")+"";
                            String aspY=param.get("aspY")+"";
                            psm=psm.enableCrop(true);
                            psm=psm.cropWH(Integer.parseInt(aspX),Integer.parseInt(aspY));

                        }

                        if(param.containsKey("maxSize")){
                            String minSize=param.get("maxSize")+"";
                            psm= psm.compress(true);
                            psm=psm.minimumCompressSize(Integer.parseInt(minSize));
                        }
                        psm.forResult(PictureConfig.CHOOSE_REQUEST);
//                        if("choose".equals(action)){
//                            psm.forResult(PictureConfig.CHOOSE_REQUEST);
//                        }else{
//                            psm.forResult(PictureConfig.CAMERA);
//                        }




                    }
                });



            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode==PictureConfig.CHOOSE_REQUEST){
             if(resultCode==Activity.RESULT_OK){
               List<LocalMedia> l= (List<LocalMedia>)data.getSerializableExtra(PictureConfig.EXTRA_RESULT_SELECTION);
//                 Log.i("size",l.size()+"");
                 List res=translate(l);
                 if(callback!=null){
                     HashMap rs=new HashMap();
                     rs.put("res",res);
                     callback.invoke(rs);
                 }
             }
       }
    }


    public HashMap translate(LocalMedia l){
        HashMap m=new HashMap();
        String cutpath=Weex.getSdcardPath(l.getCutPath());
        String path=Weex.getSdcardPath(l.getPath());
        if(l.getCutPath()!=null){
            path=cutpath;
        }
        m.put("cutPath",cutpath);
        m.put("path",path);
        m.put("compressPath",Weex.getSdcardPath(l.getCompressPath()));
        m.put("type",l.getMimeType());
        return m;
    }


    public List translate(List<LocalMedia>l){
        List res=new ArrayList();
        for(LocalMedia lm:l){
            HashMap mx=translate(lm);
            res.add(mx);
        }
        return res;
    }
}
