package com.farwolf.audio;

import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.util.ArrayList;
import java.util.List;
  
import android.R.integer;
import android.media.AudioFormat; 
import android.media.AudioRecord; 
import android.util.Log;
  
public class AudioRecordFunc {  
    
    private int bufferSizeInBytes = 0; 

    private String AudioName = "";    
    private String NewAudioName;   //NewAudioName可播放的音频文件
    private String audioPath;
      
    private AudioRecord audioRecord;   
      
    public AudioFileFunc audioFileFunc;
    private static AudioRecordFunc mInstance;  
    boolean isRecord = false;
           
    private AudioRecordFunc(){ 
          super();
    }    
    
    public AudioRecordFunc(String NewAudioName,String audioPath)
    {
    	super();
    	audioFileFunc = new AudioFileFunc(NewAudioName, audioPath);
    	
    }
      
    public synchronized static AudioRecordFunc getInstance(String NewAudioName,String audioPath) 
    { 
        if(mInstance == null)  
            mInstance = new AudioRecordFunc(NewAudioName,audioPath);  
        Log.d("wav: "+NewAudioName, "path: "+audioPath);
        return mInstance;  
    } 
      
    public int startRecordAndFile() { 
        
            if(isRecord) 
            { 
                return AudioErrorCode.E_STATE_RECODING; 
            } 
            else
            { 
            	 AudioName = audioFileFunc.getRawFilePath(); 
                 NewAudioName = audioFileFunc.getWavFilePath();
                if(audioRecord == null) 
                    creatAudioRecord(); 
                  
                audioRecord.startRecording();   
             
                // 锟斤拷录锟斤拷状态为true   
                isRecord = true;   
                // 锟斤拷锟斤拷锟斤拷频锟侥硷拷写锟斤拷锟竭筹拷   
                new Thread(new AudioRecordThread()).start();   
                  
                return AudioErrorCode.SUCCESS; 
            } 
              
          
  
    }   
    
    public void stopRecordAndFile() {   
        close();   
 
    } 
      
      
    public long getRecordFileSize(){ 
        return audioFileFunc.getFileSize(NewAudioName); 
    } 
      
    
     void close() {   
        if (audioRecord != null) {   
            System.out.println("stopRecord");   
            isRecord = false;//停止锟侥硷拷写锟斤拷   
           
            audioRecord.stop();   
            audioRecord.release();//锟酵凤拷锟斤拷源   
          
            audioRecord = null;   
            audioFileFunc = null;
            mInstance = null;
        } 
        else {
			System.err.println("close stream -----------  audioRecord is empty");
		}
    } 
      
      
    private void creatAudioRecord() {   
        // 锟斤拷取锟斤拷频锟侥硷拷路锟斤拷 
//        AudioName = audioFileFunc.getRawFilePath(); 
//        NewAudioName = audioFileFunc.getWavFilePath();  
    	System.err.println("creatAudioRecord()-----RAW:  "+AudioName+"  --- WAV:  "+NewAudioName);
        // 锟斤拷没锟斤拷锟斤拷锟斤拷纸诖锟斤拷?  
        bufferSizeInBytes = AudioRecord.getMinBufferSize(audioFileFunc.AUDIO_SAMPLE_RATE,   
                AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);   
          if (audioRecord == null) 
        	  System.err.println("before create new AudioRecord is empty");
        // 锟斤拷锟斤拷AudioRecord锟斤拷锟斤拷   
        audioRecord = new AudioRecord(audioFileFunc.AUDIO_INPUT, audioFileFunc.AUDIO_SAMPLE_RATE,   
                AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSizeInBytes);  
        if (audioRecord == null) 
      	  System.err.println("after create new AudioRecord is empty");
    } 
      
      
    class AudioRecordThread implements Runnable {   
        
        public void run() {   
        	
        	try {
				writeDateTOFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//往文件中写入裸数据 
            copyWaveFile(AudioName, NewAudioName);//给裸数据加上头文件 
            File file = new File(AudioName);  
            if (file.exists()) {   
                file.delete();   
            } 
            
        }   
    }   
    
    /**
     * 这里将数据写入文件，但是并不能播放，因为AudioRecord获得的音频是原始的裸音频，
     * 如果需要播放就必须加入一些格式或者编码的头信息。但是这样的好处就是你可以对音频的 裸数据进行处理，比如你要做一个爱说话的TOM
     * 猫在这里就进行音频的处理，然后重新封装 所以说这样得到的音频比较容易做一些音频的处理。
     * @throws IOException 
     */ 
    private void writeDateTOFile() throws IOException {   
        // new一锟斤拷byte锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷一些锟街斤拷锟斤拷荩锟斤拷锟叫∥拷锟斤拷锟斤拷锟斤拷小   
        byte[] audiodata = new byte[bufferSizeInBytes];  
        File file = new File(AudioName);  
        FileOutputStream fos = null;   
        int readsize = 0;   
//        if (file.exists()) {   
//                file.delete();   
//            }   
            fos = new FileOutputStream(file);// 锟斤拷锟斤拷一锟斤拷锟缴达拷取锟街节碉拷锟侥硷拷   
            
        while (isRecord == true) {   
        	
            readsize = audioRecord.read(audiodata, 0, bufferSizeInBytes);   
             if (AudioRecord.ERROR_INVALID_OPERATION != readsize && fos!=null)  
             {
            		fos.write(audiodata);   

              }
        }   
        
            if(fos != null)
            {
                fos.close();// 锟截憋拷写锟斤拷锟斤拷   
               
                Log.d("File out Stream", "is closed"); 
            }
        
    }   
    
    
    
    // 锟斤拷锟斤拷玫锟斤拷刹锟斤拷诺锟斤拷锟狡碉拷募锟�  
    private void copyWaveFile(String inFilename, String outFilename) {   
    	Log.e("Copy From raw_file", inFilename);
    	Log.e("Copy  To wav_file", outFilename);
        FileInputStream in = null;   
        FileOutputStream out = null;   
        long totalAudioLen = 0;   
        long totalDataLen = totalAudioLen + 36;   
        long longSampleRate = audioFileFunc.AUDIO_SAMPLE_RATE;   
        int channels = 1;   
        long byteRate = 16 * audioFileFunc.AUDIO_SAMPLE_RATE * channels / 8;   
        byte[] data = new byte[bufferSizeInBytes];   
        Log.i("inFilename", inFilename);
        Log.i("outFilename", outFilename);
        try {   
            in = new FileInputStream(inFilename);   
            out = new FileOutputStream(outFilename);   
          
            totalAudioLen = in.getChannel().size();   
            totalDataLen = totalAudioLen + 36;   
            WriteWaveFileHeader(out, totalAudioLen, totalDataLen,   
                    longSampleRate, channels, byteRate);   
            while (in.read(data) != -1) {   
                out.write(data);   
            }   
            in.close();   
            out.close();   
        } catch (FileNotFoundException e) {   
            e.printStackTrace();   
        } catch (IOException e) {   
            e.printStackTrace();   
        }  
        
        if(onRecordFinishListener!=null)
        {
        	onRecordFinishListener.onRecordFinish(outFilename);
        }
    } 
    
    
     RecordFinishListener onRecordFinishListener;
     
    
    
    public void setOnRecordFinishListener(
			RecordFinishListener onRecordFinishListener) {
		this.onRecordFinishListener = onRecordFinishListener;
	}


	public static interface RecordFinishListener
    {
    	
    	public void onRecordFinish(String path);
   
    }
    
    /**  
     * 锟斤拷锟斤拷锟结供一锟斤拷头锟斤拷息锟斤拷锟斤拷锟斤拷锟斤拷些锟斤拷息锟酵匡拷锟皆得碉拷锟斤拷锟皆诧拷锟脚碉拷锟侥硷拷锟斤拷  
     * 为锟斤拷为啥锟斤拷锟斤拷锟斤拷44锟斤拷锟街节ｏ拷锟斤拷锟斤拷锟斤拷锟矫伙拷锟斤拷锟斤拷芯锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟揭伙拷锟絯av  
     * 锟斤拷频锟斤拷锟侥硷拷锟斤拷锟斤拷锟皆凤拷锟斤拷前锟斤拷锟酵凤拷募锟斤拷锟斤拷锟剿碉拷锟揭伙拷锟脚讹拷锟矫匡拷指锟绞斤拷锟斤拷募锟斤拷锟斤拷锟� 
     * 锟皆硷拷锟斤拷锟叫碉拷头锟侥硷拷锟斤拷  
     */  
    private void WriteWaveFileHeader(FileOutputStream out, long totalAudioLen,   
            long totalDataLen, long longSampleRate, int channels, long byteRate)   
            throws IOException {   
        byte[] header = new byte[44];   
        header[0] = 'R'; // RIFF/WAVE header   
        header[1] = 'I';   
        header[2] = 'F';   
        header[3] = 'F';   
        header[4] = (byte) (totalDataLen & 0xff);   
        header[5] = (byte) ((totalDataLen >> 8) & 0xff);   
        header[6] = (byte) ((totalDataLen >> 16) & 0xff);   
        header[7] = (byte) ((totalDataLen >> 24) & 0xff);   
        header[8] = 'W';   
        header[9] = 'A';   
        header[10] = 'V';   
        header[11] = 'E';   
        header[12] = 'f'; // 'fmt ' chunk   
        header[13] = 'm';   
        header[14] = 't';   
        header[15] = ' ';   
        header[16] = 16; // 4 bytes: size of 'fmt ' chunk   
        header[17] = 0;   
        header[18] = 0;   
        header[19] = 0;   
        header[20] = 1; // format = 1   
        header[21] = 0;   
        header[22] = (byte) channels;   
        header[23] = 0;   
        header[24] = (byte) (longSampleRate & 0xff);   
        header[25] = (byte) ((longSampleRate >> 8) & 0xff);   
        header[26] = (byte) ((longSampleRate >> 16) & 0xff);   
        header[27] = (byte) ((longSampleRate >> 24) & 0xff);   
        header[28] = (byte) (byteRate & 0xff);   
        header[29] = (byte) ((byteRate >> 8) & 0xff);   
        header[30] = (byte) ((byteRate >> 16) & 0xff);   
        header[31] = (byte) ((byteRate >> 24) & 0xff);   
        header[32] = (byte) (1 * 16 / 8); // block align   
        header[33] = 0;   
        header[34] = 16; // bits per sample   
        header[35] = 0;   
        header[36] = 'd';   
        header[37] = 'a';   
        header[38] = 't';   
        header[39] = 'a';   
        header[40] = (byte) (totalAudioLen & 0xff);   
        header[41] = (byte) ((totalAudioLen >> 8) & 0xff);   
        header[42] = (byte) ((totalAudioLen >> 16) & 0xff);   
        header[43] = (byte) ((totalAudioLen >> 24) & 0xff);   
        out.write(header, 0, 44);   
    }   
}









