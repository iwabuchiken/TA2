package ta2.listeners.dialog;


import ta2.items.ListItem;
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
			
			
		default:
			break;
		}//switch (tag)
		
	}//public void onItemClick(AdapterView<?> parent, View v, int position, long id)

	private void 
	case_ACTV_MAIN_ADMIN_LV_OPS
	(ListItem li) {
		// TODO Auto-generated method stub

		if (li.getText().equals(actv.getString(
				R.string.dlg_actvmain_operations_import_db))) {

			Methods_dlg.conf_Import_DB(actv, d1, d2);
			
		} else if (li.getText().equals(actv.getString(
				R.string.dlg_actvmain_operations_import_patterns))) {//if (choice.equals(actv.getString(R.string.generic_tv_delete))))
		
			Methods_dlg.conf_Import_Patterns(actv, d1, d2);
		
		} else if (li.getText().equals(actv.getString(
				R.string.dlg_actvmain_operations_create_table_memos))) {//if (choice.equals(actv.getString(R.string.generic_tv_delete))))
			
			Methods_dlg.conf_CreateTable_Patterns(actv, d1, d2);
			
		} else if (li.getText().equals(actv.getString(
				R.string.dlg_actvmain_operations_drop_table_memos))) {//if (choice.equals(actv.getString(R.string.generic_tv_delete))))
			
			Methods_dlg.conf_DropTable_Patterns(actv, d1, d2);
			
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
		Methods.start_SE(actv, CONS.Audio.Clip.dialog_Item);
		
		////////////////////////////////

		// dispatch

		////////////////////////////////
		if (item.getText().equals(actv.getString(
				R.string.dlg_actvmain_admin_item_operations))) {


			
			Methods_dlg.dlg_Admin_Ops(actv, d1);
			
//		} else if (choice.equals(actv.getString(
//					R.string.dlg_upload_image_item_email))) {//if (choice.equals(actv.getString(R.string.generic_tv_delete))))
//		
//			String msg = actv.getString(
//					R.string.dlg_upload_image_item_email)
//					+ " => "
//					+ "Sorry. Under construction";
//			
//			Methods_dlg.dlg_ShowMessage(actv, msg, R.color.gold2);
		
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

}//public class DialogOnItemClickListener implements OnItemClickListener
