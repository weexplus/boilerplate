package com.farwolf.weex.component;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhengjiangrong on 2017/5/17.
 */
@Component(lazyload = false)
public class WXSwitch extends WXComponent<Switch> {


    private CompoundButton.OnCheckedChangeListener mListener;

    public WXSwitch(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
        super(instance, dom, parent);
    }


    @Override
    protected Switch initComponentHostView(@NonNull Context context) {
        Switch s=new Switch(context);
        return s;
    }


    @WXComponentProp(name = Constants.Name.CHECKED)
    public void setChecked(boolean checked) {
        getHostView().setOnCheckedChangeListener(null);
        getHostView().setChecked(checked);
        getHostView().setOnCheckedChangeListener(mListener);
    }

    @Override

    protected void removeEventFromView(String type) {
        super.removeEventFromView(type);
        if (getHostView() != null && Constants.Event.CHANGE.equals(type)) {
            getHostView().setOnCheckedChangeListener(null);
        }
    }

    @Override
    protected boolean setProperty(String key, Object param) {
        switch (key) {
            case Constants.Name.CHECKED:
                Boolean result = WXUtils.getBoolean(param, null);
                if (result != null) {
                    setChecked(result);
                }
                return true;
        }
        return super.setProperty(key, param);
    }

    @Override
    public void addEvent(String type) {
        super.addEvent(type);
        if (type != null && type.equals(Constants.Event.CHANGE) && getHostView() != null) {
            if (mListener == null) {
                mListener = new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        Map<String, Object> params = new HashMap<>(2);
                        params.put("value", isChecked);

                        Map<String, Object> domChanges = new HashMap<>();
                        Map<String, Object> attrsChanges = new HashMap<>();
                        attrsChanges.put("checked",Boolean.toString(isChecked));
                        domChanges.put("attrs",attrsChanges);
                        fireEvent(Constants.Event.CHANGE, params,domChanges);
                    }
                };
            }
            getHostView().setOnCheckedChangeListener(mListener);
        }
    }
}
