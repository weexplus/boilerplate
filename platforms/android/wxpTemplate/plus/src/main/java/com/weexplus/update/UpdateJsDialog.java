package com.weexplus.update;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.weex.weexplus.R;
import com.weexplus.util.IFullHttp;
import com.weexplus.view.FreeDialog;


/**
 * Created by zhengjiangrong on 2018/5/1.
 */

public class UpdateJsDialog extends LinearLayout {

    public FreeDialog f;
    public String url="";
    public int version=0;
    public String size="";
    public String path="";

    TextView title;

    ProgressBar progress;

    TextView percent;

    public UpdateJsDialog(Context context) {
        super(context);
    }

    public UpdateJsDialog(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public int getViewId() {
        return R.layout.api_updatejs_dialog;
    }


    public void init() {



    }


    public void start()
    {
        title.setText("下载中("+size+"");
        progress.setIndeterminate(false);
        progress.setMax(100);

        new JsDownloader().start(url, version,2,"",getContext(), new IFullHttp() {
            @Override
            public void OnPostProcess(float newProgress,float current,float total) {
                progress.setProgress((int)newProgress);
                percent.setText(newProgress+"%");
            }

            @Override
            public void OnPostStart(Object o) {

            }

            @Override
            public void OnPostCompelete(Object o) {
                f.dismiss();
            }

            @Override
            public void OnException(Object o) {

            }
        });
    }
}
