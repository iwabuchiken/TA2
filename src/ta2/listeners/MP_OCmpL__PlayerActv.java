package ta2.listeners;

import ta2.services.Service_ShowProgress;
import ta2.utils.CONS;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;
import android.widget.Toast;

// From: CM5::MPOnCompletionListener
public class
MP_OCmpL__PlayerActv implements OnCompletionListener {	// "MP" => MediaPlayer

	//
	Activity actv;
	
	public MP_OCmpL__PlayerActv(Activity actv) {
		
		this.actv = actv;
		
	}
	
	//@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO ?��?��?��?��?��?��?��?��?��?��?��ꂽ?��?��?��\?��b?��h?��E?��X?��^?��u
//		Methods.stopPlayer(actv);
		/***************************************
		 * Stop: Player
		 ***************************************/
		CONS.PlayerActv.mp.stop();
		
		/***************************************
		 * Stop: Service
		 ***************************************/
		Intent i = new Intent((Context) actv, Service_ShowProgress.class);

		//
//		i.putExtra("counter", timeLeft);

		// Log
		Log.d("MP_OCmpL__PlayerActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Stopping service...");

		//
//		actv.startService(i);
		actv.stopService(i);

		
//		// debug
//		Toast.makeText(actv, "Complete", Toast.LENGTH_LONG).show();
		// Log
		String msg_Log = "complete";
		Log.d("MP_OCmpL__PlayerActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}//public void onCompletion(MediaPlayer mp)

}//MPOnCompletionListener implements OnCompletionListener {
