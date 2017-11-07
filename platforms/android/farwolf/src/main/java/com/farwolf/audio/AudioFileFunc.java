package com.farwolf.audio;

import java.io.File; 

import android.R.integer;
import android.media.MediaRecorder; 
import android.os.Environment; 
  
public class AudioFileFunc { 
   
    public final static int AUDIO_INPUT = MediaRecorder.AudioSource.MIC; 
    
    public final static int AUDIO_SAMPLE_RATE = 8000;     
    
    private final static String AUDIO_RAW_FILENAME = "tmp.raw"; 
    private  String AUDIO_WAV_FILENAME; 
    private  String AUDIO_FILEPATH;
    
    public AudioFileFunc()
    {
    	super();
    }
    
    public AudioFileFunc(String Audio_Wav_Filename,String Audio_FilePath)
    {
    	super();
    	this.AUDIO_WAV_FILENAME = Audio_Wav_Filename;
    	this.AUDIO_FILEPATH = Audio_FilePath;
    }
 
      
    /** 
     *  sdcard has Existed
     * @return true | false 
     */
    public static boolean isSdcardExit(){        
        if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) 
            return true; 
        else
            return false; 
    } 
          
    /** 
     * 
     * @return 
     */
    public  String getRawFilePath(){ 
        String mAudioRawPath = ""; 
        if(isSdcardExit())
           mAudioRawPath = AUDIO_FILEPATH+AUDIO_RAW_FILENAME; 
         
        return mAudioRawPath; 
    } 
   
    public  String getWavFilePath(){ 
        String mAudioWavPath = ""; 
        if(isSdcardExit())
           mAudioWavPath = AUDIO_WAV_FILENAME; 
        
        return mAudioWavPath; 
    } 
      
 
    public static long getFileSize(String path){ 
        File mFile = new File(path); 
        if(!mFile.exists()) 
            return -1; 
        return mFile.length(); 
    } 
  
}