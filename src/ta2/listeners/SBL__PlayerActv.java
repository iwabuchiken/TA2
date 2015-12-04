package ta2.listeners;

import ta2.main.R;
import ta2.utils.CONS;
import ta2.utils.Methods;
import android.app.Activity;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SBL__PlayerActv implements OnSeekBarChangeListener {

	Activity actv;
	SeekBar sb;
	
	public SBL__PlayerActv(Activity actv) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
	}

	public SBL__PlayerActv(Activity actv, SeekBar sb) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
		this.sb = sb;
	}

	public void
	onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		// TODO Auto-generated method stub
		
		// Log
		String msg_Log = "progress = " + progress;
		Log.d("SBL__PlayerActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		float length = (float) CONS.PlayerActv.len_Audio;
//				(float) Methods.conv_ClockLabel_to_MillSec(
//							CONS.PlayerActv.ai.getLength());

		// Log
		msg_Log = "length => " + length;
		Log.d("SBL__PlayerActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		int seekedPosition = (int)
				((float)progress * 1.000f / sb.getMax() * length);
//		((float)progress * 1.000f / CONS.PlayerActv.sb.getMax() * length);
//		int seekedPosition = (int)
//				((float)progress * 1.000f / sb.getMax() 
//						* (float)PlayerActv.ai.getLength());
		
		// Log
		msg_Log = "(double)progress / sb.getMax() = " 
					+ String.format("%.5f", ((double)progress / sb.getMax()));
		
		Log.d("SBL__PlayerActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "(float)progress / sb.getMax() = " + ((float)progress / sb.getMax()));
				+ "]", msg_Log);
//		+ "]", "(double)progress / sb.getMax() = " + String.format("%.5f", ((double)progress / sb.getMax())));
		
		// Log
		msg_Log = "length = " + length;
		Log.d("SBL__PlayerActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		// Log
		Log.d("SBL__PlayerActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"seekedPosition=" + seekedPosition
				+ "("
				+ Methods.convert_intSec2Digits_lessThanHour(seekedPosition / 1000)
				+ ")");

		/***************************************
		 * Set: Current position to the view
		 ***************************************/
		if (CONS.PlayerActv.tv_CurrentPosition == null) {
			
			// Log
			msg_Log = "CONS.PlayerActv.tv_CurrentPosition => null";
			Log.d("SBL__PlayerActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);

			CONS.PlayerActv.tv_CurrentPosition = 
					(TextView) actv.findViewById(
							R.id.actv_play_tv_current_position);
			
		}//if (CONS.PlayerActv.tv_CurrentPosition == null)
		
//		CONS.PlayerActv.tv_CurrentPosition = (TextView) this.findViewById(R.id.actv_play_tv_current_position);
		
//		// Log
//		msg_Log = "seekedPosition = " + seekedPosition
//						+ " // "
//						+ "Methods.conv_MillSec_to_ClockLabel(seekedPosition) = "
//						+ Methods.conv_MillSec_to_ClockLabel(seekedPosition);
//		Log.d("SBL__PlayerActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
//		// Log
//		msg_Log = "CONS.PlayerActv.tv_CurrentPosition.getClass().getName() = "
//					+ CONS.PlayerActv.tv_CurrentPosition.getClass().getName();
//		Log.d("SBL__PlayerActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		//test
		// mill second value => round up
		int tmp = seekedPosition % 1000;
		
		if (tmp > 0) {
			
			seekedPosition = (seekedPosition / 1000) * 1000 + 1000;
			
			// Log
			msg_Log = "seekedPosition is now: " + seekedPosition;
			Log.d("SBL__PlayerActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} else {
			
			// Log
			msg_Log = "seekedPosition remains to be: " + seekedPosition;
			Log.d("SBL__PlayerActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);

		}
		
		CONS.PlayerActv.tv_CurrentPosition.setText(
				Methods.conv_MillSec_to_ClockLabel(seekedPosition));
//		Methods.convert_intSec2Digits_lessThanHour(seekedPosition / 1000));

		String current = (String) CONS.PlayerActv.tv_CurrentPosition.getText();
		
		// Log
		msg_Log = "current time = " + current;
		Log.d("SBL__PlayerActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		
	}//onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)

	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		// Log
		Log.d("SBL__PlayerActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "onStartTrackingTouch()");
	}

	public void onStopTrackingTouch(SeekBar seekBar) {
		
		/***************************************
		 * Set: Current position to preference
		 ***************************************/
		long seekedPosition =
				(long) ((float) seekBar.getProgress() * 1.000f / sb.getMax()
							* (float) CONS.PlayerActv.len_Audio);
//		* (float) Methods.conv_ClockLabel_to_MillSec(
//				CONS.PlayerActv.ai.getLength()));

		// Log
		String msg_Log = "onStopTrackingTouch: seekedPosition = " + seekedPosition;
		Log.d("SBL__PlayerActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		boolean res = 
				Methods.setPref_Long(
						actv,
						CONS.Pref.pname_PlayerActv,
						CONS.Pref.pkey_PlayerActv_CurrentPosition,
						seekedPosition);
		
		/******************************
			validate
		 ******************************/
		if (res == false) {
			
			// Log
			Log.d("SBL__PlayerActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Position => Not stored: " + seekedPosition);
			
		}
		
		// Log
		msg_Log = "Pref: current position => set";
		Log.d("SBL__PlayerActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		
	}//public void onStopTrackingTouch(SeekBar seekBar)

}//public class SeekBarChangeListener implements OnSeekBarChangeListener
