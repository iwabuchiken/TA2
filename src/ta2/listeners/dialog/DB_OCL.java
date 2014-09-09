package ta2.listeners.dialog;

import java.util.ArrayList;
import java.util.List;

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
import android.content.Context;
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

	public void onClick(View v) {
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
			
		case DLG_FILTER_SHOWLIST_CLEAR://------------------------------------------------
			
			case_DLG_FILTER_SHOWLIST_CLEAR();
			
			break;
			
		case DLG_FILTER_SHOWLIST_OK://------------------------------------------------
			
			case_DLG_FILTER_SHOWLIST_OK();
			
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
			
			
		default: // ----------------------------------------------------
			break;
		}//switch (tag_name)
	}//public void onClick(View v)

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
							actv, d1, d2,
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

		// get: list

		////////////////////////////////
		List<Memo> list_Memos = DBUtils.find_All_Memos(
						actv, 
						CONS.Enums.SortOrder.DESC);
		
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
		
	}

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

		// dispatch

		////////////////////////////////
		if (tokens.length <= 1) {
			
			//REF http://monoist.atmarkit.co.jp/mn/articles/1209/21/news003.html "正しく動作する記述を以下に"
			where = CONS.DB.col_names_TA2[0] + " like ?";
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
			
			
//			// Log
//			msg_Log = "objects[0] => " + ((String) objects[0]);
//			Log.d("DB_OCL.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//			if (objects[1] != null) {
//				
//				String[] tmp = (String[]) objects[1];
//				
//				for (Object object : objects[1]) {
////				for (String object : tmp) {
////					for (String object : (String[])objects[1]) {
////					for (Object object : (String[])objects[1]) {
//					
//					// Log
//					msg_Log = "object => " + object;
////					msg_Log = "object => " + (String) object;
//					Log.d("DB_OCL.java"
//							+ "["
//							+ Thread.currentThread().getStackTrace()[2]
//									.getLineNumber() + "]", msg_Log);
//				}
//				
//			} else {
//				
//				// Log
//				msg_Log = "objects[1] => null";
//				Log.d("DB_OCL.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber() + "]", msg_Log);
//				
//			}
			
//			where = (String) objects[0];
//			
//			args = (String[]) objects[1];
			
//			//REF http://monoist.atmarkit.co.jp/mn/articles/1209/21/news003.html "正しく動作する記述を以下に"
//			where = CONS.DB.col_names_TA2[0] + " like ?";
////			String where = CONS.DB.col_names_IFM11[11] + " = ?";
//			
//			args = new String[]{
//					
//					"%" + et.getText().toString() + "%"
//					
//			};
			
		}//if (tokens.length <= 1)
		
//		////////////////////////////////
//
//		// and/or
//
//		////////////////////////////////
////		Object[] objects = _Filter_ShowList_Build_Conditions(actv, d1);
////		
////		String where = (String) objects[0];
////		String[] args = (String[]) objects[1];
//		
//		RadioGroup rg = (RadioGroup) d1.findViewById(R.id.dlg_filter_showlist_rg);
//		
//		int RB_id_Checked = rg.getCheckedRadioButtonId();
//		
//		if (RB_id_Checked == R.id.dlg_filter_showlist_rb_and) {
//			
//			// Log
//			msg_Log = "AND is checked";
//			Log.d("DB_OCL.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//		} else if (RB_id_Checked == R.id.dlg_filter_showlist_rb_or) {
//			
//			// Log
//			msg_Log = "OR is checked";
//			Log.d("DB_OCL.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//		} else {
//			
//			// Log
//			msg_Log = "unknown radio button id => " + RB_id_Checked;
//			Log.d("DB_OCL.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//		}
		
		////////////////////////////////

		// get: list

		////////////////////////////////
//		//REF http://monoist.atmarkit.co.jp/mn/articles/1209/21/news003.html "正しく動作する記述を以下に"
//		where = CONS.DB.col_names_TA2[0] + " like ?";
////		String where = CONS.DB.col_names_IFM11[11] + " = ?";
//		
//		args = new String[]{
//				
//						"%" + et.getText().toString() + "%"
//				
//				};

		
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
