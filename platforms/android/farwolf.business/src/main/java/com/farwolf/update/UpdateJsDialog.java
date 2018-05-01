package com.farwolf.update;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.farwolf.base.ViewBase;
import com.farwolf.business.R;
import com.farwolf.interfac.IFullHttp;
import com.farwolf.view.FreeDialog;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by zhengjiangrong on 2018/5/1.
 */
@EViewGroup
public class UpdateJsDialog extends ViewBase {

    public FreeDialog f;
    public String url="";
    public String size="";
    public String path="";
    @ViewById
    TextView title;
    @ViewById
    ProgressBar progress;
    @ViewById
    TextView percent;

    public UpdateJsDialog(Context context) {
        super(context);
    }

    public UpdateJsDialog(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getViewId() {
        return R.layout.api_updatejs_dialog;
    }

    @Override
    public void init() {



    }


    public void start()
    {
        title.setText("下载中("+size+"");
        progress.setIndeterminate(false);
        progress.setMax(100);

        new JsDownloader().start(url, getContext(), new IFullHttp() {
            @Override
            public void OnPostProcess(int newProgress) {
                progress.setProgress(newProgress);
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
