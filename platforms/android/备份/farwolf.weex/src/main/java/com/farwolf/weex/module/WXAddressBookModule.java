package com.farwolf.weex.module;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhengjiangrong on 2017/9/24.
 */

public class WXAddressBookModule extends WXModule {


    JSCallback callback;

    @JSMethod
    public void read(JSCallback callback)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(mWXSDKInstance.getContext(), Manifest.permission.READ_CONTACTS);

            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED)
            {
                 this.callback=callback;
                ActivityCompat.requestPermissions((Activity) this.mWXSDKInstance.getContext(), new String[]{Manifest.permission.READ_CONTACTS},
                        11);
                return;
            }
            else
            {
                this.doread(callback);
            }

        }
        else
        {
            this.doread(callback);
        }

    }


    public  void doread(JSCallback callback)
    {
        List<HashMap> l=queryContactPhoneNumber();

//        List<HashMap> l=getPhoneContacts(mWXSDKInstance.getContext());
        HashMap m=new HashMap();
        m.put("people",l);
        callback.invokeAndKeepAlive(m);
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        switch (requestCode)
        {
            case  11:
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                 if(this.callback!=null)
                     this.doread(callback);
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private static final String[] PROJECTECTION = {
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Photo.PHOTO_ID,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
    };


    public static List<HashMap> getPhoneContacts(Context context)
    {
                List<HashMap> l=new ArrayList<>();

//        Cursor cursor = context.getContentResolver().query(ContactsContract.RawContacts.CONTENT_URI,
//                PROJECTECTION,
//                ContactsContract.RawContacts.ACCOUNT_NAME + "=?",
//                new String[]{"Phone"}, null);
//        Cursor cursor = context.getContentResolver().query(ContactsContract.RawContacts.CONTENT_URI,
//                new String[] { ContactsContract.RawContacts._ID, ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY }//
////                , null, null, null);

        String[] cols = {ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                cols, null, null, null);


//        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PROJECTECTION, null, null,  PROJECTECTION[0] + " ASC");

        while (cursor.moveToNext())
        {
            int indexId = cursor.getColumnIndex(ContactsContract.Contacts._ID);
            String contactId = cursor.getString(indexId);
            int indexDisplayName = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            String name = cursor.getString(indexDisplayName);

            Cursor phones = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
            while (phones.moveToNext())
            {
                String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                HashMap m=new HashMap();
                 m.put("firstName",name);
                 m.put("lastName","");
                 ArrayList lx=new ArrayList();
                 lx.add(phoneNumber);
                 m.put("phones",lx);
                 l.add(m);
            }
            phones.close();
        }
        cursor.close();
        return l;
    }

    private List<HashMap> queryContactPhoneNumber() {
        List<HashMap> l=new ArrayList<>();
        String[] cols = {ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = mWXSDKInstance.getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                cols, null, null, null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            // 取得联系人名字
            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
            int numberFieldColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String name = cursor.getString(nameFieldColumnIndex);
            String number = cursor.getString(numberFieldColumnIndex);
            HashMap m=new HashMap();
            m.put("firstName",name);
            m.put("lastName","");
            ArrayList lx=new ArrayList();
            lx.add(number);
            m.put("phones",lx);
            l.add(m);
//            Toast.makeText(mWXSDKInstance.getContext(), name + " " + number, Toast.LENGTH_SHORT).show();


        }
        return l;
    }
}
