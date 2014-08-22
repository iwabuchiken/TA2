package ta2.listeners.button;

import ta2.main.R;
import ta2.utils.Tags;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;

public class BO_TL implements OnTouchListener {

	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;

	//
	Vibrator vib;
	
	public BO_TL(Activity actv) {
		//
		this.actv = actv;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
	}

//	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		Tags.ButtonTags tag = (Tags.ButtonTags) v.getTag();
		
//		vib.vibrate(CONS.Admin.vibLength_click);
		
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			
			ImageButton ib;
			
			switch (tag) {
			
			case ACTV_MAIN_MEMO://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_main_bt_memo_128x128_disabled);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_MEMO_BACK://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_memo_ib_back_49x37_disabled);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
				
			}//switch (tag)
			
			break;//case MotionEvent.ACTION_DOWN:
			
			
		case MotionEvent.ACTION_UP:
			switch (tag) {
			case ACTV_MAIN_MEMO://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_main_bt_memo);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_MEMO_BACK://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_memo_ib_back_49x37);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			}//switch (tag)
			
			break;//case MotionEvent.ACTION_UP:
		}//switch (event.getActionMasked())
		return false;
	}

}
