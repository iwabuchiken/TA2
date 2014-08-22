package ta2.tasks;

import ta2.main.R;
import ta2.utils.CONS;
import ta2.utils.Methods;
import ta2.utils.Methods_dlg;
import android.app.Activity;
import android.media.AudioTrack;
import android.os.AsyncTask;
import android.util.Log;

public class Task_AudioTrack 
					extends AsyncTask<Integer, Integer, Integer> {

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
		
		int res = Methods.playSound(actv, bgmResourceIds[0]);
		
		return res;
//		return null;
		
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
	protected void 
	onPostExecute
	(Integer result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		int res = result.intValue();
		
		////////////////////////////////

		// dispatch

		////////////////////////////////
		if(res == -1) { 
		
			String msg = "audioTrack => null";
			int colorID = R.color.red;
			
		
			Methods_dlg.dlg_ShowMessage_Duration(
						actv, 
						msg,
						colorID,
						CONS.Admin.dflt_MessageDialog_Length);
			
			// Log
			Log.d("Task_AudioTrack.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg);
		
		} else if(res == -2) {

			String msg = "audioTrack => NullPointerException";
			int colorID = R.color.red;
			
			////////////////////////////////

			// countermeasure for: error

			////////////////////////////////
			if (CONS.Audio.task_Audio != null) {
				
				CONS.Audio.task_Audio.cancel(true);
				
				// Log
				String msg_Log = "task audio => cancelled";
				Log.d("MemoActv.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", msg_Log);
				
				// End the task
				CONS.Audio.task_Audio = null;
				
				if (CONS.Audio.audioTrack != null) {
					
					CONS.Audio.audioTrack.release();
					
					// Log
					msg_Log = "audio => released";
					Log.d("MemoActv.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", msg_Log);
					
				}
				
				//test
				CONS.Audio.audioTrack = null;
				
			}
			
			////////////////////////////////

			// message

			////////////////////////////////
			Methods_dlg.dlg_ShowMessage_Duration(
						actv, 
						msg,
						colorID,
						CONS.Admin.dflt_MessageDialog_Length);
			
			// Log
			Log.d("Task_AudioTrack.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg);

		} else if(res == -3) {
			
			String msg = "audioTrack => null";
			int colorID = R.color.red;
			
			////////////////////////////////
			
			// message
			
			////////////////////////////////
			Methods_dlg.dlg_ShowMessage_Duration(
					actv, 
					msg,
					colorID,
					CONS.Admin.dflt_MessageDialog_Length);
			
			// Log
			Log.d("Task_AudioTrack.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg);
			
			
		}

		

	}//onPostExecute

}//Task_AudioTrack
