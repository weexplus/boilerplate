package com.farwolf.audio;

import java.io.File;
import java.io.IOException;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;

import com.farwolf.audio.AudioRecordFunc.RecordFinishListener;
import com.farwolf.util.DateTool;
import com.farwolf.util.FileTool;

@EBean
public class AudioService {

	
	  public  MediaPlayer  mPlayer;
	  String path="";
	  @Bean
	  public FileTool file;
	
	  boolean isrecording=false;
	  
	  String recordWavName="";
	
	  private void startRecord(String name)
	     {
	    	 String path= file.getBaseDir()+"chart/send/";
	    	   
	    	 file.makeDir(path);	
	    	 
	    	 
	    	  AudioRecordFunc mRecord_1 = AudioRecordFunc.getInstance(path+name, path);
	          mRecord_1.startRecordAndFile();               
	     }
	     
	  private String stopRecord(String name,RecordFinishListener listener)
	    { 
	    	 String path= file.getBaseDir()+"chart/send/";
	    	 file.makeDir(path);
	    	  AudioRecordFunc mRecord_1 = AudioRecordFunc.getInstance(path+name, path);
	          mRecord_1.stopRecordAndFile(); 
	          mRecord_1.setOnRecordFinishListener(listener);
	          return path+name;
	            
	 	} 


	   public  void startRecod()
		{
			if(isrecording)
				return;
			recordWavName=DateTool.TimeTampMili()+".wav";
			recordWavName="tmp.wav";
			isrecording=true;
			startRecord(recordWavName);
			
//			recordButton.setText("录音中....");
		}
		
	   public  void stopRecod()
		{
			if(isrecording)
			{

				
				stopRecord(recordWavName,new RecordFinishListener() {
					
					@Override
					public void onRecordFinish(String path) {
						// TODO Auto-generated method stub
						if(audioListener!=null)
						{
							AudioService.this.path=path;
							finish(path);
						    isrecording=false;
						   
						}
						
					}
				});
				
			}
			
		}
	   
	   
	   @UiThread
	   void finish(String path)
	   {
			audioListener.finish(path);
	   }


	   public void startPlay(OnCompletionListener l) 
	     {
		      startPlay(this.path,l);
		    
	     }
	   
	   public boolean isPlaying()
	   {
		     if(mPlayer==null)
			   return false;
			 try
			 {
				 return mPlayer.isPlaying();
			 }
			 catch(Exception e)
			 {
				 return false;
			 }
			 
	   }
	   
	   
	   public void startPlay(String file,final OnCompletionListener l) 
	     {

			    	   mPlayer = new MediaPlayer();
			    	   mPlayer.setOnCompletionListener(l);
			    	 
			    	 mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
						
						@Override
						public void onCompletion(MediaPlayer mp) {
							// TODO Auto-generated method stub
							mPlayer.release();
							mPlayer=null;
							if(l!=null)
								l.onCompletion(mp);
						}
					});
	    	         try {
	    	 
	    	             mPlayer.setDataSource(file);
	    	          
	    	             mPlayer.prepare();
	    	            
	    	             mPlayer.start();
	    	             
//	    	             mPlayer.stop();
	    	 
	    	         } catch (IOException e) {
	    	 
	    	             e.printStackTrace();
	    	 
	    	         }
	    	 
	    	     }

 
	   
	   public void pause()
	   {
		   if(mPlayer!=null)
			   mPlayer.pause();
	   }
	   public void play()
	   {
		   if(mPlayer!=null)
			   mPlayer.start();
	   }
	   
	   AudioListener audioListener;
	   
	   
	   public void setAudioListener(AudioListener audioListener) {
		this.audioListener = audioListener;
	   }


	public static interface AudioListener
	   {
		   
		    
		   public void finish(String path);
		    
		   
	   }
}
