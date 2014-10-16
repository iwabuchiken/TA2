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
	public boolean 
	onTouch
	(View v, MotionEvent event) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		Tags.ButtonTags tag = (Tags.ButtonTags) v.getTag();
		
//		vib.vibrate(CONS.Admin.vibLength_click);
		
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			
			ImageButton ib;
			
			switch (tag) {

			case ACTV_SHOWLOG_IB_BACK:
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.ifm8_thumb_back_50x50_disenabled);
				
				break;// case image_activity_next

			case ACTV_SHOWLOG_IB_TOP://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.ifm8_thumb_top_50x50_disenabled);
				
				break;// case image_activity_next
				
			case ACTV_SHOWLOG_IB_BOTTOM://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.ifm8_thumb_bottom_50x50_disenabled);
						
				break;// case thumb_activity_ib_bottom

			case ACTV_SHOWLOG_IB_DOWN://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.ifm8_thumb_down_50x50_disenabled);
				
				break;// case image_activity_next

			case ACTV_SHOWLOG_IB_UP://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.ifm8_thumb_up_50x50_disenabled);
				
				break;// case image_activity_next

			case ACTV_REC_BACK://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_memo_ib_back_49x37_disabled);
				
				break;// case ib_up
				
			case ACTV_REC_STOP://----------------------------------------------------
			case ACTV_PLAT_STOP://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_rec_stop);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_REC_REC://----------------------------------------------------
			case ACTV_PLAY_PLAY://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_rec_rec_recording);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_MAIN_MEMO://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_main_bt_memo_128x128_disabled);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_MEMO_BACK://----------------------------------------------------
			case ACTV_MEMO_EDIT_BACK://----------------------------------------------------
			case ACTV_PLAY_BACK://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_memo_ib_back_49x37_disabled);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_MEMO_CLEAR://----------------------------------------------------
			case ACTV_PLAY_CLEAR://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_memo_ib_clear_41x50_disabled);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_MEMO_SAVE://----------------------------------------------------
			case ACTV_MEMO_EDIT_SAVE://----------------------------------------------------
			case ACTV_PLAY_SAVE://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_memo_ib_save_64x64_disabled);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_MAIN_SHOWLIST://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_main_bt_list_128x128_disabled);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_SHOWLIST_BACK://----------------------------------------------------
			case ACTV_PHOTO_BACK://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_memo_ib_back_49x37_disabled);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_SHOWLIST_TOP://----------------------------------------------------
			case ACTV_PHOTO_TOP://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_showlist_bt_top_45x45_disabled);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_SHOWLIST_UP://----------------------------------------------------
			case ACTV_PHOTO_UP://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_showlist_bt_up_50x50_disabled);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_SHOWLIST_DOWN://----------------------------------------------------
			case ACTV_PHOTO_DOWN://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_showlist_bt_down_50x50_disabled);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_SHOWLIST_BOTTOM://----------------------------------------------------
			case ACTV_PHOTO_BOTTOM://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_showlist_bt_bottom_45x45_disabled);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_MAIN_VOICE://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_main_bt_voice_128x128_disabled);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_MAIN_PHOTO://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_main_bt_photo_touched);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_IMAGE_BACK://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.ifm8_image_actv_back_70x70_touched);
//				ib.setImageResource(R.drawable.ifm8_image_actv_back_70x70_disabled);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
				
			}//switch (tag)
			
			break;//case MotionEvent.ACTION_DOWN:
			
			
		case MotionEvent.ACTION_UP:
			switch (tag) {

			case ACTV_SHOWLOG_IB_UP://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.ifm8_thumb_up_50x50);
				
				break;// case image_activity_next

			case ACTV_SHOWLOG_IB_DOWN://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.ifm8_thumb_down_50x50);
				
				break;// case image_activity_next

			case ACTV_SHOWLOG_IB_BOTTOM://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_tn_bt_bottom_45x45);

				break;// case thumb_activity_ib_bottom

			case ACTV_SHOWLOG_IB_TOP://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_tn_bt_top_45x45);
				
				break;// case thumb_activity_ib_top

			case ACTV_SHOWLOG_IB_BACK://----------------------------------------------------
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.ifm8_thumb_back_50x50);
				
				break;// case image_activity_next

			case ACTV_REC_BACK://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_memo_ib_back_49x37);
				
				break;// case ib_up

			case ACTV_REC_STOP://----------------------------------------------------
			case ACTV_PLAT_STOP://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_rec_stop_not_in_use);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_REC_REC://----------------------------------------------------
			case ACTV_PLAY_PLAY://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_rec_rec);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_MAIN_MEMO://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_main_bt_memo);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_MEMO_BACK://----------------------------------------------------
			case ACTV_MEMO_EDIT_BACK://----------------------------------------------------
			case ACTV_PLAY_BACK://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_memo_ib_back_49x37);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
			
			case ACTV_MEMO_CLEAR://----------------------------------------------------
			case ACTV_PLAY_CLEAR://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_memo_ib_clear_41x50);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_MEMO_SAVE://----------------------------------------------------
			case ACTV_MEMO_EDIT_SAVE://----------------------------------------------------
			case ACTV_PLAY_SAVE://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_memo_ib_save_64x64);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_MAIN_SHOWLIST://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_main_bt_list);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
			
			case ACTV_SHOWLIST_BACK://----------------------------------------------------
			case ACTV_PHOTO_BACK://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_memo_ib_back_49x37);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
			
			case ACTV_SHOWLIST_TOP://----------------------------------------------------
			case ACTV_PHOTO_TOP://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_showlist_bt_top_45x45);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_SHOWLIST_UP://----------------------------------------------------
			case ACTV_PHOTO_UP://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_showlist_bt_up_50x50);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_SHOWLIST_DOWN://----------------------------------------------------
			case ACTV_PHOTO_DOWN://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_showlist_bt_down_50x50);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_SHOWLIST_BOTTOM://----------------------------------------------------
			case ACTV_PHOTO_BOTTOM://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_showlist_bt_bottom_45x45);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up

			case ACTV_MAIN_VOICE://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_main_bt_voice_128x128);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_MAIN_PHOTO://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.actv_main_bt_photo_128x128);
				
//				v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			case ACTV_IMAGE_BACK://----------------------------------------------------
				
				ib = (ImageButton) v;
				ib.setImageResource(R.drawable.ifm8_image_actv_back_70x70);
				//			ib.setImageResource(R.drawable.ifm8_image_actv_back_70x70_disabled);
				
				//			v.setBackgroundColor(Color.GRAY);
				
				break;// case ib_up
				
			}//switch (tag)
			
			break;//case MotionEvent.ACTION_UP:
			

			
		}//switch (event.getActionMasked())
		
		return false;
		
	}//onTouch

}//public class BO_TL implements OnTouchListener
