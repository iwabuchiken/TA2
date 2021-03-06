package ta2.listeners.button;

import java.util.Locale;

import ta2.items.AudioMemo;
import ta2.items.BM;
import ta2.items.Memo;
import ta2.main.BMActv;
import ta2.main.R;
import ta2.services.Service_ShowProgress;
import ta2.tasks.Task_AudioTrack;
import ta2.utils.CONS;
import ta2.utils.DBUtils;
import ta2.utils.Methods;
import ta2.utils.Methods_dlg;
import ta2.utils.Tags;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

	private AudioMemo audio_memo;
	
	public BO_CL(Activity actv, int position) {
		//
		this.actv = actv;
		this.position = position;
		
		//
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
		
		
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
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

	}

	public BO_CL(Activity actv, AudioMemo audio_memo) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		
		this.audio_memo	= audio_memo;
		
		// Log
		String msg_Log;
		
		msg_Log = String.format(
				Locale.JAPAN,
				"audio_memo => %s", audio_memo.getText()
				);
		
		Log.i("BO_CL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		//
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

	}

	//	@Override
	public void onClick(View v) {
//		//
		Tags.ButtonTags tag = (Tags.ButtonTags) v.getTag();
//
		CONS.Admin.vib.vibrate(CONS.Admin.vibLength_click);
		
		//
		switch (tag) {

		case ACTV_PLAYER_BT_SEE_BM://-----------------------------------------------------------------------------
			
			case_ACTV_PLAYER_BT_SEE_BM();
			
			break;
			
		case ACTV_PLAYER_BT_ADD_BM://-----------------------------------------------------------------------------
			
			case_ACTV_PLAYER_BT_ADD_BM();
			
			break;
			
		case ACTV_SHOWLOG_IB_UP://-----------------------------------------------------------------------------
			
			case_ACTV_SHOWLOG_IB_UP();
			
			break;

		case ACTV_SHOWLOG_IB_DOWN://-----------------------------------------------------------------------------
			
			case_ACTV_SHOWLOG_IB_DOWN();
			
			break;

		case ACTV_SHOWLOG_IB_BOTTOM://-----------------------------------------------------------------------------
			
			case_ACTV_SHOWLOG_IB_BOTTOM();
			
			break;

		case ACTV_SHOWLOG_IB_BACK://-----------------------------------------------------------------------------
			
			case_ACTV_TN_IB_BACK();
			
			break;

		case ACTV_SHOWLOG_IB_TOP://-----------------------------------------------------------------------------
			
			case_ACTV_SHOWLOG_IB_TOP();
			
			break;

		case ACTV_MAIN_MEMO:
			
			case_ACTV_MAIN_MEMO();
			
			break;

		case ACTV_MEMO_BACK:
			
			case_ACTV_MEMO_BACK();
			
			break;
			
		case ACTV_MEMO_CLEAR:
			
			case_ACTV_MEMO_CLEAR();
			
			break;
			
		case ACTV_PLAY_CLEAR:
			
			case_ACTV_PLAY_CLEAR();
			
			break;
			
		case ACTV_MEMO_SAVE:
			
			case_ACTV_MEMO_SAVE();
			
			break;
			
//		case ACTV_PLAY_SAVE:
//			
//			case_ACTV_PLAY_SAVE();
//			
//			break;
			
		case ACTV_MAIN_SHOWLIST:
			
			case_ACTV_MAIN_SHOWLIST();
			
			break;
			
		case ACTV_SHOWLIST_BACK:
		case ACTV_IMPORT_BACK:
		case ACTV_PHOTO_BACK:
		case ACTV_IMAGE_BACK:
			
			case_ACTV_SHOWLIST_BACK();
			
			break;
			
		case ACTV_SHOWLIST_TOP:
			
			case_ACTV_SHOWLIST_TOP();
			
			break;
			
		case ACTV_IMPORT_TOP:
			
			case_ACTV_IMPORT_TOP();
			
			break;
			
		case ACTV_PHOTO_TOP:
			
			case_ACTV_PHOTO_TOP();
			
			break;
			
		case ACTV_SHOWLIST_UP:
			
			case_ACTV_SHOWLIST_UP();
			
			break;
			
		case ACTV_IMPORT_UP:
			
			case_ACTV_IMPORT_UP();
			
			break;
			
		case ACTV_PHOTO_UP:
			
			case_ACTV_PHOTO_UP();
			
			break;
			
		case ACTV_SHOWLIST_DOWN:
			
			case_ACTV_SHOWLIST_DOWN();
			
			break;
			
		case ACTV_IMPORT_DOWN:
			
			case_ACTV_IMPORT_DOWN();
			
			break;
			
		case ACTV_PHOTO_DOWN:
			
			case_ACTV_PHOTO_DOWN();
			
			break;
			
		case ACTV_SHOWLIST_BOTTOM:
			
			case_ACTV_SHOWLIST_BOTTOM();
			
			break;
			
		case ACTV_IMPORT_BOTTOM:
			
			case_ACTV_IMPORT_BOTTOM();
			
			break;
			
		case ACTV_PHOTO_BOTTOM:
			
			case_ACTV_PHOTO_BOTTOM();
			
			break;
			
		case ACTV_MEMO_EDIT_BACK:
			
			case_ACTV_MEMO_EDIT_BACK();
			
			break;
			
		case ACTV_MEMO_EDIT_SAVE:
			
			case_ACTV_MEMO_EDIT_SAVE();
			
			break;
			
		case ACTV_MAIN_VOICE:
			
			case_ACTV_MAIN_VOICE();
			
			break;
			
		case ACTV_REC_REC: //------------------------------
			
			case_ACTV_REC_REC();
			
			break;
			
		case ACTV_REC_STOP: //------------------------------
			
			case_ACTV_REC_STOP();
			
			break;
			
		case ACTV_REC_BACK: //------------------------------
			
			case_ACTV_REC_BACK();
			
			break;
			
		case ACTV_PLAY_PLAY: //------------------------------
			
			case_ACTV_PLAY_PLAY();
			
			break;
			
		case ACTV_PLAYER_PLAY: //------------------------------
			
			case_ACTV_PLAYER_PLAY();
			
			break;
			
		case ACTV_PLAT_STOP: //------------------------------
			
			case_ACTV_PLAT_STOP();
			
			break;
			
		case ACTV_PLAYER_STOP: //------------------------------
			
			case_ACTV_PLAYER_STOP();
			
			break;
			
		case ACTV_PLAY_BACK: //------------------------------
			
			case_ACTV_PLAY_BACK();
			
			break;
			
		case ACTV_PLAYER_BACK: //------------------------------
			
			case_ACTV_PLAYER_BACK();
			
			break;
			
		case ACTV_PLAY_TV: //------------------------------
			
			case_ACTV_PLAY_TV();
			
			break;
			
		case ACTV_PLAYER_TV: //------------------------------
			
			case_ACTV_PLAYER_TV();
			
			break;
			
		case ACTV_MAIN_PHOTO: //------------------------------
			
			case_ACTV_MAIN_PHOTO();
			
			break;
			
		default:
			break;
		}//switch (tag)
		
	}//public void onClick(View v)

	private void 
	case_ACTV_PLAYER_BT_SEE_BM() {
//		case_PlayerActv_See_BM() {
		// TODO Auto-generated method stub

		/***************************************
		 * Validate: Any bookmarks?
		 ***************************************/
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
//		long numOfBM = dbu.getNumOfEntries(actv, CONS.DB.tname_BM);
		long numOfBM = dbu.getNumOfEntries_BM(
								actv, 
//								CONS.DB.tname_BM, 
								this.audio_memo.getDb_Id());
//		long numOfBM = dbu.getNumOfEntries_BM(actv, CONS.DB.tname_BM, ai.getDb_id());
		
		if (numOfBM < 1) {
			
			// Log
			Log.d("BO_CL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "numOfBM < 1");
			
			// debug
			Toast.makeText(actv, "No bookmarks", Toast.LENGTH_LONG).show();
			
			return;
			
		} else {//if (numOfBM == condition)
			
			// Log
			Log.d("BO_CL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "numOfBM=" + numOfBM);
			
		}//if (numOfBM == condition)
		
		/***************************************
		 * Intent
		 ***************************************/
		Intent i = new Intent();
		
		i.setClass(actv, BMActv.class);
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
//		i.putExtra("ai_dbId", ai.getDb_id());
		i.putExtra(CONS.Intent.iKey_BMActv_AI_Id, this.audio_memo.getDb_Id());
//		i.putExtra(CONS.Intent.iKey_BMActv_AI_Id, ai.getDb_id());
//		i.putExtra(CONS.Intent.bmactv_key_ai_id, ai.getDb_id());
		
//		i.putExtra(CONS.Intent.iKey_BMActv_TableName, ai.getTable_name());
//		i.putExtra(CONS.Intent.bmactv_key_table_name, ai.getTable_name());
		
//		actv.startActivity(i);
		actv.startActivityForResult(i, CONS.Intent.REQUEST_CODE_SEE_BOOKMARKS);

	}//case_ACTV_PLAYER_BT_SEE_BM

	private void 
	case_ACTV_PLAYER_BT_ADD_BM() {
		// TODO Auto-generated method stub

		/***************************************
		 * Is the media player playing?
		 ***************************************/
		
		if (CONS.PlayerActv.mp == null) {
			
			// Log
			Log.d("BO_CL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Player => null");
			
			// debug
			Toast.makeText(actv, "Player => null", Toast.LENGTH_LONG).show();
			
			return;
			
		} else if (CONS.PlayerActv.mp.isPlaying()) {//if (PlayActv.mp == null)
			
//			_case_PlayActv_Add_BM__Playing();
			
		} else if (!CONS.PlayerActv.mp.isPlaying()) {//if (PlayActv.mp == null)
			
			_case_PlayActv_Add_BM__NotPlaying();
			
		} else {//if (PlayActv.mp == null)
			
			// Log
			Log.d("BO_CL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Player => Unknown state");
			
		}//if (PlayActv.mp == null)
		
	}//case_ACTV_PLAYER_BT_ADD_BM

	private void
	_case_PlayActv_Add_BM__NotPlaying() {
		// TODO Auto-generated method stub
		// Log
		String msg_Log = "Player => not playing";
		Log.d("BO_CL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		String currentPosition = 
				CONS.PlayerActv.tv_CurrentPosition.getText().toString();
		
		// Log
		msg_Log = "position => " + currentPosition;
		Log.d("BO_CL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		long currentPosition_long = 
						Methods.conv_ClockLabel_to_MillSec(currentPosition);
		
		if (currentPosition_long == 0) {
			
			// Log
			msg_Log = "Current position => at 0";
			Log.d("BO_CL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			// debug
			Toast.makeText(actv, msg_Log, Toast.LENGTH_SHORT).show();
			
			return;
			
		}
		
		/***************************************
		 * Insert BM data into db
		 * 1. Build a BM instance
		 * 2. Insert data using the instance
		 ***************************************/
		/***************************************
		 * 1. Build a BM instance
		 ***************************************/
		String tmp = Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now());
		
		BM bm = new BM.Builder()
		
					.setCreated_at(tmp)
					.setModified_at(tmp)
					
					.setPosition(currentPosition)
					.setTa_id(this.audio_memo.getDb_Id())
					.setTa_text(this.audio_memo.getText())
					
					.build();
		
		// Log
//		String msg_Log;
		
		msg_Log = String.format(
				Locale.JAPAN,
				"bm.text => %s", bm.getTa_text()
				);
		
		Log.i("BO_CL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		/***************************************
		 * 2. Insert data using the instance
		 ***************************************/
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		boolean res = dbu.insertData_BM(actv, bm);
		
		if (res == true) {
		
			// Log
			Log.d("BO_CL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "res=" + res);
			
			// debug
			Toast.makeText(actv, "Bookmark inserted", Toast.LENGTH_LONG).show();
			
		} else {//if (res == true)

			// Log
			Log.d("BO_CL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "res=" + res);

		}//if (res == true)			
		
	}//_case_PlayActv_Add_BM__NotPlaying()

//	private void 
//	_case_PlayActv_Add_BM__Playing() {
//		// TODO Auto-generated method stub
//		/***************************************
//		 * Get: Current position
//		 ***************************************/
////		int currentPosition = CONS.PlayActv.mp.getCurrentPosition();
//		String currentPosition = 
//				Methods.conv_MillSec_to_ClockLabel(
//						CONS.PlayActv.mp.getCurrentPosition());
//		
//		// Log
//		Log.d("BO_CL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "currentPosition=" + currentPosition);
//		
//		/***************************************
//		 * Get: Table name and db id of the ai instance
//		 ***************************************/
//		String tableName = ai.getTable_name();
//		long aiDbId = ai.getDb_id();
//		
//		// Log
//		Log.d("BO_CL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]",
//				"tableName=" + tableName
//				+ "/"
//				+ "aiDbId=" + aiDbId);
//		
//		/***************************************
//		 * Insert BM data into db
//		 * 1. Build a BM instance
//		 * 2. Insert data using the instance
//		 ***************************************/
//		/***************************************
//		 * 1. Build a BM instance
//		 ***************************************/
//		BM bm = new BM.Builder()
//					.setPosition(currentPosition)
//					.setTitle(ai.getTitle())
//					.setMemo(ai.getMemo())
//					.setAiId(ai.getDb_id())
//					.setAiTableName(ai.getTable_name())
//					.build();
//		
////		// Log
////		Log.d("BO_CL.java" + "["
////				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
////				+ ":"
////				+ Thread.currentThread().getStackTrace()[2].getMethodName()
////				+ "]", "ai.getTitle()=" + ai.getTitle());
////		// Log
////		Log.d("BO_CL.java" + "["
////				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
////				+ ":"
////				+ Thread.currentThread().getStackTrace()[2].getMethodName()
////				+ "]", "bm.getTitle()=" + bm.getTitle());
//		
//		/***************************************
//		 * 2. Insert data using the instance
//		 ***************************************/
//		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
//		boolean res = dbu.insertData_BM(actv, bm);
//		
//		if (res == true) {
//		
//			// Log
//			Log.d("BO_CL.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "res=" + res);
//			
//			// debug
//			Toast.makeText(actv, "Bookmark inserted", Toast.LENGTH_LONG).show();
//			
//		} else {//if (res == true)
//
//			// Log
//			Log.d("BO_CL.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "res=" + res);
//
//		}//if (res == true)		
//		
//	}//private void _case_PlayActv_Add_BM__Playing()
//

	private void 
	case_ACTV_MAIN_PHOTO() {
		// TODO Auto-generated method stub
		
		Methods.start_Activity_PhotoActv(actv);
		
		
	}//case_ACTV_MAIN_PHOTO

	private void 
	case_ACTV_PLAY_TV() {
		// TODO Auto-generated method stub
		
		Methods_dlg.dlg_EditMemo(actv);
		
		
	}//case_ACTV_PLAY_TV

	private void 
	case_ACTV_PLAYER_TV() {
		// TODO Auto-generated method stub
		
		Methods_dlg.dlg_EditMemo__PlayerActv(actv);
//		Methods_dlg.dlg_EditMemo(actv);
		
		
	}//case_ACTV_PLAYER_TV
	
	
	private void 
	case_ACTV_PLAY_BACK() {
		// TODO Auto-generated method stub

		/***************************************
		 * Stop: Service
		 ***************************************/
		if (CONS.PlayActv.i_Service_Progress__PlayActv == null) {

			CONS.PlayActv.i_Service_Progress__PlayActv = 
					new Intent((Context) actv, Service_ShowProgress.class);

		}//if (CONS.PlayerActv.i_Service_Progress == null)
		
//		Intent i = new Intent((Context) actv, Service_ShowProgress__PlayerActv.class);
//		Intent i = new Intent((Context) actv, Service_ShowProgress.class);
		
		//
//		i.putExtra("counter", timeLeft);
		
		// Log
		Log.d("BO_CL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Stopping service...");
		
		//
//		actv.startService(i);
		actv.stopService(CONS.PlayActv.i_Service_Progress__PlayActv);
//		actv.stopService(i);

////		Intent i = new Intent((Context) this, Service_ShowProgress.class);
//		Intent i = new Intent((Context) actv, Service_ShowProgress.class);
//		
//		//
////		i.putExtra("counter", timeLeft);
//		
//		// Log
//		String msg_Log;
//		
//		msg_Log = String.format(
//				Locale.JAPAN,
//				"service => stopping..."
//				);
//		
//		Log.i("BO_CL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//		
//		//
//		actv.startService(i);
////		this.stopService(i);


		////////////////////////////////

		// finish

		////////////////////////////////
		actv.finish();
		
		actv.overridePendingTransition(0, 0);
	
	}

	private void 
	case_ACTV_PLAYER_BACK() {
		// TODO Auto-generated method stub
		
		/***************************************
		 * Stop: Service
		 ***************************************/
		if (CONS.PlayerActv.i_Service_Progress__PlayerActv == null) {
			
			CONS.PlayerActv.i_Service_Progress__PlayerActv = 
					new Intent((Context) actv, Service_ShowProgress.class);
			
		}//if (CONS.PlayerActv.i_Service_Progress == null)
		
//		Intent i = new Intent((Context) actv, Service_ShowProgress__PlayerActv.class);
//		Intent i = new Intent((Context) actv, Service_ShowProgress.class);
		
		//
//		i.putExtra("counter", timeLeft);
		
		// Log
		Log.d("BO_CL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Stopping service...");
		
		//
//		actv.startService(i);
		actv.stopService(CONS.PlayerActv.i_Service_Progress__PlayerActv);
//		actv.stopService(i);
		
////		Intent i = new Intent((Context) this, Service_ShowProgress.class);
//		Intent i = new Intent((Context) actv, Service_ShowProgress.class);
//		
//		//
////		i.putExtra("counter", timeLeft);
//		
//		// Log
//		String msg_Log;
//		
//		msg_Log = String.format(
//				Locale.JAPAN,
//				"service => stopping..."
//				);
//		
//		Log.i("BO_CL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//		
//		//
//		actv.startService(i);
////		this.stopService(i);
		
		
		////////////////////////////////
		
		// finish
		
		////////////////////////////////
		actv.finish();
		
		actv.overridePendingTransition(0, 0);
		
	}//case_ACTV_PLAYER_BACK
	
	
	private void 
	case_ACTV_PLAT_STOP() {
		// TODO Auto-generated method stub
		
		Methods.stop_Player(actv);
		
//		// Log
//		String msg_Log = "case_ACTV_PLAT_STOP";
//		Log.d("BO_CL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
	}//case_ACTV_PLAT_STOP

	private void 
	case_ACTV_PLAYER_STOP() {
		// TODO Auto-generated method stub
		
		Methods.stop_Player__PlayerActv(actv);
//		Methods.stop_Player(actv);
		
	}//case_ACTV_PLAYER_STOP
	
	
	private void 
	case_ACTV_PLAY_PLAY() {
		// TODO Auto-generated method stub
		
		Methods.play_File(actv);
		
//		// Log
//		String msg_Log = "case_ACTV_PLAY_PLAY";
//		Log.d("BO_CL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
//		Methods.play_File(actv);
		
	}//ACTV_PLAT_PLAY

	private void 
	case_ACTV_PLAYER_PLAY() {
		// TODO Auto-generated method stub
		
		Methods.play_File__PlayerActv(actv);
//		Methods.play_File(actv);
		
	}//case_ACTV_PLAYER_PLAY
	
	private void 
	case_ACTV_REC_BACK() {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// release

		////////////////////////////////
		if (CONS.RecActv.mr != null) {
			
			CONS.RecActv.mr.stop();
			
			CONS.RecActv.mr.release();
			
			// Log
			String msg_Log = "CONS.RecActv.mr => released";
			Log.d("RecActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}

		////////////////////////////////

		// finish

		////////////////////////////////
		actv.finish();
		
		actv.overridePendingTransition(0, 0);
		
	}//case_ACTV_REC_BACK

	private void 
	case_ACTV_REC_STOP() {
		// TODO Auto-generated method stub
		
		Methods.recActv_Stop_2(actv);
//		Methods.recActv_Stop(actv);
		
	}//case_ACTV_REC_STOP

	private void 
	case_ACTV_REC_REC() {
		// TODO Auto-generated method stub
		
		Methods.recActv_Rec_2(actv);
//		Methods.recActv_Rec(actv);
		
	}//case_ACTV_REC_REC

	private void case_ACTV_MAIN_VOICE() {
		// TODO Auto-generated method stub
		
		Methods.start_Activity_RecActv(actv);
		
	}

	private void 
	case_ACTV_MEMO_EDIT_SAVE() {
		// TODO Auto-generated method stub
		
		String msg_Log;
		
		////////////////////////////////

		// validate: any text?

		////////////////////////////////
		EditText et = (EditText) actv.findViewById(R.id.actv_memo_et);
		
		String tmp = et.getText().toString();
		
		if (tmp == null) {
			
			String msg = "Text => null";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);

			return;
			
		}
		
		if (tmp.length() < 1) {
			
			String msg = "No text";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);
			
			return;
			
		}
		
		////////////////////////////////

		// update memo

		////////////////////////////////
		boolean res = Methods.update_Memo(actv);
		
//			-1	insertion => failed<br>
//			-2	Exception<br>
//			1	text => inserted<br>

		int res_i = res == true ? 1 : 0;
		
		Methods.report_Update_Memos(actv, res_i);
		
		////////////////////////////////

		// notify

		////////////////////////////////
//		Memo m = CONS.MemoEditActv.memo;
		Memo m = Methods.find_Memo_from_ListView(actv, CONS.MemoEditActv.memo.getDb_Id());
		
		if (m != null) {
			
			m.setText(tmp);
			
		} else {

			// Log
			msg_Log = "memo => null";
			Log.e("DB_OCL.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + "]", msg_Log);
			
		}
		
		CONS.ShowListActv.adp_List_Memos.notifyDataSetChanged();
		
		// Log
		msg_Log = "CONS.ShowListActv.adp_List_Memos => notified";
		Log.d("BO_CL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

	}//case_ACTV_MEMO_EDIT_SAVE

	private void 
	case_ACTV_MEMO_EDIT_BACK() {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// finish

		////////////////////////////////
		actv.setResult(CONS.Intent.RESULT_CODE_MEMO_EDIT_ACTIVE);
		
		actv.finish();
		
		// Log
		String msg_Log = "result => set: " + CONS.Intent.RESULT_CODE_MEMO_EDIT_ACTIVE;
		Log.d("BO_CL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		actv.overridePendingTransition(0, 0);

	}

	private void case_ACTV_SHOWLIST_BOTTOM() {
		// TODO Auto-generated method stub

		/******************************
			validate: list
		 ******************************/
		if (CONS.ShowListActv.list_Memos == null) {
			
	//		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
			
			CONS.ShowListActv.list_Memos = DBUtils.find_All_Memos(actv);
			
		}
		
		ListView lv = ((ListActivity) actv).getListView();
		
		int numOfGroups = CONS.ShowListActv.list_Memos.size() / lv.getChildCount();
		
		int indexOfLastChild = lv.getChildCount() * numOfGroups;
		
		lv.setSelection(indexOfLastChild);

	}

	private void case_ACTV_IMPORT_BOTTOM() {
		// TODO Auto-generated method stub
		
		/******************************
			validate: list
		 ******************************/
		if (CONS.ImportActv.list_Audio_Memos == null) {
			
			//		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
			
			CONS.ImportActv.list_Audio_Memos = DBUtils.find_All_Memos__ExternalAudios(actv, CONS.Enums.SortOrder.DESC);

		}
		
		ListView lv = ((ListActivity) actv).getListView();
		
		int numOfGroups = CONS.ImportActv.list_Audio_Memos.size() / lv.getChildCount();
		
		int indexOfLastChild = lv.getChildCount() * numOfGroups;
		
		lv.setSelection(indexOfLastChild);
		
	}//case_ACTV_IMPORT_BOTTOM
	
	
	private void 
	case_ACTV_PHOTO_BOTTOM() {
		// TODO Auto-generated method stub
		
		/******************************
			validate: list
		 ******************************/
		if (CONS.PhotoActv.list_TIs == null) {
			
			String msg = "TI list  => null. Please go back to MainActv";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);
			
			return;
				
		}

		////////////////////////////////

		// set selection

		////////////////////////////////
		ListView lv = ((ListActivity) actv).getListView();
		
		int numOfGroups = CONS.PhotoActv.list_TIs.size() / lv.getChildCount();
		
		int indexOfLastChild = lv.getChildCount() * numOfGroups;
		
		lv.setSelection(indexOfLastChild);
		
	}//case_ACTV_PHOTO_BOTTOM
	
	private void 
	case_ACTV_SHOWLIST_DOWN() {
		// TODO Auto-generated method stub
		/******************************
			validate: list
		 ******************************/
		if (CONS.ShowListActv.list_Memos == null) {
			
	//		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
			
			CONS.ShowListActv.list_Memos = DBUtils.find_All_Memos(actv);
			
		}
		
		ListView lv = ((ListActivity) actv).getListView();
		
		int new_Position = lv.getLastVisiblePosition();
		
		if((new_Position + lv.getChildCount()) 
				> CONS.ShowListActv.list_Memos.size()) {
			
			new_Position = CONS.ShowListActv.list_Memos.size() - lv.getChildCount();
			
		}
		
		lv.setSelection(new_Position);
		
	}

	private void 
	case_ACTV_IMPORT_DOWN() {
		// TODO Auto-generated method stub
		/******************************
			validate: list
		 ******************************/
		if (CONS.ImportActv.list_Audio_Memos == null) {
			
			//		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
			
			CONS.ImportActv.list_Audio_Memos = DBUtils.find_All_Memos__ExternalAudios(actv, CONS.Enums.SortOrder.DESC);

		}
		
		ListView lv = ((ListActivity) actv).getListView();
		
		int new_Position = lv.getLastVisiblePosition();
		
		if((new_Position + lv.getChildCount()) 
				> CONS.ImportActv.list_Audio_Memos.size()) {
			
			new_Position = CONS.ImportActv.list_Audio_Memos.size() - lv.getChildCount();
			
		}
		
		lv.setSelection(new_Position);
		
	}//case_ACTV_IMPORT_DOWN
	
	private void 
	case_ACTV_PHOTO_DOWN() {
		// TODO Auto-generated method stub
		/******************************
			validate: list
		 ******************************/
		if (CONS.PhotoActv.list_TIs == null) {
			
			String msg = "TI list  => null. Please go back to MainActv";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);
			
			return;
				
		}
		
		ListView lv = ((ListActivity) actv).getListView();
		
		int new_Position = lv.getLastVisiblePosition();
		
		if((new_Position + lv.getChildCount()) 
				> CONS.PhotoActv.list_TIs.size()) {
			
			new_Position = CONS.PhotoActv.list_TIs.size() - lv.getChildCount();
			
		}
		
		lv.setSelection(new_Position);
		
	}//case_ACTV_PHOTO_DOWN
	
	private void case_ACTV_SHOWLIST_UP() {
		// TODO Auto-generated method stub
		
		/******************************
			validate: list
		 ******************************/
		if (CONS.ShowListActv.list_Memos == null) {
			
	//		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
			
			CONS.ShowListActv.list_Memos = DBUtils.find_All_Memos(actv);
			
		}
		
		ListView lv = ((ListActivity) actv).getListView();
		
		int lastPos = lv.getLastVisiblePosition();
		
		int childCount = lv.getChildCount();
		
		int new_Position;
		
		if (lastPos - (childCount * 2) + 2 > 0) {
			
			new_Position = lastPos - (childCount * 2) + 2;
			
		} else {
			
			new_Position = 0;
	
		}
		
		lv.setSelection(new_Position);		
		
	}

	private void case_ACTV_IMPORT_UP() {
		// TODO Auto-generated method stub
		
		/******************************
			validate: list
		 ******************************/
		if (CONS.ImportActv.list_Audio_Memos == null) {
			
			//		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
			
			CONS.ImportActv.list_Audio_Memos = 
								DBUtils.find_All_Memos__ExternalAudios(
											actv, 
											CONS.Enums.SortOrder.DESC);
			
		}
		
		ListView lv = ((ListActivity) actv).getListView();
		
		int lastPos = lv.getLastVisiblePosition();
		
		int childCount = lv.getChildCount();
		
		int new_Position;
		
		if (lastPos - (childCount * 2) + 2 > 0) {
			
			new_Position = lastPos - (childCount * 2) + 2;
			
		} else {
			
			new_Position = 0;
			
		}
		
		lv.setSelection(new_Position);		
		
	}//case_ACTV_IMPORT_UP
	
	
	private void 
	case_ACTV_PHOTO_UP() {
		// TODO Auto-generated method stub
		
		/******************************
			validate: list
		 ******************************/
		if (CONS.PhotoActv.list_TIs == null) {
			
			String msg = "TI list  => null. Please go back to MainActv";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);
			
			return;
				
		}

		////////////////////////////////

		// set: position

		////////////////////////////////
		ListView lv = ((ListActivity) actv).getListView();
		
		int lastPos = lv.getLastVisiblePosition();
		
		int childCount = lv.getChildCount();
		
		int new_Position;
		
		if (lastPos - (childCount * 2) + 2 > 0) {
			
			new_Position = lastPos - (childCount * 2) + 2;
			
		} else {
			
			new_Position = 0;
			
		}
		
		lv.setSelection(new_Position);		
		
	}
	
	private void case_ACTV_SHOWLIST_TOP() {
		// TODO Auto-generated method stub
	
		/******************************
			validate: list
		 ******************************/
		if (CONS.ShowListActv.list_Memos == null) {
			
	//		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
			
			CONS.ShowListActv.list_Memos = DBUtils.find_All_Memos(actv);
			
		}
		
		ListView lv = ((ListActivity) actv).getListView();
		
		lv.setSelection(0);

	}

	private void case_ACTV_IMPORT_TOP() {
		// TODO Auto-generated method stub
		
		/******************************
			validate: list
		 ******************************/
		if (CONS.ImportActv.list_Audio_Memos == null) {
			
			//		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
			
			CONS.ImportActv.list_Audio_Memos = DBUtils.find_All_Memos__ExternalAudios(actv, CONS.Enums.SortOrder.DESC);
//			CONS.ImportActv.list_Audio_Memos = DBUtils.find_All_Memos(actv);
			
		}
		
		ListView lv = ((ListActivity) actv).getListView();
		
		lv.setSelection(0);
		
	}//IMPORT_
	
	private void case_ACTV_PHOTO_TOP() {
		// TODO Auto-generated method stub
		
		/******************************
			validate: list
		 ******************************/
		if (CONS.PhotoActv.list_TIs == null) {
			
			//		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
			
//			CONS.PhotoActv.ti_List = DBUtils.find_All_Memos(actv);
			
			String msg = "TI list  => null. Please go back to MainActv";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);
			
			return;
			
		}
		
		ListView lv = ((ListActivity) actv).getListView();
		
		lv.setSelection(0);
		
	}
	
	private void 
	case_ACTV_SHOWLIST_BACK() {
		// TODO Auto-generated method stub
	
		////////////////////////////////

		// finish

		////////////////////////////////
		actv.finish();
		
		actv.overridePendingTransition(0, 0);

		
	}

	private void 
	case_ACTV_MAIN_SHOWLIST() {
		// TODO Auto-generated method stub

		Methods.start_Activity_ShowListActv(actv);
		
	}

	private void 
	case_ACTV_MEMO_SAVE() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// validate: any text?

		////////////////////////////////
		EditText et = (EditText) actv.findViewById(R.id.actv_memo_et);
		
		String tmp = et.getText().toString();
		
		if (tmp == null) {
			
			String msg = "Text => null";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);

			return;
			
		}
		
		if (tmp.length() < 1) {
			
			String msg = "No text";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);
			
			return;
			
		}
		
		////////////////////////////////

		// save memo

		////////////////////////////////
		int res = Methods.save_Memo(actv);
		
//			-1	insertion => failed<br>
//			-2	Exception<br>
//			1	text => inserted<br>

		////////////////////////////////

		// clear view?

		////////////////////////////////
		boolean pref = Methods.get_Pref_Boolean(
				actv, 
				CONS.Pref.pname_MainActv, 
				actv.getString(R.string.prefs_ClearView_WhenSaved_key), 
				false);

		if (pref == true) {
			
			et.setText("");
			
		}
		
		
		Methods.report_Save_Memos(actv, res);
		
	}

//	private void 
//	case_ACTV_PLAY_SAVE() {
//		// TODO Auto-generated method stub
//		////////////////////////////////
//		
//		// validate: any text?
//		
//		////////////////////////////////
//		EditText et = (EditText) actv.findViewById(R.id.actv_play_et);
//		
//		String tmp = et.getText().toString();
//		
//		if (tmp == null) {
//			
//			String msg = "Text => null";
//			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);
//			
//			return;
//			
//		}
//		
//		if (tmp.length() < 1) {
//			
//			String msg = "No text";
//			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);
//			
//			return;
//			
//		}
//		
//		////////////////////////////////
//		
//		// save memo
//		
//		////////////////////////////////
//		int res = Methods.save_Memo(actv, R.id.actv_play_et);
//		
////			-1	insertion => failed<br>
////			-2	Exception<br>
////			1	text => inserted<br>
//		
//		////////////////////////////////
//		
//		// clear view?
//		
//		////////////////////////////////
//		boolean pref = Methods.get_Pref_Boolean(
//				actv, 
//				CONS.Pref.pname_MainActv, 
//				actv.getString(R.string.prefs_ClearView_WhenSaved_key), 
//				false);
//		
//		if (pref == true) {
//			
//			et.setText("");
//			
//		}
//		
//		
//		Methods.report_Save_Memos(actv, res);
//		
//	}//ACTV_PLAY_SAVE
	
	private void 
	case_ACTV_MEMO_CLEAR() {
		// TODO Auto-generated method stub
		
		Methods_dlg.conf_Clear_View(actv);
		
		
	}//case_ACTV_MEMO_CLEAR

	private void 
	case_ACTV_PLAY_CLEAR() {
		// TODO Auto-generated method stub
		
		Methods_dlg.conf_Clear_View_PlayActv(actv);
		
		
	}//case_ACTV_PLAY_CLEAR
	
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
	case_ACTV_MAIN_MEMO() {
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
			
//			Methods.start_SE_new(actv, CONS.Audio.Clip.dialog_Cancel);
			
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

	private void 
	case_ACTV_SHOWLOG_IB_UP() {
		// TODO Auto-generated method stub
		
//		/******************************
//			validate: list
//		 ******************************/
//		if (CONS.ShowLogActv.list_ShowLog_Files == null) {
//			
//			CONS.ShowLogActv.list_ShowLog_Files = 
//							Methods.get_LogItem_List(actv);
//			
//		}		
		
		ListView lv = ((ListActivity) actv).getListView();
		
		int lastPos = lv.getLastVisiblePosition();
		
		int childCount = lv.getChildCount();
		
		int new_Position;
		
		if (lastPos - (childCount * 2) + 2 > 0) {
			
			new_Position = lastPos - (childCount * 2) + 2;
			
		} else {
			
			new_Position = 0;
			
		}
		
		lv.setSelection(new_Position);		
		
	}//case_ACTV_TN_IB_TOP

	private void 
	case_ACTV_SHOWLOG_IB_DOWN() {
		// TODO Auto-generated method stub
		
		/******************************
			validate: list
		 ******************************/
		if (CONS.ShowLogActv.list_ShowLog_Files == null) {
			
			CONS.ShowLogActv.list_ShowLog_Files = 
							Methods.get_LogItem_List(actv);
			
		}
		
		ListView lv = ((ListActivity) actv).getListView();
		
		int new_Position = lv.getLastVisiblePosition();
		
		if((new_Position + lv.getChildCount()) 
				> CONS.ShowLogActv.list_ShowLog_Files.size()) {
			
			new_Position = 
					CONS.ShowLogActv.list_ShowLog_Files.size() - lv.getChildCount();
			
		}
		
		lv.setSelection(new_Position);
		
	}//case_ACTV_TN_IB_TOP

	private void 
	case_ACTV_SHOWLOG_IB_BOTTOM() {
		// TODO Auto-generated method stub
		
		/******************************
			validate: list
		 ******************************/
		if (CONS.ShowLogActv.list_ShowLog_Files == null) {
			
			CONS.ShowLogActv.list_ShowLog_Files = 
							Methods.get_LogItem_List(actv);
			
		}
			
		ListView lv = ((ListActivity) actv).getListView();
		
		int numOfGroups = 
					CONS.ShowLogActv.list_ShowLog_Files.size() / lv.getChildCount();
		
		int indexOfLastChild = lv.getChildCount() * numOfGroups;
		
		lv.setSelection(indexOfLastChild);
		
	}//case_ACTV_TN_IB_TOP

	private void 
	case_ACTV_TN_IB_BACK() {
		// TODO Auto-generated method stub
		
		actv.finish();
		
		actv.overridePendingTransition(0, 0);
		
	}

	private void 
	case_ACTV_SHOWLOG_IB_TOP() {
		// TODO Auto-generated method stub
		
//		/******************************
//			validate: list
//		 ******************************/
//		if (CONS.ShowLogActv.list_ShowLog_Files == null) {
//			
////			DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
//			
//			CONS.ShowLogActv.list_ShowLog_Files = 
//							Methods.get_LogItem_List(actv);
//			
//		}
		
		ListView lv = ((ListActivity) actv).getListView();
		
		lv.setSelection(0);
		
	}//case_ACTV_TN_IB_TOP

}//public class ButtonOnClickListener implements OnClickListener
