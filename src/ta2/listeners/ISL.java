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
		msg_Log = String.format(
					Locale.JAPAN,
					"position => %d / value => %s", 
					position, selected_Value);
		
		Log.d("ISL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// change: summary

		////////////////////////////////
		this.pref_Dropdown.setSummary("Current = " + selected_Value);
		
		////////////////////////////////

		// update: pref

		////////////////////////////////
		Methods.set_Pref_Int(
					(Activity)con, 
					CONS.Pref.pname_MainActv, 
					CONS.Pref.pkey_PrefActv_MemoListSize_Dropdown_CurrentSelection, 
					position);
		
		
//		// Log
//		String msg_Log = "v.getClass().getName() => " + v.getClass().getName();
//		Log.d("ISL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//		
//		// Log
//		msg_Log = "parent.getClass().getName() => " + parent.getClass().getName();
//		Log.d("ISL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//		
//		msg_Log = "parent.getItemAtPosition(0) => " 
//					+ parent.getItemAtPosition(0).getClass().getName();
//		Log.d("ISL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//		
		msg_Log = String.format(
					Locale.JAPAN,
					"parent.getItemAtPosition(%d) => %s",
				
					position, parent.getItemAtPosition(position));
//					
//		Log.d("ISL.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		
		
	}//case_SP_MEMOLIST_SIZE

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
