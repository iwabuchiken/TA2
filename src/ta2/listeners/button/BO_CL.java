package ta2.listeners.button;

import ta2.main.R;
import ta2.tasks.Task_AudioTrack;
import ta2.utils.CONS;
import ta2.utils.Methods;
import ta2.utils.Methods_dlg;
import ta2.utils.Tags;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class BO_CL implements OnClickListener {
	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;

	//
	Vibrator vib;
	
	//
	int position;
	
	public BO_CL(Activity actv, int position) {
		//
		this.actv = actv;
		this.position = position;
		
		//
//		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
		
		
	}//public ButtonOnClickListener(Activity actv, int position)

	public BO_CL(Activity actv) {
		
		this.actv = actv;
		
		////////////////////////////////

		// se

		////////////////////////////////
		boolean val = Methods.get_Pref_Boolean(
				actv, 
				CONS.Pref.pname_MainActv, 
				actv.getString(R.string.prefs_sound_effect_key), 
				false);
		
		if (val == true) {
			
			if (CONS.Audio.task_Audio == null) {
				
				CONS.Audio.task_Audio = new Task_AudioTrack(actv);
				
			}
			
		}
		
		//
//		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

	}

	//	@Override
	public void onClick(View v) {
//		//
		Tags.ButtonTags tag = (Tags.ButtonTags) v.getTag();
//
//		CONS.Admin.vib.vibrate(CONS.Admin.vibLength_click);
		
		//
		switch (tag) {

		case ACTV_MAIN_MEMO:
			
			case_MEMO();
			
			break;

		case ACTV_MEMO_BACK:
			
			case_ACTV_MEMO_BACK();
			
			break;
			
		case ACTV_MEMO_CLEAR:
			
			case_ACTV_MEMO_CLEAR();
			
			break;
			
		case ACTV_MEMO_SAVE:
			
			case_ACTV_MEMO_SAVE();
			
			break;
			
			
		default:
			break;
		}//switch (tag)
		
	}//public void onClick(View v)

	private void 
	case_ACTV_MEMO_SAVE() {
		// TODO Auto-generated method stub
		
		int res = Methods.save_Memo(actv);
		
//			-1	insertion => failed<br>
//			-2	Exception<br>
//			1	text => inserted<br>

		Methods.report_Save_Memos(actv, res);
		
	}

	private void 
	case_ACTV_MEMO_CLEAR() {
		// TODO Auto-generated method stub
		
		Methods_dlg.conf_Clear_View(actv);
		
		
	}//case_ACTV_MEMO_CLEAR

	private void 
	case_ACTV_MEMO_BACK() {
		// TODO Auto-generated method stub
		
//		////////////////////////////////
//		
//		// bgm => stop
//		
//		////////////////////////////////
//		if (CONS.Audio.task_Audio != null) {
//		
//			CONS.Audio.task_Audio.cancel(true);
//			
//			// Log
//			String msg_Log = "task audio => cancelled";
//			Log.d("MemoActv.java" + "["
//			+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//			+ "]", msg_Log);
//			
//			// End the task
//			CONS.Audio.task_Audio = null;
//			
//			if (CONS.Audio.audioTrack != null) {
//				
//				CONS.Audio.audioTrack.release();
//				
//				// Log
//				msg_Log = "audio => released";
//				Log.d("MemoActv.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber() + "]", msg_Log);
//				
//			}
//			//test
//			CONS.Audio.audioTrack = null;
//		
//		}

		////////////////////////////////

		// save text

		////////////////////////////////
		Methods.save_Memo_Temporary(actv);

		////////////////////////////////

		// finish

		////////////////////////////////
		actv.finish();
		
		actv.overridePendingTransition(0, 0);
		
	}

	private void 
	case_MEMO() {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// bgm

		////////////////////////////////
		boolean val = Methods.get_Pref_Boolean(
				actv, 
				CONS.Pref.pname_MainActv, 
				actv.getString(R.string.prefs_sound_effect_key), 
				false);
		
		// avoid starting the same task instance more than once
		//		at a time
		if (val == true) {
			
			Methods.start_SE_new(actv, CONS.Audio.Clip.dialog_Cancel);
			
		}
		
//		////////////////////////////////
//
//		// bgm
//
//		////////////////////////////////
//		boolean val = Methods.get_Pref_Boolean(
//				actv, 
//				CONS.Pref.pname_MainActv, 
//				actv.getString(R.string.prefs_sound_effect_key), 
//				false);
//		
//		// avoid starting the same task instance more than once
//		//		at a time
//		if (val == true) {
//			
//			// Log
//			String msg_Log = "Memo => starting SE...";
//			Log.d("BO_CL.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//			if (CONS.Audio.task_Audio != null) {
//				
//				// Log
//				msg_Log = "CONS.Audio.task_Audio => not null";
//				Log.d("BO_CL.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber() + "]", msg_Log);
//				
//				if(CONS.Audio.audioTrack != null) {
////				if (CONS.Audio.task_Audio != null
////						&& CONS.Audio.audioTrack != null) {
//				
//					// Log
//					msg_Log = "cancelling task audio...";
//					Log.d("BO_CL.java"
//							+ "["
//							+ Thread.currentThread().getStackTrace()[2]
//									.getLineNumber() + "]", msg_Log);
//					
//					CONS.Audio.task_Audio.cancel(true);
//					
//					CONS.Audio.task_Audio = null;
//					
//					// Log
//					msg_Log = "audio task => re-initializing...";
//					Log.d("DOI_CL.java"
//							+ "["
//							+ Thread.currentThread().getStackTrace()[2]
//									.getLineNumber() + "]", msg_Log);
//					CONS.Audio.task_Audio = new Task_AudioTrack(actv);
//					
//				}
//				
//			} else {
//
//				msg_Log = "NOT cancelling task audio...";
//				Log.d("BO_CL.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber() + "]", msg_Log);
//
//			}
//			
//			int bgmResourceId = R.raw.audio_nature_mp3;
//			
////			CONS.Audio.task_Audio = new Task_AudioTrack(actv);
////		Task_AudioTrack task = new Task_AudioTrack(actv);
//			
////		task.execute("Start");
//			CONS.Audio.task_Audio.execute(bgmResourceId);
//			
//		}//if (val == true)
		
//		task.execute(bgmResourceId);
		
		// se
//		Methods.start_SE(actv, CONS.Audio.Clip.dialog_Cancel);
		
		Methods.start_Activity_MemoActv(actv);
		
	}//case_MEMO

}//public class ButtonOnClickListener implements OnClickListener
