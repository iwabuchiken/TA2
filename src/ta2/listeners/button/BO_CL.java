package ta2.listeners.button;

import ta2.main.R;
import ta2.tasks.Task_AudioTrack;
import ta2.utils.CONS;
import ta2.utils.DBUtils;
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
import android.widget.EditText;
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
			
			case_ACTV_SHOWLIST_BACK();
			
			break;
			
		case ACTV_SHOWLIST_TOP:
			
			case_ACTV_SHOWLIST_TOP();
			
			break;
			
		case ACTV_SHOWLIST_UP:
			
			case_ACTV_SHOWLIST_UP();
			
			break;
			
		case ACTV_SHOWLIST_DOWN:
			
			case_ACTV_SHOWLIST_DOWN();
			
			break;
			
		case ACTV_SHOWLIST_BOTTOM:
			
			case_ACTV_SHOWLIST_BOTTOM();
			
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
			
		case ACTV_PLAT_STOP: //------------------------------
			
			case_ACTV_PLAT_STOP();
			
			break;
			
		case ACTV_PLAY_BACK: //------------------------------
			
			case_ACTV_PLAY_BACK();
			
			break;
			
		case ACTV_PLAY_TV: //------------------------------
			
			case_ACTV_PLAY_TV();
			
			break;
			
		case ACTV_MAIN_PHOTO: //------------------------------
			
			case_ACTV_MAIN_PHOTO();
			
			break;
			
		default:
			break;
		}//switch (tag)
		
	}//public void onClick(View v)

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
	case_ACTV_PLAY_BACK() {
		// TODO Auto-generated method stub

		////////////////////////////////

		// finish

		////////////////////////////////
		actv.finish();
		
		actv.overridePendingTransition(0, 0);
	
	}

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
		
	}//case_ACTV_MEMO_EDIT_SAVE

	private void 
	case_ACTV_MEMO_EDIT_BACK() {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// finish

		////////////////////////////////
		actv.finish();
		
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

	private void case_ACTV_SHOWLIST_DOWN() {
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
