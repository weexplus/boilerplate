package com.farwolf.perssion;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zhengjiangrong on 2018/6/8.
 */

public class Perssion {


   public static void check(final  Activity context, final String persion, final PerssionCallback callback )
    {
        RxPermissions rxPermission = new RxPermissions(context);
        rxPermission
                .request(persion)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean value) {

                        if(!value)
                        {

                            AlertDialog.Builder builder = new AlertDialog.Builder((Activity)context);
                            builder.setTitle("请注意");
                            builder.setMessage("当前功能需要"+getName(persion)+"权限,是否请前往设置赋予权限?");//提示内容
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
//                                    Toast.makeText(HybridCore.getInstance().getPageManager().getCurrentActivity(),"请给予权限!",Toast.LENGTH_SHORT).show();
                                    PermissionSetting p=new PermissionSetting(context);
                                    p.jumpPermissionPage();
                                    return;
                                }
                            });
                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {

                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();


                        }
                        callback.onGranted();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    public static String getName(String per)
    {
        HashMap m=new HashMap();
        m.put(Manifest.permission.ACCESS_CHECKIN_PROPERTIES,"访问登记属性");
        m.put(Manifest.permission.ACCESS_COARSE_LOCATION,"获取错略位置");
        m.put(Manifest.permission.ACCESS_FINE_LOCATION,"获取精确位置");
        m.put(Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,"访问定位额外命令");
//        m.put(Manifest.permission.ACCESS_MOCK_LOCATION,"获取模拟定位信息");
        m.put(Manifest.permission.ACCESS_NETWORK_STATE,"获取网络状态");
//        m.put(Manifest.permission.ACCESS_SURFACE_FLINGER,"访问Surface Flinger");
        m.put(Manifest.permission.ACCESS_WIFI_STATE,"获取WiFi状态");
        m.put(Manifest.permission.ACCOUNT_MANAGER,"账户管理");
//        m.put(Manifest.permission.AUTHENTICATE_ACCOUNTS,"验证账户");
        m.put(Manifest.permission.BATTERY_STATS,"电量统计");
        m.put(Manifest.permission.BIND_APPWIDGET,"绑定小插件");
        m.put(Manifest.permission.BIND_DEVICE_ADMIN,"绑定设备管理");
        m.put(Manifest.permission.BIND_INPUT_METHOD,"绑定输入法");
        m.put(Manifest.permission.BIND_REMOTEVIEWS,"绑定RemoteView");
        m.put(Manifest.permission.BIND_WALLPAPER,"绑定壁纸");
        m.put(Manifest.permission.BLUETOOTH,"使用蓝牙");
        m.put(Manifest.permission.BLUETOOTH_ADMIN,"蓝牙管理");
//        m.put(Manifest.permission.BRICK,"变成砖头");
        m.put(Manifest.permission.BROADCAST_PACKAGE_REMOVED,"应用删除时广播");
        m.put(Manifest.permission.BROADCAST_SMS,"收到短信时广播");
        m.put(Manifest.permission.BROADCAST_STICKY,"连续广播");
        m.put(Manifest.permission.BROADCAST_WAP_PUSH,"WAP PUSH广播");
        m.put(Manifest.permission.CALL_PHONE,"拨打电话");
        m.put(Manifest.permission.CALL_PRIVILEGED,"通话");
        m.put(Manifest.permission.CAMERA,"拍照");
        m.put(Manifest.permission.CHANGE_COMPONENT_ENABLED_STATE,"改变组件状态");
        m.put(Manifest.permission.CHANGE_CONFIGURATION,"改变配置");
        m.put(Manifest.permission.CHANGE_NETWORK_STATE,"改变网络状态");
        m.put(Manifest.permission.CHANGE_WIFI_MULTICAST_STATE,"改变WiFi多播状态");
        m.put(Manifest.permission.CHANGE_WIFI_STATE,"改变WiFi状态");
        m.put(Manifest.permission.CLEAR_APP_CACHE,"清除应用缓存");
//        m.put(Manifest.permission.CLEAR_APP_USER_DATA,"清除用户数据");
//        m.put(Manifest.permission.CWJ_GROUP,"底层访问权限");
//        m.put(Manifest.permission.CELL_PHONE_MASTER_EX,"手机优化大师扩展权限");
        m.put(Manifest.permission.CONTROL_LOCATION_UPDATES,"控制定位更新");
        m.put(Manifest.permission.DELETE_CACHE_FILES,"删除缓存文件");
        m.put(Manifest.permission.DELETE_PACKAGES,"删除应用");
//        m.put(Manifest.permission.DEVICE_POWER,"电源管理");
        m.put(Manifest.permission.DIAGNOSTIC,"应用诊断");
        m.put(Manifest.permission.DISABLE_KEYGUARD,"禁用键盘锁");
        m.put(Manifest.permission.DUMP,"转存系统信息");
        m.put(Manifest.permission.EXPAND_STATUS_BAR,"状态栏控制");
        m.put(Manifest.permission.FACTORY_TEST,"工厂测试模式");
//        m.put(Manifest.permission.FLASHLIGHT,"使用闪光灯");
//        m.put(Manifest.permission.FORCE_BACK,"强制后退");
        m.put(Manifest.permission.GET_ACCOUNTS,"访问账户Gmail列表");
        m.put(Manifest.permission.GET_PACKAGE_SIZE,"获取应用大小");
        m.put(Manifest.permission.GET_TASKS,"获取任务信息");
        m.put(Manifest.permission.GLOBAL_SEARCH,"允许全局搜索");
//        m.put(Manifest.permission.HARDWARE_TEST,"硬件测试");
//        m.put(Manifest.permission.INJECT_EVENTS,"注射事件");
        m.put(Manifest.permission.INSTALL_LOCATION_PROVIDER,"安装定位提供");
        m.put(Manifest.permission.INSTALL_PACKAGES,"安装应用程序");
//        m.put(Manifest.permission.INTERNAL_SYSTEM_WINDOW,"内部系统窗口");
        m.put(Manifest.permission.INTERNET,"访问网络");
        m.put(Manifest.permission.KILL_BACKGROUND_PROCESSES,"结束后台进程");
//        m.put(Manifest.permission.MANAGE_ACCOUNTS,"管理账户");
//        m.put(Manifest.permission.MANAGE_APP_TOKENS,"管理程序引用");
//        m.put(Manifest.permission.MTWEAK_USER,"高级权限");
//        m.put(Manifest.permission.MTWEAK_FORUM,"社区权限");
        m.put(Manifest.permission.MASTER_CLEAR,"软格式化");
        m.put(Manifest.permission.MODIFY_AUDIO_SETTINGS,"修改声音设置");
        m.put(Manifest.permission.MODIFY_PHONE_STATE,"修改电话状态");
        m.put(Manifest.permission.MOUNT_FORMAT_FILESYSTEMS,"格式化文件系统");
        m.put(Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,"挂载文件系统");
        m.put(Manifest.permission.NFC,"允许NFC通讯");
        m.put(Manifest.permission.PERSISTENT_ACTIVITY,"永久Activity");
        m.put(Manifest.permission.PROCESS_OUTGOING_CALLS,"处理拨出电话");
        m.put(Manifest.permission.READ_CALENDAR,"读取日程提醒");
        m.put(Manifest.permission.READ_CONTACTS,"读取联系人");
        m.put(Manifest.permission.READ_FRAME_BUFFER,"屏幕截图");
//        m.put(Manifest.permission.READ_HISTORY_BOOKMARKS,"读取收藏夹和历史记录");
        m.put(Manifest.permission.READ_INPUT_STATE,"读取输入状态");
        m.put(Manifest.permission.READ_LOGS,"读取系统日志");
        m.put(Manifest.permission.READ_PHONE_STATE,"读取电话状态");
        m.put(Manifest.permission.READ_SMS,"读取短信内容");
        m.put(Manifest.permission.READ_SYNC_SETTINGS,"读取同步设置");
        m.put(Manifest.permission.READ_SYNC_STATS,"读取同步状态");
        m.put(Manifest.permission.REBOOT,"重启设备");
        m.put(Manifest.permission.RECEIVE_BOOT_COMPLETED,"开机自动允许");
        m.put(Manifest.permission.RECEIVE_MMS,"接收彩信");
        m.put(Manifest.permission.RECEIVE_SMS,"接收短信");
        m.put(Manifest.permission.RECEIVE_WAP_PUSH,"接收Wap Push");
        m.put(Manifest.permission.RECORD_AUDIO,"录音");
        m.put(Manifest.permission.REORDER_TASKS,"排序系统任务");
        m.put(Manifest.permission.RESTART_PACKAGES,"结束系统任务");
        m.put(Manifest.permission.SEND_SMS,"发送短信");
//        m.put(Manifest.permission.SET_ACTIVITY_WATCHER,"设置Activity观察其");
        m.put(Manifest.permission.SET_ALARM,"设置闹铃提醒");
        m.put(Manifest.permission.SET_ALWAYS_FINISH,"设置总是退出");
        m.put(Manifest.permission.SET_ANIMATION_SCALE,"设置动画缩放");
        m.put(Manifest.permission.SET_DEBUG_APP,"设置调试程序");
//        m.put(Manifest.permission.SET_ORIENTATION,"设置屏幕方向");
        m.put(Manifest.permission.SET_PREFERRED_APPLICATIONS,"设置应用参数");
        m.put(Manifest.permission.SET_PROCESS_LIMIT,"设置进程限制");
        m.put(Manifest.permission.SET_TIME,"设置系统时间");
        m.put(Manifest.permission.SET_TIME_ZONE,"设置系统时区");
        m.put(Manifest.permission.SET_WALLPAPER,"设置桌面壁纸");
        m.put(Manifest.permission.SET_WALLPAPER_HINTS,"设置壁纸建议");
        m.put(Manifest.permission.SIGNAL_PERSISTENT_PROCESSES,"发送永久进程信号");
        m.put(Manifest.permission.STATUS_BAR,"状态栏控制");
//        m.put(Manifest.permission.SUBSCRIBED_FEEDS_READ,"访问订阅内容");
//        m.put(Manifest.permission.SUBSCRIBED_FEEDS_WRITE,"写入订阅内容");
        m.put(Manifest.permission.SYSTEM_ALERT_WINDOW,"显示系统窗口");
        m.put(Manifest.permission.UPDATE_DEVICE_STATS,"更新设备状态");
//        m.put(Manifest.permission.USE_CREDENTIALS,"使用证书");
        m.put(Manifest.permission.USE_SIP,"使用SIP视频");
        m.put(Manifest.permission.VIBRATE,"使用振动");
        m.put(Manifest.permission.WAKE_LOCK,"唤醒锁定");
        m.put(Manifest.permission.WRITE_APN_SETTINGS,"写入GPRS接入点设置");
        m.put(Manifest.permission.WRITE_CALENDAR,"写入日程提醒");
        m.put(Manifest.permission.WRITE_CONTACTS,"写入联系人");
        m.put(Manifest.permission.WRITE_EXTERNAL_STORAGE,"写入外部存储");
        m.put(Manifest.permission.WRITE_GSERVICES,"写入Google地图数据");
//        m.put(Manifest.permission.WRITE_HISTORY_BOOKMARKS,"写入收藏夹和历史记录");
        m.put(Manifest.permission.WRITE_SECURE_SETTINGS,"读写系统敏感设置");
        m.put(Manifest.permission.WRITE_SETTINGS,"读写系统设置");
//        m.put(Manifest.permission.WRITE_SMS,"编写短信");
        if(m.containsKey(per))
        return m.get(per)+"";
        return "该";
    }
}
