package ta2.listeners.dialog;

import ta2.main.R;
import ta2.tasks.Task_AudioTrack;
import ta2.utils.CONS;
import ta2.utils.Methods;
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

	//
	Vibrator vib;
	
	// Used in => Methods.dlg_addMemo(Activity actv, long file_id, String tableName)
	long db_Id;
	String tableName;
	private String item_Name;	// Methods_dlg.conf_DropTable
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
			
		case DLG_CONF_DROP_TABLE_PATTERNS_OK://------------------------------------------------
			
			case_DLG_CONF_DROP_TABLE_PATTERNS_OK();
			
			break;
			
		case DLG_CONF_RESTORE_DB_OK://------------------------------------------------
			
			case_DLG_CONF_RESTORE_DB_OK();
			
			break;
			
			
		default: // ----------------------------------------------------
			break;
		}//switch (tag_name)
	}//public void onClick(View v)

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
		
		Methods.import_Patterns(actv, d1, d2, d3);
		
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
