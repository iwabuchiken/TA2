//REF https://code.google.com/p/krvarma-android-samples/source/browse/trunk/AudioRecorder.2/src/com/varma/samples/audiorecorder/RecorderActivity.java
package ta2.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

public class Recorder {

	private static final int RECORDER_BPP = 16;
	private static final String AUDIO_RECORDER_FILE_EXT_WAV = ".wav";
	private static final String AUDIO_RECORDER_FOLDER = "AudioRecorder";
	private static final String AUDIO_RECORDER_TEMP_FILE = "record_temp.raw";
	private static final int RECORDER_SAMPLERATE = 44100;
	private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_STEREO;
	private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
	
	private AudioRecord recorder = null;
	private int bufferSize = 0;
	private Thread recordingThread = null;
	private boolean isRecording = false;
	
	
	public Recorder() {
		super();
		
		this.bufferSize = AudioRecord.getMinBufferSize(RECORDER_SAMPLERATE,RECORDER_CHANNELS,RECORDER_AUDIO_ENCODING);
		
	}

	public String getFilename(){
		
//		String filepath = CONS.DB.dPath_Audio;
		
//		String filepath = Environment.getExternalStorageDirectory().getPath();
		
		File file = new File(CONS.RecActv.AUDIO_RECORDER_FOLDER);
//		File file = new File(filepath,AUDIO_RECORDER_FOLDER);
		
		if(!file.exists()){
		        file.mkdirs();
		}
		
		String fname = CONS.Paths.fpath_AudioRecorded, 
				
//		String fname = String.format("%s/%s%s", 
//				file.getAbsolutePath(), 
//				Methods.conv_MillSec_to_AudioFileLabel(Methods.getMillSeconds_now()),
//				CONS.DB.fname_Audio_Ext);
		
		// Log
		msg_Log = "fname => " + fname;
		Log.d("Recorder.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		////////////////////////////////

		// set: file name => use the name in memo.text

		////////////////////////////////
//		CONS.RecActv.fname_Generated_WavFile = fname;
		
		////////////////////////////////

		// return

		////////////////////////////////
		return fname;
//		return String.format("%s/%s%s", 
//				file.getAbsolutePath(), 
//				Methods.conv_MillSec_to_AudioFileLabel(Methods.getMillSeconds_now()),
//				CONS.DB.fname_Audio_Ext);
		
//		return (file.getAbsolutePath() 
//				+ "/" 
//				+ System.currentTimeMillis() 
//				+ CONS.RecActv.AUDIO_RECORDER_FILE_EXT_WAV);
		
	}

	private String getTempFilename(){
		
		String dirpath = CONS.DB.dPath_Audio;
		
//		String filepath = Environment.getExternalStorageDirectory().getPath();
		File file = new File(CONS.RecActv.AUDIO_RECORDER_FOLDER);
//		File file = new File(filepath,AUDIO_RECORDER_FOLDER);
		
		if(!file.exists()){
		        file.mkdirs();
		}
		
		File tempFile = new File(dirpath,CONS.RecActv.AUDIO_RECORDER_TEMP_FILE);
		
//		if(tempFile.exists())
//				tempFile.delete();
		
		return (file.getAbsolutePath() + "/" + CONS.RecActv.AUDIO_RECORDER_TEMP_FILE);
		
	}

	public void 
	startRecording() {
		
		////////////////////////////////

		// validate: any temp file?

		////////////////////////////////
		String dirpath = CONS.DB.dPath_Audio;
		
		File tempFile = new File(dirpath,CONS.RecActv.AUDIO_RECORDER_TEMP_FILE);
		
		if(tempFile.exists()) {
			
			tempFile.delete();
			
			// Log
			String msg_Log = "temp file => deleted: " + tempFile.getAbsolutePath();
			Log.d("Recorder.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		////////////////////////////////

		// recorder

		////////////////////////////////
        recorder = new AudioRecord(MediaRecorder.AudioSource.MIC,
                                        RECORDER_SAMPLERATE, RECORDER_CHANNELS,RECORDER_AUDIO_ENCODING, bufferSize);
        
        recorder.startRecording();
        
        isRecording = true;
        
        recordingThread = new Thread(new Runnable() {
                
                @Override
                public void run() {
                        writeAudioDataToFile();
                }
        },"AudioRecorder Thread");
        
        recordingThread.start();
        
	}//startRecording
	
	private void 
	writeAudioDataToFile(){
		
		// Log
		String msg_Log = "writeAudioDataToFile";
		Log.d("Recorder.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
        byte data[] = new byte[bufferSize];
        String filename = getTempFilename();
        FileOutputStream os = null;
        
        try {
                os = new FileOutputStream(filename);
        } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
        	
        	// Log
			msg_Log = "FileNotFoundException";
			Log.e("Recorder.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
        	
                e.printStackTrace();
        }
        
        int read = 0;
        
        if(null != os){
                while(isRecording){
                        read = recorder.read(data, 0, bufferSize);
                        
                        if(AudioRecord.ERROR_INVALID_OPERATION != read){
                                try {
                                        os.write(data);
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                        }
                }
                
                try {
                        os.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        
	}//writeAudioDataToFile	

	public void 
	stopRecording(){
		
        if(null != recorder){
                isRecording = false;
                
                recorder.stop();
                recorder.release();
                
                recorder = null;
                recordingThread = null;
        }
        
        copyWaveFile(getTempFilename(),getFilename());
        deleteTempFile();
        
	}
	
	private void deleteTempFile() {
        File file = new File(getTempFilename());
        
        file.delete();
	}
	
	private void 
	copyWaveFile
	(String inFilename,String outFilename) {
		
		// Log
		String msg_Log = "inFilename => " + inFilename;
		Log.d("Recorder.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
        FileInputStream in = null;
        FileOutputStream out = null;
        long totalAudioLen = 0;
        long totalDataLen = totalAudioLen + 36;
        long longSampleRate = RECORDER_SAMPLERATE;
        int channels = 2;
        long byteRate = RECORDER_BPP * RECORDER_SAMPLERATE * channels/8;
        
        byte[] data = new byte[bufferSize];
        
        try {
                in = new FileInputStream(inFilename);
                out = new FileOutputStream(outFilename);
                totalAudioLen = in.getChannel().size();
                totalDataLen = totalAudioLen + 36;
                
//                AppLog.logString("File size: " + totalDataLen);
                
                WriteWaveFileHeader(out, totalAudioLen, totalDataLen,
                                longSampleRate, channels, byteRate);
                
                while(in.read(data) != -1){
                        out.write(data);
                }
                
                in.close();
                out.close();
        } catch (FileNotFoundException e) {
        	
        	// Log
			msg_Log = "FileNotFoundException";
			Log.e("Recorder.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
                e.printStackTrace();
                
        } catch (IOException e) {
        	
        	// Log
			msg_Log = "IOException";
			Log.e("Recorder.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
        	
                e.printStackTrace();
                
        }
        
        // Log
		msg_Log = "outFilename => " + outFilename;
		Log.d("Recorder.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
        
	}//copyWaveFile
	
	private void WriteWaveFileHeader(
            FileOutputStream out, long totalAudioLen,
            long totalDataLen, long longSampleRate, int channels,
            long byteRate) throws IOException {
    
	    byte[] header = new byte[44];
	    
	    header[0] = 'R';  // RIFF/WAVE header
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
	    header[12] = 'f';  // 'fmt ' chunk
	    header[13] = 'm';
	    header[14] = 't';
	    header[15] = ' ';
	    header[16] = 16;  // 4 bytes: size of 'fmt ' chunk
	    header[17] = 0;
	    header[18] = 0;
	    header[19] = 0;
	    header[20] = 1;  // format = 1
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
	    header[32] = (byte) (2 * 16 / 8);  // block align
	    header[33] = 0;
	    header[34] = RECORDER_BPP;  // bits per sample
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

	/******************************
		@return
			-10	recorder => null<br>
	 ******************************/
	public int 
	getState() {
		
		if (this.recorder == null) {
			
			// Log
			String msg_Log = "recorder => null";
			Log.e("Recorder.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return -10;
			
		}
		
		////////////////////////////////

		// state

		////////////////////////////////
		return recorder.getState();

	}//getState
	
}//public class Recorder
