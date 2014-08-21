package ta2.listeners.button;

import ta2.utils.Methods;
import ta2.utils.Tags;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class BO_CL implements OnClickListener {
	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;

	//
	Vibrator vib;
	
	//
	int position;
	
	public BO_CL(Activity actv, int position) {
		//
		this.actv = actv;
		this.position = position;
		
		//
//		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
		
		
	}//public ButtonOnClickListener(Activity actv, int position)

	public BO_CL(Activity actv) {
		
		this.actv = actv;
		
		//
//		CONS.Admin.vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);

	}

	//	@Override
	public void onClick(View v) {
//		//
		Tags.ButtonTags tag = (Tags.ButtonTags) v.getTag();
//
//		CONS.Admin.vib.vibrate(CONS.Admin.vibLength_click);
		
		//
		switch (tag) {

		case ACTV_MAIN_MEMO:
			
			case_MEMO();
			
			break;

		case ACTV_MAIN_BACK:
			
			case_ACTV_MAIN_BACK();
			
			break;
			
			
		default:
			break;
		}//switch (tag)
		
	}//public void onClick(View v)

	private void 
	case_ACTV_MAIN_BACK() {
		// TODO Auto-generated method stub
		
		actv.finish();
		
		actv.overridePendingTransition(0, 0);
		
	}

	private void 
	case_MEMO() {
		// TODO Auto-generated method stub
		
		Methods.start_Activity_PrefActv(actv);
		
	}

}//public class ButtonOnClickListener implements OnClickListener
