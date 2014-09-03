
package ta2.listeners;

import java.util.Locale;

import ta2.items.Pref_Dropdown;
import ta2.utils.CONS;
import ta2.utils.Methods;
import ta2.utils.Tags;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CheckedTextView;
import android.widget.Spinner;

//ItemSelectedListener
public class ISL implements OnItemSelectedListener {

	Activity actv;
	
	Context con;	// Spinner: Memo list size, Pref_Dropdown
	Pref_Dropdown pref_Dropdown;
	
	public ISL(Activity actv) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
	}

	public 
	ISL
	(Context con, Pref_Dropdown pref_Dropdown) {
		// TODO Auto-generated constructor stub
		
		this.con	= con;
		this.pref_Dropdown	= pref_Dropdown;
		
	}

	public void
	onItemSelected
	(AdapterView<?> parent, View v, int position, long id) {
		
		Tags.SpinnerTag tag = (Tags.SpinnerTag) parent.getTag();
		
		switch(tag) {
		
		case SP_MEMOLIST_SIZE://----------------------------------------

			case_SP_MEMOLIST_SIZE(parent, v, position);
//			// TODO Auto-generated method stub
//			storeName = (String) parent.getItemAtPosition(position);
//
//			spGenre = (Spinner) actv.findViewById(R.id.itemlist_tab1_sp_genre);
//			
//			genreName = (String) spGenre.getSelectedItem();
//
//			/***************************************
//			 * Filter
//			 ***************************************/
//			Methods.filterList3(actv, storeName, genreName);

			break;// case spStrore

		default://----------------------------------------
			break;
		
		}//switch(tag)
		
	}//onItemSelected()

	private void 
	case_SP_MEMOLIST_SIZE
	(AdapterView<?> parent, View v, int position) {
		// TODO Auto-generated method stub
		
		String msg_Log;
		
		////////////////////////////////

		// get: selection

		////////////////////////////////
		String selected_Value = (String) parent.getItemAtPosition(position);
		
		// Log
		msg_Log = "selected_Value => " + selected_Value;
		Log.d("ISL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
//		////////////////////////////////
//
//		// get: pref value
//
//		////////////////////////////////
//		int selection = Methods.get_Pref_Int(
//				(Activity)con, 
//				CONS.Pref.pname_MainActv, 
//				CONS.Pref.pkey_PrefActv_MemoListSize_Dropdown_CurrentSelection, 
//				CONS.Pref.dflt_IntExtra_value);
		
		////////////////////////////////

		// change: summary

		////////////////////////////////
//		String summary_Text = null;
		int pref_Value_New = CONS.Pref.dflt_IntExtra_value;
		
		if (selected_Value.equals(CONS.PrefActv.sp_NoSelection_label)
				) {
//			|| selection == CONS.PrefActv.sp_NoSelection_value) {
			
			selected_Value = "";
			
			pref_Value_New = position;
//			pref_Value_New = CONS.PrefActv.sp_NoSelection_value;
			
		} else {

			pref_Value_New = position;
			
		}
		
		this.pref_Dropdown.setSummary("Current = " + selected_Value);
		
		////////////////////////////////

		// update: pref

		////////////////////////////////
		Methods.set_Pref_Int(
					(Activity)con, 
					CONS.Pref.pname_MainActv, 
					CONS.Pref.pkey_PrefActv_MemoListSize_Dropdown_CurrentSelection, 
					pref_Value_New);
//		position);
		
		
	}//case_SP_MEMOLIST_SIZE

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
