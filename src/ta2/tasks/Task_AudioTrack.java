package ta2.tasks;

import ta2.utils.CONS;
import ta2.utils.Methods;
import android.app.Activity;
import android.media.AudioTrack;
import android.os.AsyncTask;
import android.util.Log;

public class Task_AudioTrack extends AsyncTask<Integer, Integer, Integer> {

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		if(this.isCancelled()) {
			
			this.onCancelled();
			
		}

		super.onPreExecute();
	}

	Activity actv;
	int bgmResourceId;
	
	public Task_AudioTrack(Activity actv) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
	}

	public Task_AudioTrack(Activity actv, int bgmResourceId) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
		this.bgmResourceId = bgmResourceId;
	}

	@Override
	protected Integer 
	doInBackground(Integer... bgmResourceIds) {
		
//		if(this.isCancelled()) {
//			
//			this.onCancelled();
//			
//		}
		
		Methods.playSound(actv, bgmResourceIds[0]);
		
		return null;
		
	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// validate: audio track => not null

		////////////////////////////////
		if (CONS.Audio.audioTrack == null) {
			
			// Log
			String msg_Log = "CONS.Audio.audioTrack => null";
			Log.d("Task_AudioTrack.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} else if (CONS.Audio.audioTrack.getPlayState() 
				== AudioTrack.PLAYSTATE_PLAYING) {
			
			CONS.Audio.audioTrack.stop();
			
			// Log
			String msg_Log = "track => stopped";
			Log.d("Task_AudioTrack.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			//test
			CONS.Audio.audioTrack.release();
			
			CONS.Audio.audioTrack = null;
			
		} else {
			
			// Log
			String msg_Log = "track => not playing";
			Log.d("Task_AudioTrack.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(Integer result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

}
