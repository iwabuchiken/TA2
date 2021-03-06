package ta2.listeners;


import java.util.ArrayList;
import java.util.List;

import ta2.items.AudioMemo;
import ta2.items.Memo;
import ta2.items.WordPattern;
import ta2.utils.CONS;
import ta2.utils.Methods;
import ta2.utils.Methods_dlg;
import ta2.utils.Tags;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;

// ListOnItemLongClickListener
public class
LOI_LCL implements OnItemLongClickListener {

	Activity actv;

	Dialog dlg1;
	Dialog dlg2; 
	
	Vibrator vib;
	
	public LOI_LCL(Activity actv) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
		
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}

	public 
	LOI_LCL
	(Activity actv, Dialog dlg1, Dialog dlg2) {
		// TODO Auto-generated constructor stub
		this.actv	= actv;
		this.dlg1	= dlg1;
		this.dlg2	= dlg2;
		
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

	}

	public boolean
	onItemLongClick
	(AdapterView<?> parent, View v, int position, long id) {
		
		Tags.ListTags tag = null;
		
			
		tag = (Tags.ListTags) parent.getTag();
		
		CONS.Admin.vib.vibrate(CONS.Admin.vibLength_click);
//		vib.vibrate(CONS.Admin.vibLength_click);
		
		Memo item;
		
		AudioMemo audio_item;
		
		WordPattern wp;
		
		switch (tag) {
			
		case ACTV_IMPORTACTV_LV://----------------------------------------------------
			
			audio_item = (AudioMemo) parent.getItemAtPosition(position);
			
			case_ACTV_IMPORTACTV_LV(audio_item, position);
			
			break;// case actv_bm_lv
			
		case ACTV_SHOWLIST_LV://----------------------------------------------------

			item = (Memo) parent.getItemAtPosition(position);
			
			case_ACTV_SHOWLIST_LV(item, position);
			
			break;// case actv_bm_lv
			
		case ACTV_MEMO_LV_3://----------------------------------------------------
			
			wp = (WordPattern) parent.getItemAtPosition(position);
			
			case_ACTV_MEMO_LV_3(wp);
			
			break;// case actv_bm_lv
			
		case ACTV_MEMO_LV_2://----------------------------------------------------
			
			wp = (WordPattern) parent.getItemAtPosition(position);
			
			case_ACTV_MEMO_LV_3(wp);
			
			break;// case actv_bm_lv
			
		case ACTV_MEMO_LV_1://----------------------------------------------------
			
			wp = (WordPattern) parent.getItemAtPosition(position);
			
			case_ACTV_MEMO_LV_3(wp);
			
			break;// case actv_bm_lv
			
		default:
			break;
		
		}//switch (tag)
		
		return true;
		
	}//onItemLongClick (AdapterView<?> parent, View v, int position, long id)

	private void 
	case_ACTV_MEMO_LV_3
	(WordPattern wp) {
		// TODO Auto-generated method stub
		
		Methods_dlg.dlg_Admin_Patterns_GV(actv, wp);
		
	}

	private void 
	case_ACTV_SHOWLIST_LV
	(Memo memo, int inList_Pos) {
		// TODO Auto-generated method stub
		////////////////////////////////

		// pref: position

		////////////////////////////////
		Methods.set_Pref_Int(
				actv,
				CONS.Pref.pname_ShowListActv,
				CONS.Pref.pkey_ShowListActv_Current_Position,
//				CONS.Pref.pkey_CurrentPosition,
				inList_Pos);
		
		// Log
//		String msg_log = "Pref: " + CONS.Pref.pkey_CurrentPosition
		String msg_log = "Pref: " + CONS.Pref.pkey_CurrentPosition_MainActv
						+ " => "
						+ "Set to: " + inList_Pos;
		
		Log.d("LOI_LCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_log);
		
		CONS.ShowListActv.adp_List_Memos.notifyDataSetChanged();

		
		////////////////////////////////

		// dialog

		////////////////////////////////
		Methods_dlg.dlg_ShowListActv_LongClick(actv, memo);
		
		
		
	}//case_ACTV_SHOWLIST_LV

	private void 
	case_ACTV_IMPORTACTV_LV
	(AudioMemo audio_item, int inList_Pos) {
		// TODO Auto-generated method stub
		////////////////////////////////
		
		// pref: position
		
		////////////////////////////////
		Methods.set_Pref_Int(
				actv,
				CONS.Pref.pname_ImportActv,
				CONS.Pref.pkey_ImportActv_Current_Position,
//				CONS.Pref.pname_ShowListActv,
//				CONS.Pref.pkey_ShowListActv_Current_Position,
//				CONS.Pref.pkey_CurrentPosition,
				inList_Pos);
		
		// Log
//		String msg_log = "Pref: " + CONS.Pref.pkey_CurrentPosition
		String msg_log = "Pref: " + CONS.Pref.pkey_CurrentPosition_MainActv
				+ " => "
				+ "Set to: " + inList_Pos;
		
		Log.d("LOI_LCL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_log);
		
		CONS.ImportActv.adp_List_AudioMemos.notifyDataSetChanged();
//		CONS.ShowListActv.adp_List_Memos.notifyDataSetChanged();
		
		
		////////////////////////////////
		
		// dialog
		
		////////////////////////////////
		Methods_dlg.dlg_ImportActv_LongClick(actv, audio_item);
//		Methods_dlg.dlg_ShowListActv_LongClick(actv, memo);
		
		
		
	}//case_ACTV_IMPORTACTV_LV
	
}//public class ListOnItemLongClickListener implements OnItemLongClickListener
