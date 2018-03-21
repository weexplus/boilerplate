package com.farwolf.weex.component;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXImage;
import com.taobao.weex.ui.component.WXVContainer;

/**
 * Created by zhengjiangrong on 2017/6/15.
 */

public class WXFImage extends WXImage {


    public WXFImage(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);
    }

//    @WXComponentProp(name = Constants.Name.SRC)
//    public void setSrc(String src) {



//        String placeholder=null;
//        WXAttr w= getDomObject().getAttrs();
//        Set st=  w.entrySet();
//        Iterator it= st.iterator();
//        while(it.hasNext())
//        {
//           String kl= it.next()+"";
//            System.out.println(kl);
//        }
//
//        if(getDomObject().getAttrs().containsKey(Constants.Name.PLACEHOLDER)){
//            placeholder= (String) getDomObject().getAttrs().get(Constants.Name.PLACEHOLDER);
//        }else if(getDomObject().getAttrs().containsKey(Constants.Name.PLACE_HOLDER)){
//            placeholder=(String)getDomObject().getAttrs().get(Constants.Name.PLACE_HOLDER);
//        }

//        if(placeholder!=null)
//        {
//            if(Weex.getBaseUrl(getInstance()).startsWith("http"))
//            {
//                placeholder= Weex.getRootUrl(placeholder,this.getInstance());
////                placeholder= placeholder.replace(Weex.getBaseUrl(getInstance()),"app/");
//            }
//            else
//            {
//                placeholder= getRootUrl(placeholder,this.getInstance());
//            }
//            getDomObject().getAttrs().put(Constants.Name.PLACE_HOLDER,placeholder);
//            getDomObject().getAttrs().put(Constants.Name.PLACEHOLDER,placeholder);
//        }

//        String p=getDomObject().getAttrs().get(Constants.Name.PLACE_HOLDER)+"";
//        super.setSrc(src);
//
//
//    }
//
//    @WXComponentProp(name = Constants.Name.PLACEHOLDER)
//    public void setPlaceHolder(String placeHolder) {
//
//    }





}
