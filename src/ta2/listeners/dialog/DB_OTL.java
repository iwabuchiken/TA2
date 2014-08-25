package ta2.listeners.dialog;

import ta2.main.R;
import ta2.utils.Tags;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;

public class DB_OTL implements OnTouchListener {

	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;
	Dialog dlg1;
	private Dialog dlg2;
	private Dialog dlg3;
	
	public DB_OTL(Activity actv, Dialog dlg) {
		//
		this.actv = actv;
		this.dlg1 = dlg;
	}
	
	public DB_OTL(Activity actv) {
		//
		this.actv = actv;
	}

	public 
	DB_OTL
	(Activity actv, Dialog dlg1, Dialog dlg2) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		this.dlg1 = dlg1;
		this.dlg2 = dlg2;

	}

	public DB_OTL(Activity actv, Dialog dlg1, Dialog dlg2, Dialog dlg3) {
		// TODO Auto-generated constructor stub
		
		this.actv = actv;
		
		this.dlg1 = dlg1;
		this.dlg2 = dlg2;
		this.dlg3 = dlg3;
		
	}

	//	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		Tags.DialogTags tag_name = (Tags.DialogTags) v.getTag();
		
		ImageButton ib;
		
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
				switch (tag_name) {
				
				case GENERIC_DISMISS:
				case GENERIC_DISMISS_THIRD_DIALOG:
				case GENERIC_DISMISS_SECOND_DIALOG:
					
				case DLG_CONF_IMPORT_DB_OK:
					
				case DLG_CONF_IMPORT_PATTERNS_OK:
					
				case DLG_CONF_CREATE_TABLE_PATTERNS_OK:
					
				case DLG_CONF_DROP_TABLE_PATTERNS_OK:
					
				case DLG_CONF_CREATE_TABLE_MEMOS_OK:
					
				case DLG_CONF_DROP_TABLE_MEMOS_OK:
					
				case DLG_CONF_RESTORE_DB_OK:
					
				case DLG_CONF_CLEAR_VIEW_OK:
					
				case DLG_REGISTER_PATTERNS_OK:
					
					
					v.setBackgroundColor(Color.GRAY);
					
					break;
					
				case DLG_FILTER_SHOWLIST_CLEAR:
					
					ib = (ImageButton) v;
					ib.setImageResource(R.drawable.general_ib_clear_yellow_64x64_disabled);
					
					break;
					
				case DLG_FILTER_SHOWLIST_OK:
					
					ib = (ImageButton) v;
					ib.setImageResource(R.drawable.general_ib_ok_green_48x48_disabled);
					
					break;
					
				case DLG_FILTER_SHOWLIST_RESET:
					
					ib = (ImageButton) v;
					ib.setImageResource(R.drawable.general_ib_cancel_red_64x64_disdabled);
					
					break;
				}//switch (tag_name)
		
			break;//case MotionEvent.ACTION_DOWN:
			
		case MotionEvent.ACTION_UP:
			switch (tag_name) {

			case GENERIC_DISMISS:
			case GENERIC_DISMISS_SECOND_DIALOG:
			case GENERIC_DISMISS_THIRD_DIALOG:

			case DLG_CONF_IMPORT_DB_OK:
				
			case DLG_CONF_IMPORT_PATTERNS_OK:
				
			case DLG_CONF_CREATE_TABLE_PATTERNS_OK:
				
			case DLG_CONF_DROP_TABLE_PATTERNS_OK:
				
			case DLG_CONF_CREATE_TABLE_MEMOS_OK:
				
			case DLG_CONF_DROP_TABLE_MEMOS_OK:
				
			case DLG_CONF_RESTORE_DB_OK:
				
			case DLG_CONF_CLEAR_VIEW_OK:
				
			case DLG_REGISTER_PATTERNS_OK:
				
					v.setBackgroundColor(Color.WHITE);
					
					break;
					
			case DLG_FILTER_SHOWLIST_CLEAR:
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.general_ib_clear_yellow_64x64);
				
				break;
				
			case DLG_FILTER_SHOWLIST_OK:
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.general_ib_ok_green_64x64);
				
				break;
				
			case DLG_FILTER_SHOWLIST_RESET:
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.general_ib_cancel_red_64x64);
				
				break;
				
			}//switch (tag_name)
		
			break;//case MotionEvent.ACTION_UP:
		
		}//switch (event.getActionMasked())
		
		return false;
		
	}//public boolean onTouch(View v, MotionEvent event)

}//public class DB_OTL implements OnTouchListener
