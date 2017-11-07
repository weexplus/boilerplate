package com.farwolf.audio;



import android.content.Context; 
import android.content.res.Resources.NotFoundException; 
  
public class AudioErrorCode { 
	
    public final static int SUCCESS = 1000; 
    public final static int E_NOSDCARD = 1001; 
    public final static int E_STATE_RECODING = 1002; 
    public final static int E_UNKOWN = 1003; 
      
      
    public static String getErrorInfo(Context vContext, int vType) throws NotFoundException 
    { 
        switch(vType) 
        { 
        case SUCCESS: 
            return "success"; 
        case E_NOSDCARD: 
            return "没有sdcard"; 
        case E_STATE_RECODING: 
            return "正在录音中，请先停止录音";   
        case E_UNKOWN: 
        default: 
            return  "无法识别的错误";           
              
        } 
    } 
  
}