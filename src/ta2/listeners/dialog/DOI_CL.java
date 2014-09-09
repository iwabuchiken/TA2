package ta2.listeners.dialog;


import ta2.items.ListItem;
import ta2.items.Memo;
import ta2.items.WordPattern;
import ta2.main.R;
import ta2.tasks.Task_AudioTrack;
import ta2.utils.CONS;
import ta2.utils.Methods;
import ta2.utils.Methods_dlg;
import ta2.utils.Tags;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DOI_CL implements OnItemClickListener {

	//
	Activity actv;
	Dialog d1;
	Dialog d2;
	
	//
	Vibrator vib;
	private String file_Name;
	private Memo memo;
	private WordPattern wp;
	
	
	//
//	Methods.DialogTags dlgTag = null;

	public DOI_CL(Activity actv, Dialog dlg) {
		// 
		this.actv = actv;
		this.d1 = dlg;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
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

		
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)

	public DOI_CL(Activity actv, Dialog dlg, Dialog dlg2) {
		// 
		this.actv = actv;
		this.d1 = dlg;
		this.d2 = dlg2;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)


	public DOI_CL
	(Activity actv, Dialog dlg1, String file_Name) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		this.d1 = dlg1;
		
		this.file_Name	= file_Name;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

	}

	public 
	DOI_CL
	(Activity actv, Dialog d1, Memo memo) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		this.d1 = d1;
		
		this.memo	= memo;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

	}

	public DOI_CL
	(Activity actv, Dialog d1, WordPattern wp) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		this.d1 = d1;
		
		this.wp	= wp;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

	}

	//	@Override
	public void 
	onItemClick
	(AdapterView<?> parent, View v, int position, long id) {
		/*----------------------------
		 * Steps
		 * 1. Get tag
		 * 2. Vibrate
		 * 3. Switching
			----------------------------*/
		
		Tags.DialogItemTags tag = (Tags.DialogItemTags) parent.getTag();
//		
		vib.vibrate(CONS.Admin.vibLength_click);
		
//		String item = (String) parent.getItemAtPosition(position);
		
		ListItem li;
		WordPattern	wp;
		String item;
		
		/*----------------------------
		 * 3. Switching
			----------------------------*/
		switch (tag) {
		
//		case dlg_db_admin_lv://----------------------------------------------
		case ACTV_MAIN_ADMIN_LV://----------------------------------------------
			
			li = (ListItem) parent.getItemAtPosition(position);
			
			case_Admin_LV(li);
			
			break;// case dlg_add_memos_gv

		case ACTV_MAIN_ADMIN_LV_OPS://----------------------------------------------
			
			li = (ListItem) parent.getItemAtPosition(position);
			
			case_ACTV_MAIN_ADMIN_LV_OPS(li);
			
			break;// case dlg_add_memos_gv
			
		case GV_FILTER_SHOWLIST://----------------------------------------------
			
			wp = (WordPattern) parent.getItemAtPosition(position);
			
			case_GV_FILTER_SHOWLIST(wp);
			
			break;// case dlg_add_memos_gv
			
		case ACTV_MEMO_ADMIN_PATTERNS://----------------------------------------------
			
			li = (ListItem) parent.getItemAtPosition(position);
			
			case_ACTV_MEMO_ADMIN_PATTERNS(li);
			
			break;// case dlg_add_memos_gv
			
		case ACTV_SHOWLIST_LV://----------------------------------------------
			
			li = (ListItem) parent.getItemAtPosition(position);
			
			case_ACTV_SHOWLIST_LV(li);
			
			break;// case dlg_add_memos_gv
			
		case ACTV_MEMO_ADMIN_PATTERNS_GV://----------------------------------------------
			
			li = (ListItem) parent.getItemAtPosition(position);
			
			case_ACTV_MEMO_ADMIN_PATTERNS_GV(li);
			
			break;// case dlg_add_memos_gv
			
			
		default:
			break;
		}//switch (tag)
		
	}//public void onItemClick(AdapterView<?> parent, View v, int position, long id)

	private void 
	case_ACTV_MEMO_ADMIN_PATTERNS_GV
	(ListItem li) {
		// TODO Auto-generated method stub
	
		if (li.getText().equals(actv.getString(
				R.string.generic_tv_edit))) {

//			Methods_dlg.register_Patterns(actv, d1);
			
//			Methods_dlg.dlg_Admin_Ops(actv, d1);
			
		} else if (li.getText().equals(actv.getString(
				R.string.generic_tv_delete))) {//if (choice.equals(actv.getString(R.string.generic_tv_delete))))

			Methods_dlg.conf_Delete_Pattern(actv, d1, wp);
			
		} else {
			
			String msg = "Unknown choice => " + li.getText();
			Methods_dlg.dlg_ShowMessage_SecondDialog(actv, msg, d1);
			
		}
		
	}//case_ACTV_MEMO_ADMIN_PATTERNS_GV

	private void 
	case_ACTV_SHOWLIST_LV
	(ListItem li) {
		// TODO Auto-generated method stub
	
		////////////////////////////////

		// dispatch

		////////////////////////////////
		if (li.getText().equals(actv.getString(
				R.string.generic_tv_edit))) {

			Methods.start_Activity_MemoEditActv(actv, d1, memo);
			
//			Methods_dlg.register_Patterns(actv, d1);
			
//			Methods_dlg.dlg_Admin_Ops(actv, d1);
			
		} else if (li.getText().equals(actv.getString(
				R.string.generic_tv_delete))) {//if (choice.equals(actv.getString(R.string.generic_tv_delete))))

			Methods_dlg.conf_Delete_Memo(actv, d1, memo);
			
		} else {
			
		}

	}//case_ACTV_SHOWLIST_LV

	private void 
	case_ACTV_MEMO_ADMIN_PATTERNS
	(ListItem li) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// dispatch

		////////////////////////////////
		if (li.getText().equals(actv.getString(
				R.string.generic_tv_register))) {

			Methods_dlg.register_Patterns(actv, d1);
			
//			Methods_dlg.dlg_Admin_Ops(actv, d1);
			
		} else if (li.getText().equals(actv.getString(
				R.string.generic_tv_edit))) {//if (choice.equals(actv.getString(R.string.generic_tv_delete))))
		
//			boolean res = _case_Admin_LV__Backup();
			
			return;
			
		} else if (li.getText().equals(actv.getString(
				R.string.generic_tv_delete))) {//if (choice.equals(actv.getString(R.string.generic_tv_delete))))

//			Methods_dlg.conf_Restore_DB(actv, d1);
			
			return;
			
		} else {
			
		}

		
	}//case_ACTV_MEMO_ADMIN_PATTERNS

	private void 
	case_GV_FILTER_SHOWLIST
	(WordPattern wp) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// get view

		////////////////////////////////
		EditText et = 
				(EditText) d1.findViewById(R.id.dlg_filter_showlist_et_content);
		
		////////////////////////////////

		// get pattern

		////////////////////////////////
		String pattern = wp.getWord();
		
		////////////////////////////////

		// add pattern

		////////////////////////////////
		String tmp = et.getText().toString();

		if (tmp == null) {
			
			String msg = "Input => null";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
			return;
			
		}
		
		if (tmp.length() > 0) {
			
			tmp = tmp + " " + pattern;
			
		} else {
			
			tmp = pattern;
			
		}
		
		et.setText(tmp);
		
		////////////////////////////////

		// set: selection

		////////////////////////////////
		et.setSelection(tmp.length());
		
	}//case_GV_FILTER_SHOWLIST

	private void 
	case_ACTV_MAIN_ADMIN_LV_OPS
	(ListItem li) {
		// TODO Auto-generated method stub

		if (li.getText().equals(actv.getString(
				R.string.dlg_actvmain_admin_item_upload_db))) {

			Methods_dlg.conf_Upload_DB(actv, d1, d2);
			
		} else if (li.getText().equals(actv.getString(
					R.string.dlg_actvmain_operations_import_db))) {
				
				Methods_dlg.conf_Import_DB(actv, d1, d2);
				
		} else if (li.getText().equals(actv.getString(
				R.string.dlg_actvmain_operations_import_patterns))) {//if (choice.equals(actv.getString(R.string.generic_tv_delete))))
		
			Methods_dlg.conf_Import_Patterns(actv, d1, d2);
		
			////////////////////////////////

			// tables

			////////////////////////////////
		} else if (li.getText().equals(actv.getString(
				R.string.dlg_actvmain_operations_create_table_patterns))) {//if (choice.equals(actv.getString(R.string.generic_tv_delete))))
			
			Methods_dlg.conf_CreateTable_Patterns(actv, d1, d2);
			
		} else if (li.getText().equals(actv.getString(
				R.string.dlg_actvmain_operations_drop_table_patterns))) {//if (choice.equals(actv.getString(R.string.generic_tv_delete))))
			
			Methods_dlg.conf_DropTable_Patterns(actv, d1, d2);
			
		} else if (li.getText().equals(actv.getString(
				R.string.dlg_actvmain_operations_create_table_memos))) {//if (choice.equals(actv.getString(R.string.generic_tv_delete))))
			
			Methods_dlg.conf_CreateTable_Memos(actv, d1, d2);
			
		} else if (li.getText().equals(actv.getString(
				R.string.dlg_actvmain_operations_drop_table_memos))) {//if (choice.equals(actv.getString(R.string.generic_tv_delete))))
			
			Methods_dlg.conf_DropTable_Memos(actv, d1, d2);
			
		}

		// Log
		String msg_Log = "item => " + li.getText();
		Log.d("DOI_CL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}

	private void case_Admin_LV(ListItem item) {
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
			
			Methods.start_SE_new(actv, CONS.Audio.Clip.dialog_Item);
			
		}
		
		////////////////////////////////

		// dispatch

		////////////////////////////////
		if (item.getText().equals(actv.getString(
				R.string.dlg_actvmain_admin_item_operations))) {


			
			Methods_dlg.dlg_Admin_Ops(actv, d1);
			
		} else if (item.getText().equals(actv.getString(
				R.string.dlg_actvmain_admin_item_backup_db))) {//if (choice.equals(actv.getString(R.string.generic_tv_delete))))
		
			boolean res = _case_Admin_LV__Backup();
			
			return;
			
		} else if (item.getText().equals(actv.getString(
				R.string.dlg_actvmain_admin_item_restore_db))) {//if (choice.equals(actv.getString(R.string.generic_tv_delete))))

			Methods_dlg.conf_Restore_DB(actv, d1);
			
			return;
			
		} else {
			
//			////////////////////////////////
//			
//			// bgm
//			
//			////////////////////////////////
//			Methods.start_SE(actv, CONS.Audio.Clip.dialog_Item_UnderConstruction);
//		Methods.start_SE(actv, R.raw.tap_x0_094);
			
		}

		
	}

	private boolean
	_case_Admin_LV__Backup() {
		// TODO Auto-generated method stub
	
		boolean res = Methods.backup_DB(actv);
		
		if (res == true) {
			
			String msg = "DB backup => done";
			Methods_dlg.dlg_ShowMessage(actv, msg);
			
			d1.dismiss();
			
		} else {
			
			String msg = "DB backup => failed";
			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.red);
			
		}
		
		return res;

	}//_case_Admin_LV__Backup

}//public class DialogOnItemClickListener implements OnItemClickListener
