package com.farwolf.update;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.Toast;

import com.farwolf.base.ViewBase;
import com.farwolf.business.R;
import com.farwolf.util.AppMainfest;
import com.farwolf.util.SDCard;
import com.farwolf.view.FreeDialog;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.io.File;

/**
 * Created by zhengjiangrong on 2017/4/12.
 */
@EViewGroup
public class UpdateDialog extends ViewBase {

    public FreeDialog f;
    @ViewById
    TextView version_name;
    @ViewById
    TextView size;
    @ViewById
    TextView desc;

    Version data;

    @Bean
    AppMainfest appMainfest;

    @Pref
    UpdatePref_ pref;

    public UpdateDialog(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UpdateDialog(Context context) {
        super(context);
    }

    @Override
    public int getViewId() {
        return R.layout.api_update_dialog;
    }

    @Override
    public void init() {
        desc.setMovementMethod(ScrollingMovementMethod.getInstance());
    }


    public void init(Version v)
    {
      this.data=v;
        this.desc.setText(v.desc);
        this.version_name.setText("最新版本:"+v.versionName);
        this.size.setText("版本大小:"+v.source);
        this.desc.setText(v.desc);
    }

    @Click
    public void ignoreClicked() {

        Toast.makeText(getActivity(), pref.version().get(),Toast.LENGTH_LONG);
        pref.edit().version().put(this.data.versionName).apply();
        pref.edit().time().put(System.currentTimeMillis()).apply();
        f.dismiss();
    }

    @Click
    public void okClicked() {

        DownloadManager downloadManager=(DownloadManager) getContext().getSystemService( Context.DOWNLOAD_SERVICE);;

        DownloadManager.Request down=new DownloadManager.Request (Uri.parse(this.data.downloadUrl));
        //设置允许使用的网络类型，这里是移动网络和wifi都可以
        down.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);

        //不显示下载界面
        down.setVisibleInDownloadsUi(true);

        //设置下载后文件存放的位置
        new File(getApkPath()).delete();
        down.setDestinationUri(Uri.fromFile(new File(getApkPath())));

        downloadManager.enqueue(down);

        Toast.makeText(getContext(),"后台下载中",Toast.LENGTH_LONG).show();
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                 Toast.makeText(getContext(),"下载完毕",Toast.LENGTH_LONG).show();
                 installApk(new File(getApkPath()));
            }
        };
        getActivity().registerReceiver(receiver, filter);
        this.f.dismiss();
    }

    @Click
    public void cancelClicked() {

        f.dismiss();
    }


    public String getApkPath()
    {
        String directory=SDCard.getBasePath(getContext())+"/"+appMainfest.getPakageName();
        if(!SDCard.IsFileExist(directory))
        {
             new File(directory).mkdirs();
        }
        String path= directory+ "/update"+this.data.versionName+".apk";
        return path;
    }





        //安装apk
        protected void installApk(File file) {
            Intent intent = new Intent();
            //执行动作
            intent.setAction(Intent.ACTION_VIEW);
            //执行的数据类型
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            getContext().startActivity(intent);
        }

}
