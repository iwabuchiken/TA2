package ta2.listeners.dialog;


import ta2.items.ListItem;
import ta2.utils.CONS;
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
	Dialog dlg1;
	Dialog dlg2;
	
	//
	Vibrator vib;
	private String file_Name;
	
	
	//
//	Methods.DialogTags dlgTag = null;

	public DOI_CL(Activity actv, Dialog dlg) {
		// 
		this.actv = actv;
		this.dlg1 = dlg;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)

	public DOI_CL(Activity actv, Dialog dlg, Dialog dlg2) {
		// 
		this.actv = actv;
		this.dlg1 = dlg;
		this.dlg2 = dlg2;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)


	public DOI_CL
	(Activity actv, Dialog dlg1, String file_Name) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		this.dlg1 = dlg1;
		
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
		
		ListItem listItem;
		
		/*----------------------------
		 * 3. Switching
			----------------------------*/
		switch (tag) {
		
//		case dlg_db_admin_lv://----------------------------------------------
		case Admin_LV://----------------------------------------------
			
			listItem = (ListItem) parent.getItemAtPosition(position);
			
			case_Admin_LV(listItem);
			
			break;// case dlg_add_memos_gv

			
		default:
			break;
		}//switch (tag)
		
	}//public void onItemClick(AdapterView<?> parent, View v, int position, long id)

	private void case_Admin_LV(ListItem item) {
		// TODO Auto-generated method stub
		
		// Log
		String msg_Log = "list item => " + item.getText();
		Log.d("DOI_CL.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}

	private void case_Admin_LV(String item) {
		// TODO Auto-generated method stub
		
	}

}//public class DialogOnItemClickListener implements OnItemClickListener
