package ta2.listeners.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ta2.items.FilterHistory;
import ta2.items.Memo;
import ta2.items.WordPattern;
import ta2.main.R;
import ta2.tasks.Task_AudioTrack;
import ta2.tasks.Task_FTP;
import ta2.utils.CONS;
import ta2.utils.DBUtils;
import ta2.utils.Methods;
import ta2.utils.Methods_dlg;
import ta2.utils.Tags;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DB_OCL implements OnClickListener {
	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;
	Dialog d1;
	Dialog d2;		//=> Used in dlg_input_empty_btn_XXX
	Dialog d3;
	private Dialog d4;

	//
	Vibrator vib;
	
	// Used in => Methods.dlg_addMemo(Activity actv, long file_id, String tableName)
	long db_Id;
	String tableName;
	private String item_Name;	// Methods_dlg.conf_DropTable
	private Memo memo;
	private WordPattern wp;
	public DB_OCL(Activity actv, Dialog dlg1) {
		//
		this.actv = actv;
		this.d1 = dlg1;
		
		this._Init_SoundEffect(actv);
		//
//		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
	}

	private void 
	_Init_SoundEffect
	(Activity actv) {
		// TODO Auto-generated method stub
		
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
		
	}//_Init_SoundEffect

	public DB_OCL(Activity actv, Dialog dlg1,
			Dialog dlg2) {
		//
		this.actv = actv;
		this.d1 = dlg1;
		this.d2 = dlg2;
		
		//
//		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}

	public DB_OCL(Activity actv, Dialog dlg1,
			Dialog dlg2, Dialog dlg3) {
		//
		this.actv = actv;
		this.d1 = dlg1;
		this.d2 = dlg2;
		this.d3 = dlg3;
		
		//
//		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}

	public DB_OCL(Activity actv, Dialog dlg1, long file_id, String tableName) {
		// 
		this.actv = actv;
		this.d1 = dlg1;
		
		this.tableName = tableName;
		
		this.db_Id = file_id;
		
//		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}//public DialogButtonOnClickListener(Activity actv, Dialog dlg1, long file_id, String tableName)


	public DB_OCL
	(Activity actv, Dialog dlg1, Dialog dlg2, String item_Name) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		this.d1 = dlg1;
		this.d2 = dlg2;

		this.item_Name	= item_Name;
		
//		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}

	public 
	DB_OCL
	(Activity actv, Dialog dlg1, long db_id) {
		// TODO Auto-generated constructor stub
		
		this.actv	= actv;
		this.d1	= dlg1;
		
		this.db_Id	= db_id;
		
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

	}

	public DB_OCL
	(Activity actv, Dialog d1, Dialog d2, Memo memo) {
		// TODO Auto-generated constructor stub
		
		this.actv	= actv;
		this.d1	= d1;
		this.d2	= d2;

		this.memo	= memo;
		
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}

	public DB_OCL
	(Activity actv, Dialog d1, Dialog d2, WordPattern wp) {
		// TODO Auto-generated constructor stub
		
		this.actv	= actv;
		this.d1	= d1;
		this.d2	= d2;

		this.wp	= wp;
		
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

	}

	public DB_OCL
	(Activity actv, 
		Dialog d1, Dialog d2, Dialog d3, Dialog d4) {
		// TODO Auto-generated constructor stub
		
		this.actv	= actv;
		this.d1	= d1;
		this.d2	= d2;
		this.d3	= d3;
		this.d4	= d4;

		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

	}

	public void 
	onClick(View v) {
		//
		Tags.DialogTags tag_name = (Tags.DialogTags) v.getTag();

		CONS.Admin.vib.vibrate(CONS.Admin.vibLength_click);
		
		// Used for sound effect
		boolean val;
		
		// Log
		Log.d("DialogButtonOnClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "tag_name.name()=" + tag_name.name());
		
		//
		switch (tag_name) {
		
		case DLG_CONF_DROP_CREATE_TABLE_FILTER_HISTORY_OK://-------------------
		
			case_DLG_CONF_DROP_CREATE_TABLE_FILTER_HISTORY_OK();
			
			break;
			
		case GENERIC_DISMISS://------------------------------------------------
			
			///////////////////////////////

			// bgm

			////////////////////////////////
			val = Methods.get_Pref_Boolean(
					actv, 
					CONS.Pref.pname_MainActv, 
					actv.getString(R.string.prefs_sound_effect_key), 
					false);

			if (val == true) {
				
				Methods.start_SE_new(actv, CONS.Audio.Clip.dialog_Cancel);
				
			}

//			Methods.start_SE(actv, CONS.Audio.Clip.dialog_Cancel);

			
			d1.dismiss();
			
			break;

		case GENERIC_DISMISS_SECOND_DIALOG: // ----------------------------------------------------
			
			///////////////////////////////

			// bgm

			////////////////////////////////
			val = Methods.get_Pref_Boolean(
					actv, 
					CONS.Pref.pname_MainActv, 
					actv.getString(R.string.prefs_sound_effect_key), 
					false);

			if (val == true) {
				
				Methods.start_SE_new(actv, CONS.Audio.Clip.dialog_Cancel);
				
			}
			
			d2.dismiss();
			
			break;// case dlg_generic_dismiss_second_dialog

		case GENERIC_DISMISS_THIRD_DIALOG://------------------------------------------------
			
			///////////////////////////////

			// bgm

			////////////////////////////////
			val = Methods.get_Pref_Boolean(
					actv, 
					CONS.Pref.pname_MainActv, 
					actv.getString(R.string.prefs_sound_effect_key), 
					false);

			if (val == true) {
				
				Methods.start_SE_new(actv, CONS.Audio.Clip.dialog_Cancel);
				
			}

			d3.dismiss();
			
			break;

		case GENERIC_DISMISS_4TH_DIALOG://------------------------------------------------
			
			d4.dismiss();
			
			break;
			
		case GENERIC_CLEAR_SECOND_DIALOG://------------------------------------------------
			
			case_GENERIC_CLEAR_SECOND_DIALOG();
			
			break;
			
		case DLG_CONF_IMPORT_DB_OK://------------------------------------------------
			
			case_DLG_CONF_IMPORT_DB_OK();
			
			break;
			
		case DLG_CONF_IMPORT_PATTERNS_OK://------------------------------------------------
			
			case_DLG_CONF_IMPORT_PATTERNS_OK();
			
			break;
			
		case DLG_CONF_CREATE_TABLE_PATTERNS_OK://------------------------------------------------
			
			case_DLG_CONF_CREATE_TABLE_PATTERNS_OK();
			
			break;
			
		case DLG_CONF_CREATE_TABLE_MEMOS_OK://------------------------------------------------
			
			case_DLG_CONF_CREATE_TABLE_MEMOS_OK();
			
			break;
			
		case DLG_CONF_DROP_TABLE_PATTERNS_OK://------------------------------------------------
			
			case_DLG_CONF_DROP_TABLE_PATTERNS_OK();
			
			break;
			
		case DLG_CONF_DROP_TABLE_MEMOS_OK://------------------------------------------------
			
			case_DLG_CONF_DROP_TABLE_MEMOS_OK();
			
			break;
			
		case DLG_CONF_RESTORE_DB_OK://------------------------------------------------
			
			case_DLG_CONF_RESTORE_DB_OK();
			
			break;
			
		case DLG_CONF_CLEAR_VIEW_OK://------------------------------------------------
			
			case_DLG_CONF_CLEAR_VIEW_OK();
			
			break;
			
//		case DLG_CONF_CLEAR_VIEW_PLAY_ACTV_OK://------------------------------------------------
//			
//			case_DLG_CONF_CLEAR_VIEW_PLAY_ACTV_OK();
//			
//			break;
			
		case DLG_FILTER_SHOWLIST_CLEAR://------------------------------------------------
			
			case_DLG_FILTER_SHOWLIST_CLEAR();
			
			break;
			
		case DLG_FILTER_SHOWLIST_OK://------------------------------------------------
			
			case_DLG_FILTER_SHOWLIST_OK_2();
//			case_DLG_FILTER_SHOWLIST_OK();
			
			break;
			
		case DLG_FILTER_SHOWLIST_RESET://------------------------------------------------
			
			case_DLG_FILTER_SHOWLIST_RESET();
			
			break;
			
		case DLG_REGISTER_PATTERNS_OK://------------------------------------------------
			
			case_DLG_REGISTER_PATTERNS_OK();
			
			break;
			
		case DLG_CONF_DELETE_MEMO_OK://------------------------------------------------
			
			case_DLG_CONF_DELETE_MEMO_OK();
			
			break;
			
		case DLG_CONF_DELETE_PATTERN_OK://------------------------------------------------
			
			case_DLG_CONF_DELETE_PATTERN_OK();
			
			break;
			
		case DLG_CONF_UPLOAD_DB_OK://------------------------------------------------
			
			case_DLG_CONF_UPLOAD_DB_OK();
			
			break;
			
		case DLG_CONF_ADD_COLUMN_USED_OK://------------------------------------------------
			
			case_DLG_CONF_ADD_COLUMN_USED_OK();
			
			break;
			
		case DLG_CONF_DROP_CREATE_TABLE_ADMIN_OK://------------------------------------------------
			
			case_DLG_CONF_DROP_CREATE_TABLE_ADMIN_OK();
			
			break;
			
		case DLG_EDIT_MEMOS_BT_OK://------------------------------------------------
			
			case_DLG_EDIT_MEMOS_BT_OK();
			
			break;
			
		case DLG_EDIT_MEMOS_ACTV_IMAGE_BT_OK://------------------------------------------------
			
			case_DLG_EDIT_MEMOS_ACTV_IMAGE_BT_OK();
			
			break;
			
		case DLG_EDIT_MEMOS_ACTV_IMAGE_FROM_SHOWLIST_BT_OK://------------------------------------------------
			
			case_DLG_EDIT_MEMOS_ACTV_IMAGE_FROM_SHOWLIST_BT_OK();
			
			break;
			
			
		default: // ----------------------------------------------------
			break;
		}//switch (tag_name)
	}//public void onClick(View v)

	private void 
	case_DLG_CONF_DROP_CREATE_TABLE_FILTER_HISTORY_OK() {
		// TODO Auto-generated method stub
	
		int res;
//		boolean res_b;
		
		////////////////////////////////
		
		// drop table
		
		////////////////////////////////
		String tname = CONS.DB.tname_FilterHistory;
		
		res = DBUtils.dropTable_2(actv, tname);
		
//		-1 Table doesn't exist
//		-2 SQLException
//		1 Table dropped
		
		if (res == -2) {
			
			String msg = "Table can't be dropped (SQLException): " + tname;
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);

			return;
		}
		
			//<<<<<<< HEAD
			// create table
			
		////////////////////////////////
		res = DBUtils.createTable_static(
				actv, 
				tname, 
				CONS.DB.col_names_FilterHistory, 
				CONS.DB.col_types_FilterHistory);
		
		////////////////////////////////
			
		// report
		
		////////////////////////////////
		String msg = null;
		int colorID = 0;
		
		switch(res) {
		
//		-1 Table exists
//		-2 SQLException
//		1 Table created
		
		case 1:
			
			msg = "Table created: " + tname
			;
			colorID = R.color.green4;
			
			////////////////////////////////
			
			// dismiss
			
			////////////////////////////////
			if(d3 != null) d3.dismiss();
			if(d2 != null) d2.dismiss();
			if(d1 != null) d1.dismiss();
			
			break;
			
		case -1:
			
			msg = "Table exists: " + tname;
			colorID = R.color.gold2;
			
//			if(d2 != null) d2.dismiss();
			////////////////////////////////
			
			// dismiss
			
			////////////////////////////////
			if(d3 != null) d3.dismiss();
			
			////////////////////////////////

			// message

			////////////////////////////////
			Methods_dlg.dlg_ShowMessage(
					actv, 
					msg,
					colorID);
			
			return;
//			break;
			
		case -2:
			
			msg = "SQLException: " + tname;
			colorID = R.color.red;
			
//			if(d2 != null) d2.dismiss();
			////////////////////////////////
			
			// dismiss
			
			////////////////////////////////
			if(d3 != null) d3.dismiss();
			
			break;
			
		default:
			
			msg = "Unknown result in creating a table => " + res;
			colorID = R.color.gold2;
			
			////////////////////////////////
			
			// dismiss
			
			////////////////////////////////
			if(d3 != null) d3.dismiss();
			
		}
		
			
	}//case_DLG_CONF_DROP_CREATE_TABLE_FILTER_HISTORY_OK
	
//=======
	private void
	case_DLG_EDIT_MEMOS_ACTV_IMAGE_FROM_SHOWLIST_BT_OK() {
		// TODO Auto-generated method stub
		
		// Log
		String msg_Log = "case_DLG_EDIT_MEMOS_ACTV_IMAGE_FROM_SHOWLIST_BT_OK";
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		////////////////////////////////
		
		// validate: any text?
		
		////////////////////////////////
		EditText et = (EditText) d1.findViewById(R.id.dlg_add_memos_et_content);
		
		String tmp = et.getText().toString();
		
		if (tmp == null) {
			
			String msg = "Text => null";
			Methods_dlg.dlg_ShowMessage_SecondDialog(actv, msg, d1);
//			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);
			
			return;
			
		}
		
		if (tmp.length() < 1) {
			
			String msg = "No text";
			Methods_dlg.dlg_ShowMessage_SecondDialog(actv, msg, d1);
//>>>>>>> master
			
			return;
			
		}
		
		////////////////////////////////
		
////<<<<<<< HEAD
//		// create table
//		
//		////////////////////////////////
//		res = DBUtils.createTable_static(
//				actv, 
//				tname, 
//				CONS.DB.col_names_FilterHistory, 
//				CONS.DB.col_types_FilterHistory);
//		
//		////////////////////////////////
//		
//		// report
//		
//=======
		// save memo
		
		////////////////////////////////
//		String text = String.format(CONS.RecActv.fmt_FileName_Photo, 
////				String text = String.format("@%s %s", 
//						 
//						CONS.IMageActv.ti.getDb_Id(), 
//						tmp);

		String tname = CONS.DB.tname_TA2;
		
		String where = CONS.DB.col_names_TA2_full[0] + " = ?";
		
		String[] args = new String[]{
				
				String.valueOf(CONS.IMageActv.memo.getDb_Id())
				
		};
		
		ContentValues cv = new ContentValues();
		
		cv.put(CONS.DB.col_names_TA2_full[3], tmp);
		
		boolean res = DBUtils.updateData_generic_static(
							actv, 
							CONS.DB.tname_TA2, 
							cv, 
							where, 
							args);
		
//		int res = DBUtils.save_Memo(actv, text);
//		boolean res = Methods.update_Memo_PlayActv(actv, d1);
		
		////////////////////////////////

		// db: IFM11

		////////////////////////////////
		if (res == true) {
			
			res = _update_ContentProvider_IFM11(actv, tmp);

			if (res == true) {
				
				// Log
				msg_Log = "contentprovider => updated: " + tmp;
				Log.d("DB_OCL.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
			} else {

				// Log
				msg_Log = "contentprovider => update failed: " + tmp;
				Log.d("DB_OCL.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
			}
			
		} else {
			
			// Log
			msg_Log = "TA2: db update => failed";
			Log.e("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
		////////////////////////////////

		// notify

		////////////////////////////////
		Memo m = Methods.find_Memo_from_ListView(actv, CONS.IMageActv.memo.getDb_Id());
		
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
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		
		////////////////////////////////

		// report

//>>>>>>> master
		////////////////////////////////
		String msg = null;
		int colorID = 0;
		
//<<<<<<< HEAD
//=======
		if (res == true) {

			msg = "text => updated";
			colorID = R.color.green4;

			d1.dismiss();
			
			Methods_dlg.dlg_ShowMessage(
//					Methods_dlg.dlg_ShowMessage_Duration(
					actv, 
					msg,
					colorID);

		} else {

			msg = "text => can't update";
			colorID = R.color.red;

//			d1.dismiss();
			
			Methods_dlg.dlg_ShowMessage_SecondDialog(actv, msg, d1);
					
		}
//
////		-1 Table doesnt exist
////		-2 SQLException
////		1 Table dropped
//
//		switch(res) {
//
//		case -1: 
//			
//			msg = "insertion => failed";
//			colorID = R.color.red;
//			
//			break;
//		
//		case -2: 
//			
//			msg = "Exception";
//			colorID = R.color.red;
//			
//			break;
//			
//		case 1: 
//			
//			msg = "text => inserted";
//			colorID = R.color.green4;
//
//			d1.dismiss();
//			
//			Methods_dlg.dlg_ShowMessage(
////					Methods_dlg.dlg_ShowMessage_Duration(
//					actv, 
//					msg,
//					colorID);
//
//			return;
////			break;
//			
//		default:
//			
//			msg = "Unknown result => " + res;
//			colorID = R.color.gold2;
//			
//			break;
//			
//		}
		
//		Methods_dlg.dlg_ShowMessage_SecondDialog(actv, msg, d1);
		
	}//case_DLG_EDIT_MEMOS_ACTV_IMAGE_BT_OK
	
	private boolean 
	_update_ContentProvider_IFM11
	(Activity actv, String tmp) {
		// TODO Auto-generated method stub

		////////////////////////////////

		// get: id

		////////////////////////////////
		String id_str = null;
		
		String fmt_FileName_PhotoMemo = "^&(\\d+)?";	// => 39
//		String fmt_FileName_PhotoMemo = "^&(\\d+?)";	// => 3
		
//		String text = "@2014-10-03_10-01-45-933.wav";
		String text = tmp;
		
		Pattern p = Pattern.compile(fmt_FileName_PhotoMemo);
		
		Matcher m = p.matcher(text);
		
		if (m.find()) {
//			if (m.matches()) {
			
			id_str = m.group(1);
			
			// Log
			String msg_Log = "matches => " + id_str;
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} else {
			
			// Log
			String msg_Log = "doesn't match => " + tmp;
			Log.e("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return false;
			
		}

		////////////////////////////////

		// prep: text

		////////////////////////////////
		int tmp_len = id_str.length();
		
		String text_trimmed = tmp.substring(tmp_len + 3);
//		String text_trimmed = tmp.substring(tmp_len + 2);
//		String text_trimmed = tmp.substring(tmp_len);
		
		// Log
		String msg_Log = String.format(
						"text_trimmed => \"%s\" (len = %d)",
						text_trimmed,
						tmp_len);
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// update

		////////////////////////////////
		Uri contentUri = Uri.parse(CONS.PhotoActv.content_Uri);

		ContentValues cv = new ContentValues();
		
		cv.put("memos", tmp);
		
		String where = android.provider.BaseColumns._ID
				+ " = ?";

		String[] args = new String[]{
				
				id_str
//				"312"
				
		};

		int updatedCount = actv.getContentResolver()
					.update(
							contentUri, 
//							ContentUris.withAppendedId(contentUri, 1), 
							cv, 
//							new ContentValues(), 
							where, args);
//		null, null);
		
		// Log
		msg_Log = "updatedCount => " + updatedCount;
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		////////////////////////////////

		// return

		////////////////////////////////
		if (updatedCount > 0) {
			
			return true;
			
		} else {

			return false;
			
		}
		
	}//DLG_EDIT_MEMOS_ACTV_IMAGE_FROM_SHOWLIST_BT_OK

	private void 
	case_DLG_EDIT_MEMOS_ACTV_IMAGE_BT_OK() {
		// TODO Auto-generated method stub
		
		// Log
		String msg_Log = "case_DLG_EDIT_MEMOS_ACTV_IMAGE_BT_OK";
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// validate: any text?
		
		////////////////////////////////
		EditText et = (EditText) d1.findViewById(R.id.dlg_add_memos_et_content);
		
		String tmp = et.getText().toString();
		
		if (tmp == null) {
			
			String msg = "Text => null";
			Methods_dlg.dlg_ShowMessage_SecondDialog(actv, msg, d1);
//			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);
			
			return;
			
		}
		
		if (tmp.length() < 1) {
			
			String msg = "No text";
			Methods_dlg.dlg_ShowMessage_SecondDialog(actv, msg, d1);
			
			return;
			
		}
		
		////////////////////////////////
		
		// save memo
		
		////////////////////////////////
		String text = String.format(CONS.RecActv.fmt_FileName_Photo, 
//				String text = String.format("@%s %s", 
				
				CONS.IMageActv.ti.getDb_Id(), 
				tmp);
		
		int res = DBUtils.save_Memo(actv, text);
//		boolean res = Methods.update_Memo_PlayActv(actv, d1);
		
		////////////////////////////////
		
		// report
		
		////////////////////////////////
		String msg = null;
		int colorID = 0;
		
//		-1 Table doesnt exist
//		-2 SQLException
//		1 Table dropped
		
		switch(res) {
		
		case -1: 
			
			msg = "insertion => failed";
			colorID = R.color.red;
			
			break;
			
		case -2: 
			
			msg = "Exception";
			colorID = R.color.red;
			
			break;
			
		case 1: 
			
			msg = "text => inserted";
			colorID = R.color.green4;
			
			d1.dismiss();
			
			Methods_dlg.dlg_ShowMessage(
//					Methods_dlg.dlg_ShowMessage_Duration(
					actv, 
					msg,
					colorID);
			
			return;
//			break;
			
		default:
			
			msg = "Unknown result => " + res;
			colorID = R.color.gold2;
//>>>>>>> master
			
			break;
			
		}
		
//<<<<<<< HEAD
		Methods_dlg.dlg_ShowMessage_ThirdDialog(
				actv, 
				msg,
				d1, d2,
				colorID);
		
	}//case DLG_CONF_DROP_CREATE_TABLE_FILTER_HISTORY_OK:
	

////=======
//		Methods_dlg.dlg_ShowMessage_SecondDialog(actv, msg, d1);
//		
//	}//case_DLG_EDIT_MEMOS_ACTV_IMAGE_BT_OK
	
//>>>>>>> master
	private void 
	case_DLG_EDIT_MEMOS_BT_OK() {
		// TODO Auto-generated method stub
		
		////////////////////////////////
		
		// validate: any text?
		
		////////////////////////////////
		EditText et = (EditText) d1.findViewById(R.id.dlg_add_memos_et_content);
		
		String tmp = et.getText().toString();
		
		if (tmp == null) {
			
			String msg = "Text => null";
			Methods_dlg.dlg_ShowMessage_SecondDialog(actv, msg, d1);
//			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);
			
			return;
			
		}
		
		if (tmp.length() < 1) {
			
			String msg = "No text";
			Methods_dlg.dlg_ShowMessage_SecondDialog(actv, msg, d1);
			
			return;
			
		}
		
		////////////////////////////////
		
		// save memo
		
		////////////////////////////////
		boolean res = Methods.update_Memo_PlayActv(actv, d1);
//		int res = Methods.save_Memo(actv, d1, R.id.dlg_add_memos_et_content);
//		int res = Methods.save_Memo(actv, R.id.actv_play_et);
		
//			-1	insertion => failed<br>
//			-2	Exception<br>
//			1	text => inserted<br>
		
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
		
		////////////////////////////////

		// closing

		////////////////////////////////
		if (res == true) {
			
			d1.dismiss();
			
			TextView tv_Memo = (TextView) actv.findViewById(R.id.actv_play_tv);
			
			tv_Memo.setText(tmp);
			
			////////////////////////////////

			// update: CONS.PlayActv.memo

			////////////////////////////////
			CONS.PlayActv.memo.setText(tmp);
			
			////////////////////////////////

			// notify

			////////////////////////////////
			Memo m = Methods.find_Memo_from_ListView(actv, CONS.PlayActv.memo.getDb_Id());
			
			if (m != null) {
				
				m.setText(tmp);
				
			} else {

				// Log
				String msg_Log = "memo => null";
				Log.e("DB_OCL.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
				
			}
			
			CONS.ShowListActv.adp_List_Memos.notifyDataSetChanged();
			
			// Log
			String msg_Log = "CONS.ShowListActv.adp_List_Memos => notified";
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} else {
			
			String msg = "Can't save memo";
			Methods_dlg.dlg_ShowMessage_SecondDialog(actv, msg, d1);

		}
		
//		Methods.report_Save_Memos(actv, res);
		
	}//case_DLG_EDIT_MEMOS_BT_OK
	
	private void 
	case_DLG_CONF_DROP_CREATE_TABLE_ADMIN_OK() {
		// TODO Auto-generated method stub
		
		int res;
		boolean res_b;
		
		////////////////////////////////
		
		// drop table
		
		////////////////////////////////
		String tname = CONS.DB.tname_Admin;
		
		res = DBUtils.dropTable_2(actv, tname);
		
//		-1 Table doesn't exist
//		-2 SQLException
//		1 Table dropped
		
		if (res == -2) {
			
			String msg = "Table can't be dropped (SQLException): " + tname;
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			return;
			
		}
		
		////////////////////////////////
		
		// create table
		
		////////////////////////////////
		res = DBUtils.createTable_static(
				actv, 
				tname, 
				CONS.DB.col_names_Admin, 
				CONS.DB.col_types_Admin);
		
		////////////////////////////////
		
		// report
		
		////////////////////////////////
		String msg = null;
		int colorID = 0;
		
		////////////////////////////////
		
		// dispatch
		
		////////////////////////////////
		switch(res) {
		
//		-1 Table exists
//		-2 SQLException
//		1 Table created
		
		case 1:
			
			msg = "Table created: " + tname
			;
			colorID = R.color.green4;
			
			////////////////////////////////
			
			// dismiss
			
			////////////////////////////////
			if(d3 != null) d3.dismiss();
			if(d2 != null) d2.dismiss();
			if(d1 != null) d1.dismiss();
			
			break;
			
		case -1:
			
			msg = "Table exists: " + tname;
			colorID = R.color.gold2;
			
//			if(d2 != null) d2.dismiss();
			////////////////////////////////
			
			// dismiss
			
			////////////////////////////////
			if(d3 != null) d3.dismiss();
			
			////////////////////////////////

			// message

			////////////////////////////////
			Methods_dlg.dlg_ShowMessage(
					actv, 
					msg,
					colorID);
			
			return;
//			break;
			
		case -2:
			
			msg = "SQLException: " + tname;
			colorID = R.color.red;
			
//			if(d2 != null) d2.dismiss();
			////////////////////////////////
			
			// dismiss
			
			////////////////////////////////
			if(d3 != null) d3.dismiss();
			
			break;
			
		default:
			
			msg = "Unknown result in creating a table => " + res;
			colorID = R.color.gold2;
			
			////////////////////////////////
			
			// dismiss
			
			////////////////////////////////
			if(d3 != null) d3.dismiss();
			
			break;
			
		}
		
		Methods_dlg.dlg_ShowMessage_ThirdDialog(
				actv, 
				msg,
				d1, d2,
				colorID);
		
	}//case_DLG_CONF_DROP_CREATE_TABLE_ADMIN_OK

	private void 
	case_DLG_CONF_ADD_COLUMN_USED_OK() {
		// TODO Auto-generated method stub
		
		Methods.addCol_PatternsUsed(actv, d1, d2, d3);
		
	}//case_DLG_CONF_ADD_COLUMN_USED_OK

	private void 
	case_DLG_CONF_UPLOAD_DB_OK() {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// validate: network status

		////////////////////////////////
		boolean res = Methods.isOnline(actv);
		
		if (res == false) {
			
			String msg = "Sorry. Network is not ready";
			Methods_dlg.dlg_ShowMessage_4thDialog(
							actv, d1, d2, d3,
							msg, R.color.gold2);
			
			return;
			
		} else {
			
			// Log
			String msg_Log = "Network is ready";
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
//		////////////////////////////////
//
//		// get view: checkbox
//
//		////////////////////////////////
//		CheckBox cb = (CheckBox) dlg2.findViewById(
//							R.id.dlg_tmpl_confirm_simple_cb_delete_file);

		////////////////////////////////

		// task

		////////////////////////////////
		Task_FTP task = new Task_FTP(
							actv, d1, d2, d3,
							CONS.Remote.FtpType.DB_FILE.toString()
							);
//		cb.isChecked());
		
		task.execute(CONS.Remote.FtpType.DB_FILE.toString());

	}//case_DLG_CONF_UPLOAD_DB_OK

	private void 
	case_DLG_CONF_DELETE_PATTERN_OK() {
		// TODO Auto-generated method stub
		
		Methods.delete_Pattern(actv, d1, d2, wp);
		
		
	}//case_DLG_CONF_DELETE_PATTERN_OK

	private void 
	case_DLG_CONF_DELETE_MEMO_OK() {
		// TODO Auto-generated method stub
		
		Methods.delete_Memo(actv, d1, d2, memo);
		
	}

	private void 
	case_DLG_REGISTER_PATTERNS_OK() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// view

		////////////////////////////////
		EditText et = (EditText) d2.findViewById(R.id.dlg_tmpl_ok_cancel_et);
		
		////////////////////////////////

		// get: value

		////////////////////////////////
		String new_Word = et.getText().toString();
		
		////////////////////////////////

		// validate: any input

		////////////////////////////////
		if (new_Word == null ) {
			
			String msg = "Input => null";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);
			
			return;
			
		}
		
		if (new_Word.length() < 1) {
			
			String msg = "No input";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			return;
			
		}
		
		////////////////////////////////

		// save word

		////////////////////////////////
		int res = DBUtils.save_Pattern(actv, new_Word);
		
		////////////////////////////////

		// report

		////////////////////////////////
		String msg = null;
		int colorID = 0;
		
//		-1 insertion => failed
//		-2 Exception
//		-3 pattern already in DB
//		1 Inserted

		
		switch(res) {
		
		case -1: 
			
			msg = "insertion => failed";
			colorID = R.color.red;
			
			break;
		
		case -2: 
			
			msg = "Exception";
			colorID = R.color.red;
			
			break;
			
		case -3: 
			
			msg = "pattern already in DB";
			colorID = R.color.red;
			
			break;
			
		case 1: 
			
			msg = "Inserted => " + new_Word;
			colorID = R.color.green4;
			
			d2.dismiss();
			d1.dismiss();
			
			// Update listviews
			Methods.update_MemoActv_ListViews(actv, new_Word);
			
			break;
			
		default:
			
			msg = "Unknown result => " + res;
			colorID = R.color.gold2;
			
			d2.dismiss();
			d1.dismiss();
			
			break;
			
		}
		
		Methods_dlg.dlg_ShowMessage(
//				Methods_dlg.dlg_ShowMessage_Duration(
				actv, 
				msg,
				colorID);
		
		
	}//case_DLG_REGISTER_PATTERNS_OK

	private void 
	case_DLG_FILTER_SHOWLIST_RESET() {
		// TODO Auto-generated method stub

		////////////////////////////////

		// list size

		////////////////////////////////
		String pref_MemoList_Size = Methods.get_Pref_String(
							actv, 
							CONS.Pref.pname_MainActv, 
							actv.getString(R.string.prefs_MemoList_Size_key), 
							null);

		////////////////////////////////

		// get: list

		////////////////////////////////
		List<Memo> list_Memos = DBUtils.find_All_Memos(
						actv, 
						CONS.Enums.SortOrder.DESC, 
						Integer.parseInt(pref_MemoList_Size));
		
		////////////////////////////////

		// update: list

		////////////////////////////////
		if (list_Memos == null) {
			
			String msg = "Can't build list";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			return;
			
		}
		
		// Log
		String msg_Log = "list_Memos.size() => " + list_Memos.size();
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		

		CONS.ShowListActv.list_Memos.clear();
		CONS.ShowListActv.list_Memos.addAll(list_Memos);
		
		////////////////////////////////

		// notify

		////////////////////////////////
		CONS.ShowListActv.adp_List_Memos.notifyDataSetChanged();
		
		////////////////////////////////

		// dismiss

		////////////////////////////////
		d1.dismiss();
		
	}//case_DLG_FILTER_SHOWLIST_RESET

	@SuppressWarnings("unused")
	private void 
	case_DLG_FILTER_SHOWLIST_OK() {
		// TODO Auto-generated method stub
		
		String msg_Log;
		
		////////////////////////////////

		// get view

		////////////////////////////////
		EditText et = (EditText) d1.findViewById(R.id.dlg_filter_showlist_et_content);
		
		////////////////////////////////

		// validate: multiple keywords

		////////////////////////////////
		String input = et.getText().toString();
		
		input = input.trim();
		
		input = input.replaceAll("　", " ");
		
		input = input.replaceAll(" +", " ");
		
		// Log
		msg_Log = "input is now => " + input;
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
//		String[] tokens = input.split(" +");
		String[] tokens = input.split(" ");
		
		if (tokens == null) {
			
			String msg = "Split the input => null";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			return;
			
		}

		// Log
		msg_Log = "tokens.length => " + tokens.length;
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// setup: where, args

		////////////////////////////////
		String where = null;
		
		String[] args = null;

		////////////////////////////////

		// condition: NOT

		////////////////////////////////
		RadioGroup rg = (RadioGroup) d1.findViewById(R.id.dlg_filter_showlist_rg);
		
		int RB_id_Checked = rg.getCheckedRadioButtonId();

		////////////////////////////////

		// dispatch

		////////////////////////////////
		if (tokens.length <= 1) {
			
			if (RB_id_Checked == R.id.dlg_filter_showlist_rb_not) {
				
				where = CONS.DB.col_names_TA2[0] + " NOT LIKE ?";
				
			} else {

				where = CONS.DB.col_names_TA2[0] + " LIKE ?";
				
			}
			
			// Log
			msg_Log = "where => " + where;
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			//REF http://monoist.atmarkit.co.jp/mn/articles/1209/21/news003.html "正しく動作する記述を以下に"
//			where = CONS.DB.col_names_TA2[0] + " like ?";
//			String where = CONS.DB.col_names_IFM11[11] + " = ?";
			
			args = new String[]{
					
							"%" + et.getText().toString() + "%"
					
					};
			
		} else {
			
			//test
			where = this._Filter_ShowList_Build_Conditions_where(actv, d1);
			
//			this._Filter_ShowList_Build_Conditions(actv, d1, where, args);
//			Object[] objects = this._Filter_ShowList_Build_Conditions(actv, d1);
			
			// Log
			msg_Log = "where => " + where;
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
//			args = this._Filter_ShowList_Build_Conditions_args(actv, d1);
			args = tokens;
			
			for (int i = 0; i < args.length; i++) {
				
				args[i] = "%" + args[i] + "%";
				
				// Log
				msg_Log = "args => " + args[i];
				Log.d("DB_OCL.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", msg_Log);
			}
			
		}//if (tokens.length <= 1)
		
		List<Memo> list_Memos = DBUtils.find_All_Memos_conditions(
						actv, 
						CONS.Enums.SortOrder.DESC, 
						where, 
						args);
		
		////////////////////////////////

		// update: list

		////////////////////////////////
		if (list_Memos == null) {
			
			String msg = "Can't build list";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			return;
			
		}
		
		// Log
		msg_Log = "list_Memos.size() => " + list_Memos.size();
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		

		CONS.ShowListActv.list_Memos.clear();
		CONS.ShowListActv.list_Memos.addAll(list_Memos);
		
		////////////////////////////////

		// notify

		////////////////////////////////
		CONS.ShowListActv.adp_List_Memos.notifyDataSetChanged();
		
		////////////////////////////////

		// dismiss

		////////////////////////////////
		d1.dismiss();
		
		////////////////////////////////

		// store: string --> pref

		////////////////////////////////
		boolean res = Methods.set_Pref_String(
				actv, 
				CONS.Pref.pname_MainActv, 
				CONS.Pref.pkey_ShowListActv_Filter_String, 
				input);
		
		if (res == true) {
			
			// Log
			msg_Log = "pref filter => set: " + input;
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} else {

			// Log
			msg_Log = "pref filter => not set: " + input;
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}

//				input
	}//case_DLG_FILTER_SHOWLIST_OK
	
	@SuppressWarnings("unused")
	private void 
	case_DLG_FILTER_SHOWLIST_OK_2() {
		// TODO Auto-generated method stub
		
		String msg_Log;
		
		int res_i;
		
		////////////////////////////////
		
		// get view
		
		////////////////////////////////
		EditText et = (EditText) d1.findViewById(R.id.dlg_filter_showlist_et_content);
		
		////////////////////////////////
		
		// validate: multiple keywords
		
		////////////////////////////////
		String input = et.getText().toString();
		
		input = input.trim();
		
		input = input.replaceAll("　", " ");
		
		input = input.replaceAll(" +", " ");
		
		// Log
		msg_Log = "input is now => " + input;
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
//		String[] tokens = input.split(" +");
		String[] tokens = input.split(" ");
		
		if (tokens == null) {
			
			String msg = "Split the input => null";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			return;
			
		}
		
		// Log
		msg_Log = "tokens.length => " + tokens.length;
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// setup: where, args
		
		////////////////////////////////
		String where = null;
		
		String[] args = null;
		
		List<Memo> list_Memos = null;
		
		////////////////////////////////
		
		// condition: NOT
		
		////////////////////////////////
		RadioGroup rg = (RadioGroup) d1.findViewById(R.id.dlg_filter_showlist_rg);
		
		int RB_id_Checked = rg.getCheckedRadioButtonId();
		
		////////////////////////////////
		
		// dispatch
		
		////////////////////////////////
		if (tokens.length <= 1) {
			
			// Log
			msg_Log = "tokens.length <= 1";
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			list_Memos = Methods.filter_MemoList_Single_KW(actv, RB_id_Checked, et);
			
		} else {//if (tokens.length <= 1)
			
			list_Memos = Methods.filter_MemoList_Multiple_KW(actv, RB_id_Checked, tokens);
			
		}//if (tokens.length <= 1)
		
		////////////////////////////////
		
		// update: list
		
		////////////////////////////////
		if (list_Memos == null) {
			
			String msg = "Can't build list";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			return;
			
		}
		
		// Log
		msg_Log = "list_Memos.size() => " + list_Memos.size();
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		
		CONS.ShowListActv.list_Memos.clear();
		CONS.ShowListActv.list_Memos.addAll(list_Memos);
		
		////////////////////////////////
		
		// notify
		
		////////////////////////////////
		CONS.ShowListActv.adp_List_Memos.notifyDataSetChanged();
		
		////////////////////////////////
		
		// dismiss
		
		////////////////////////////////
		d1.dismiss();
		
		////////////////////////////////
		
		// store: string --> pref
		
		////////////////////////////////
		boolean res = Methods.set_Pref_String(
				actv, 
				CONS.Pref.pname_MainActv, 
				CONS.Pref.pkey_ShowListActv_Filter_String, 
				input);
		
		if (res == true) {
			
			// Log
			msg_Log = "pref filter => set: " + input;
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} else {
			
			// Log
			msg_Log = "pref filter => not set: " + input;
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
		////////////////////////////////

		// save: filter history

		////////////////////////////////
		////////////////////////////////

		// validate: same filter

		////////////////////////////////
		FilterHistory fh_Prev = DBUtils.find_FH_latest(actv);
		
		if (input.equals(fh_Prev.getKeywords())
				&& RB_id_Checked == fh_Prev.getOperator()
					) {
				
				// Log
				msg_Log = "same filter. not saving";
				Log.d("Methods.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", msg_Log);
		} else {
			
			// Log
			msg_Log = "saving filter...";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			RadioButton rb = (RadioButton) rg.findViewById(RB_id_Checked);
			
			Methods.save_Filter(actv, input, RB_id_Checked, rb.getText().toString());
//				_filter_MemoList_History__SaveFilter(actv, input, RB_id_Checked, fh);
			
		}

		
		
////		android.provider.BaseColumns._ID,		// 0
////		"created_at", "modified_at",			// 1,2
////		"keywords",									// 3
////		"operator",									// 4
////		"op_label",									// 5
//		
//		ContentValues cv = new ContentValues();
//		
//		cv.put(CONS.DB.col_names_FilterHistory_full[1], 
//				Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
//		
//		cv.put(CONS.DB.col_names_FilterHistory_full[2], 
//				Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now()));
//		
//		cv.put(CONS.DB.col_names_FilterHistory_full[3], input);
//		
//		cv.put(CONS.DB.col_names_FilterHistory_full[4], RB_id_Checked);
//		
//		// operator label
//		RadioButton rb = (RadioButton) rg.findViewById(RB_id_Checked);
//		
//		if (rb != null) {
//			
//			String label = rb.getText().toString();
//			
//			if (label != null && !label.equals("")) {
//				
//				cv.put(CONS.DB.col_names_FilterHistory_full[5], label);
//				
//			}
//			
//		}
//		
//		res = DBUtils.insert_Data_generic(actv, CONS.DB.tname_FilterHistory, cv);
//		
//		if (res == true) {
//			
//			// Log
//			msg_Log = "filter history => saved: " + input;
//			Log.d("DB_OCL.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//		} else {
//
//			// Log
//			msg_Log = "filter history => not saved: " + input;
//			Log.d("DB_OCL.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//		}
		
	}//case_DLG_FILTER_SHOWLIST_OK
	

	private void 
//			private Object[] 
	_Filter_ShowList_Build_Conditions
	(Activity actv, 
		Dialog d1, String where, String[] args) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// EditText

		////////////////////////////////
		EditText et = (EditText) d1.findViewById(R.id.dlg_filter_showlist_et_content);

		String[] tokens = et.getText().toString().trim().split(" ");
		
		////////////////////////////////

		// RagioGroup

		////////////////////////////////
		RadioGroup rg = (RadioGroup) d1.findViewById(R.id.dlg_filter_showlist_rg);
		
//		String where = "";
//		String[] args = null;
		List<String> list_args = new ArrayList<String>();
		
		////////////////////////////////

		// validate: multiple

		////////////////////////////////
		//REF http://www.adakoda.com/android/000067.html
		int RB_id_Checked = rg.getCheckedRadioButtonId();
		
		if (RB_id_Checked == R.id.dlg_filter_showlist_rb_and) {
			
			// Log
			String msg_Log = "AND is checked";
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);

			////////////////////////////////

			// where

			////////////////////////////////
			int i;
			
			for (i = 0; i < tokens.length - 1; i++) {
				
				where += CONS.DB.col_names_TA2[0] + " like ?"
						+ " " + "AND" + " ";
				
				list_args.add(tokens[i]);
				
			}
			
			where += CONS.DB.col_names_TA2[0] + " like ?";
			
			list_args.add(tokens[i]);
			
		} else if (RB_id_Checked == R.id.dlg_filter_showlist_rb_or) {
			
			// Log
			String msg_Log = "OR is checked";
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);

			////////////////////////////////

			// where

			////////////////////////////////
			int i;
			
			for (i = 0; i < tokens.length - 1; i++) {
				
				where += CONS.DB.col_names_TA2[0] + " like ?"
						+ " " + "OR" + " ";
				
				list_args.add(tokens[i]);
				
			}
			
			where += CONS.DB.col_names_TA2[0] + " like ?";
			
			list_args.add(tokens[i]);

		} else if (RB_id_Checked == R.id.dlg_filter_showlist_rb_not) {
			
			// Log
			String msg_Log = "NOT is checked";
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			////////////////////////////////
			
			// where
			
			////////////////////////////////
			int i;
			
			for (i = 0; i < tokens.length - 1; i++) {
				
				where += CONS.DB.col_names_TA2[0] + " NOT LIKE ?"
						+ " " + "AND" + " ";
				
				list_args.add(tokens[i]);
				
			}
			
			where += CONS.DB.col_names_TA2[0] + " NOT LIKE ?";
			
			list_args.add(tokens[i]);
			
		} else {
			
			// Log
			String msg_Log = "unknown radio button id => " + RB_id_Checked;
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			where = null;
			
		}

		////////////////////////////////

		// set: args

		////////////////////////////////
//		args = (String[]) list_args.toArray();
		
		args = new String[list_args.size()];
		
		for (int i = 0; i < list_args.size(); i++) {
			
			args[i] = list_args.get(i);
			
		}
		
		////////////////////////////////

		// return

		////////////////////////////////
//		return new Object[]{where, args.toArray()};
		
		////////////////////////////////

		// get: list

		////////////////////////////////
		//REF http://monoist.atmarkit.co.jp/mn/articles/1209/21/news003.html "正しく動作する記述を以下に"
//		String where = CONS.DB.col_names_TA2[0] + " like ?";
////		String where = CONS.DB.col_names_IFM11[11] + " = ?";
//		
//		String[] args = new String[]{
//				
//						"%" + et.getText().toString() + "%"
//				
//				};

		
		
//		return null;
	}

	private String[]
	_Filter_ShowList_Build_Conditions_args
	(Activity actv, Dialog d1) {
		// TODO Auto-generated method stub
		////////////////////////////////
		
		// EditText
		
		////////////////////////////////
		EditText et = (EditText) d1.findViewById(R.id.dlg_filter_showlist_et_content);
		
		String[] tokens = et.getText().toString().trim().split(" ");
		
//		String where = "";
		String[] args = new String[tokens.length];
//		List<String> list_args = new ArrayList<String>();
		
		
		
		////////////////////////////////
		
		// where
		
		////////////////////////////////
		int i;
		
		for (i = 0; i < tokens.length; i++) {
			
//			list_args.add(tokens[i]);
//			args[i] = tokens[i]
			
		}
			
//		////////////////////////////////
//		
//		// set: args
//		
//		////////////////////////////////
////		args = (String[]) list_args.toArray();
//		
//		String[] args = new String[list_args.size()];
//		
//		for (i = 0; i < list_args.size(); i++) {
//			
//			args[i] = list_args.get(i);
//			
//		}
		
		////////////////////////////////
		
		// return
		
		////////////////////////////////
		return args;
		
	}//_Filter_ShowList_Build_Conditions_args
	
	
	private String
	_Filter_ShowList_Build_Conditions_where
	(Activity actv, Dialog d1) {
		// TODO Auto-generated method stub
		////////////////////////////////
		
		// EditText
		
		////////////////////////////////
		EditText et = (EditText) d1.findViewById(R.id.dlg_filter_showlist_et_content);
		
		String input = et.getText().toString();
		
		input = input.trim();
		
		input = input.replaceAll("　", " ");
		
		input = input.replaceAll(" +", " ");
		
		String[] tokens = input.split(" ");
//		String[] tokens = et.getText().toString().trim().split(" ");
		
		////////////////////////////////
		
		// RagioGroup
		
		////////////////////////////////
		RadioGroup rg = (RadioGroup) d1.findViewById(R.id.dlg_filter_showlist_rg);
		
		String where = "";
		
		////////////////////////////////
		
		// validate: multiple
		
		////////////////////////////////
		//REF http://www.adakoda.com/android/000067.html
		int RB_id_Checked = rg.getCheckedRadioButtonId();
		
		if (RB_id_Checked == R.id.dlg_filter_showlist_rb_and) {
			
			// Log
			String msg_Log = "AND is checked";
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			////////////////////////////////
			
			// where
			
			////////////////////////////////
			int i;
			
			for (i = 0; i < tokens.length - 1; i++) {
				
				where += CONS.DB.col_names_TA2[0] + " like ?"
						+ " " + "AND" + " ";
				
			}
			
			where += CONS.DB.col_names_TA2[0] + " like ?";
			
		} else if (RB_id_Checked == R.id.dlg_filter_showlist_rb_or) {
			
			// Log
			String msg_Log = "OR is checked";
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			////////////////////////////////
			
			// where
			
			////////////////////////////////
			int i;
			
			for (i = 0; i < tokens.length - 1; i++) {
				
				where += CONS.DB.col_names_TA2[0] + " like ?"
						+ " " + "OR" + " ";
				
			}
			
			where += CONS.DB.col_names_TA2[0] + " like ?";
			
		} else if (RB_id_Checked == R.id.dlg_filter_showlist_rb_not) {
			
			// Log
			String msg_Log = "NOT is checked";
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			////////////////////////////////
			
			// where
			
			////////////////////////////////
			int i;
			
			for (i = 0; i < tokens.length - 1; i++) {
				
				where += CONS.DB.col_names_TA2[0] + " NOT LIKE ?"
						+ " " + "AND" + " ";
				
			}
			
			where += CONS.DB.col_names_TA2[0] + " NOT LIKE ?";
			
		} else {
			
			// Log
			String msg_Log = "unknown radio button id => " + RB_id_Checked;
			Log.d("DB_OCL.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			where = null;
			
		}

		////////////////////////////////
		
		// return
		
		////////////////////////////////
		return where;
		
	}//_Filter_ShowList_Build_Conditions_where
	
	private void 
	case_DLG_FILTER_SHOWLIST_CLEAR() {
		// TODO Auto-generated method stub
		////////////////////////////////
	
		// view
	
		////////////////////////////////
		EditText et = (EditText) d1.findViewById(R.id.dlg_filter_showlist_et_content);
		
		////////////////////////////////

		// clear

		////////////////////////////////
		et.setText("");
		
	}

	private void 
	case_DLG_CONF_DROP_TABLE_MEMOS_OK() {
		// TODO Auto-generated method stub
		
		int res = Methods.drop_Table(actv, CONS.DB.tname_TA2);
		
		////////////////////////////////

		// report

		////////////////////////////////
//		-1 Table doesnt exist
//		-2 SQLException
//		1 Table dropped
		
		Methods.report_DropTable_Memos(actv, res, d1, d2, d3);
		
	}

	private void 
	case_DLG_CONF_CREATE_TABLE_MEMOS_OK() {
		// TODO Auto-generated method stub

		Methods.createTable_Memos(actv, d1, d2, d3);
		
	}

	private void 
	case_DLG_CONF_CLEAR_VIEW_OK() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// view

		////////////////////////////////
		EditText et = (EditText) actv.findViewById(R.id.actv_memo_et);
		
		////////////////////////////////

		// clear

		////////////////////////////////
		et.setText("");
		
		////////////////////////////////

		// dismiss

		////////////////////////////////
		d1.dismiss();
		
	}

//	private void 
//	case_DLG_CONF_CLEAR_VIEW_PLAY_ACTV_OK() {
//		// TODO Auto-generated method stub
//		////////////////////////////////
//		
//		// view
//		
//		////////////////////////////////
//		EditText et = (EditText) actv.findViewById(R.id.actv_play_et);
//		
//		////////////////////////////////
//		
//		// clear
//		
//		////////////////////////////////
//		et.setText("");
//		
//		////////////////////////////////
//		
//		// dismiss
//		
//		////////////////////////////////
//		d1.dismiss();
//		
//	}//case_DLG_CONF_CLEAR_VIEW_PLAY_ACTV_OK
	
	private void 
	case_DLG_CONF_RESTORE_DB_OK() {
		// TODO Auto-generated method stub
		
		Methods.restore_DB(actv, d1, d2);
		
	}

	private void 
	case_DLG_CONF_DROP_TABLE_PATTERNS_OK() {
		// TODO Auto-generated method stub
		
		Methods.drop_Table(actv, CONS.DB.tname_Patterns, d1, d2, d3);
		
		
	}//case_DLG_CONF_DROP_TABLE_PATTERNS_OK

	private void 
	case_DLG_CONF_CREATE_TABLE_PATTERNS_OK() {
		// TODO Auto-generated method stub
		
		Methods.createTable_Patterns(actv, d1, d2, d3);
		
		
		
	}//case_DLG_CONF_CREATE_TABLE_PATTERNS_OK

	private void 
	case_DLG_CONF_IMPORT_PATTERNS_OK() {
		// TODO Auto-generated method stub
		
		int res = Methods.import_Patterns(actv);
		
		////////////////////////////////

		// report

		////////////////////////////////
		String msg = null;
		int colorID = 0;
		
//		>1 Number of patterns saved
//		0 No patterns saved
//		-1 Table 'patterns' => not exist
//		-2 Can't build list
//		-3 Unknown result

		
		switch(res) {

//		-1	Table exists<br>
//		-2	Exception in executing the sql<br>
//		1	Table created<br>
		
		case -1: 
			
			msg = "Table 'patterns' => not exist";
			colorID = R.color.gold2;
			
			d3.dismiss();
			
			break;
		
		case -2: 
			
			msg = "Can't build patterns list";
			colorID = R.color.red;
			
			d3.dismiss();
			
			break;
			
		case -3: 
			
			msg = "Unknown result";
			colorID = R.color.red;
			
			d3.dismiss();
			
			break;
			
		case 0: 
			
			msg = "No patterns saved";
			colorID = R.color.gold2;
			
			d3.dismiss();
			
			break;
			
		default:
			
			msg = "patterns saved: " + res;
			colorID = R.color.green4;
			
			d3.dismiss();
			d2.dismiss();
			d1.dismiss();
			
			break;
			
		}
		
		Methods_dlg.dlg_ShowMessage(
//				Methods_dlg.dlg_ShowMessage_Duration(
				actv, 
				msg,
				colorID);

		
		
	}//case_DLG_CONF_IMPORT_PATTERNS_OK

	private void 
	case_GENERIC_CLEAR_SECOND_DIALOG() {
		// TODO Auto-generated method stub
		
		// Log
		String msg_Log = "clearing all dialogues...";
		Log.d("DB_OCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		d2.dismiss();
		d1.dismiss();
		
		
		
	}

	private void 
	case_DLG_CONF_IMPORT_DB_OK() {
		// TODO Auto-generated method stub
		
		Methods.import_DB(actv, d1, d2, d3);
		
//		// Log
//		String msg_Log = "Importing db file...";
//		Log.d("DB_OCL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
	}

}//DialogButtonOnClickListener
