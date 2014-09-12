package ta2.listeners;

import java.util.ArrayList;
import java.util.List;

import ta2.items.WordPattern;
import ta2.main.R;
import ta2.utils.CONS;
import ta2.utils.Methods;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

// ListOnItemLongClickListener
public class LOI_CL implements OnItemClickListener {

	Activity actv;

	Dialog dlg1;
	Dialog dlg2; 
	
	Vibrator vib;
	
	public LOI_CL(Activity actv) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
		
		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}

	@Override
	public void onItemClick
	(AdapterView<?> parent, View v, int position, long id) {
		// TODO Auto-generated method stub
		Tags.ListTags tag = null;
		
		
		tag = (Tags.ListTags) parent.getTag();
		
		CONS.Admin.vib.vibrate(CONS.Admin.vibLength_click);
//		vib.vibrate(CONS.Admin.vibLength_click);
		
		// Log
		String msg_Log = "tag => " + tag.toString();
		Log.d("LOI_CL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		switch (tag) {
			
		case ACTV_MEMO_LV_1://----------------------------------------------------
		case ACTV_MEMO_LV_2://----------------------------------------------------
		case ACTV_MEMO_LV_3://----------------------------------------------------

			WordPattern item = (WordPattern) parent.getItemAtPosition(position);
			
			case_ACTV_MEMO_LV_1(item);
			
			break;// case actv_bm_lv
			
			
		default:
			break;
		
		}//switch (tag)		
		
	}

	private void 
	case_ACTV_MEMO_LV_1
	(WordPattern item) {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// view

		////////////////////////////////
		EditText et = (EditText) actv.findViewById(R.id.actv_memo_et);
		
		////////////////////////////////

		// build: text

		////////////////////////////////
		String tmp = et.getText().toString();
		
		tmp = tmp + item.getWord() + " ";
		
		////////////////////////////////

		// set

		////////////////////////////////
		et.setText(tmp);
		
		et.setSelection(tmp.length());

		////////////////////////////////

		// update: used

		////////////////////////////////
		Methods.update_Pattern_Used(actv, item.getDb_Id());
		
	}//case_ACTV_MEMO_LV_1

}//public class ListOnItemLongClickListener implements OnItemLongClickListener
