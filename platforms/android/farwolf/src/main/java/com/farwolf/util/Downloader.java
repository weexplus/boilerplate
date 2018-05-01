package com.farwolf.util;

import android.os.AsyncTask;
import android.util.Log;

import com.farwolf.interfac.IFullHttp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zhengjiangrong on 2018/5/1.
 */

public class Downloader extends AsyncTask<String, Integer, String> {


        IFullHttp listener;


        public Downloader(IFullHttp listener){
             this.listener=listener;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            listener.OnPostStart(null);

        }

        @Override
        protected String doInBackground(String... params) {

            final String downloadUrl = params[0];
            final String savepath = params[1];

            File f=new File(savepath);
            if(f.exists())
            {
                f.delete();
            }

            final File file = new File(savepath);

            File dir = file.getParentFile();
            if (!dir.exists()){
                dir.mkdirs();
            }

            HttpURLConnection httpConnection = null;
            InputStream is = null;
            FileOutputStream fos = null;
            int updateTotalSize = 0;
            URL url;
            try {
                url = new URL(downloadUrl);
                httpConnection = (HttpURLConnection) url.openConnection();
                httpConnection.setConnectTimeout(5000);
                httpConnection.setReadTimeout(5000);

//                Log.e("", "download status code: " + httpConnection.getResponseCode());

                if (httpConnection.getResponseCode() != 200) {
                    return null;
                }

                updateTotalSize = httpConnection.getContentLength();

                if (file.exists()) {
                    if (updateTotalSize == file.length()) {
                        // 下载完成
                        return file.getAbsolutePath();
                    } else {
                        file.delete();
                    }
                }
                file.createNewFile();
                is = httpConnection.getInputStream();
                fos = new FileOutputStream(file, false);
                byte buffer[] = new byte[4096];

                int readSize = 0;
                int currentSize = 0;

                while ((readSize = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, readSize);
                    currentSize += readSize;
                    publishProgress((currentSize * 100 / updateTotalSize));
                }
                // download success
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                if (httpConnection != null) {
                    httpConnection.disconnect();
                }
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return file.getAbsolutePath();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.e("", "current progress is " + values[0]);
            listener.OnPostProcess(values[0]);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s != null){
                listener.OnPostCompelete(s);
            }else {
                listener.OnException(s);
            }
        }

}
