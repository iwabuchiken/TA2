package ta2.tasks;

import java.util.Locale;
import java.util.TimerTask;

import ta2.utils.CONS;
import ta2.utils.Methods;
import android.os.Handler;
import android.util.Log;

public class Task_Timer__PlayerActv extends TimerTask {

	Handler handler;
	
	public Task_Timer__PlayerActv(Handler handler) {
		// TODO Auto-generated constructor stub
		
		this.handler	= handler;
		
	}

	@Override
	public void run() {
		
//		// Log
//		String msg_Log;
//		
//		msg_Log = String.format(
//				Locale.JAPAN,
//				"run() => starting..."
//				);
//		
//		Log.i("Task_Timer.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		if (CONS.PlayerActv.mp != null) {
			
			handler.post(new Runnable(){

//						@Override
				public void run() {
					/***************************************
					 * Update: Progress label
					 ***************************************/
//							PlayerActv.updateProgressLabel(actv);
//					new PlayerActv().updateProgressLabel();
					Methods.update_ProgressLable__PlayerActv();
//					Methods.update_ProgressLable();
					
					/***************************************
					 * Update: Seekbar
					 ***************************************/
					if (CONS.PlayerActv.mp != null
//									&& !CONS.PlayerActv.sb.isInTouchMode()) {
							&& !CONS.PlayerActv.sb.isPressed()) {
						
						// Log
						Log.d("Task_Timer.java"
								+ "["
								+ Thread.currentThread()
										.getStackTrace()[2]
										.getLineNumber()
								+ ":"
								+ Thread.currentThread()
										.getStackTrace()[2]
										.getMethodName() + "]",
								"CONS.PlayerActv.mp != null && " +
//										"!CONS.PlayerActv.sb.isInTouchMode()");
								"!CONS.PlayerActv.sb.isPressed()");
						
						int currentPosition = CONS.PlayerActv.mp.getCurrentPosition();
						
						long length = CONS.PlayerActv.len_Audio;
//						long length = Methods.conv_ClockLabel_to_MillSec(
//								CONS.PlayerActv.ai.getLength());
						
						int seekPositon = (int)
//											((currentPosition / length)
									(((float)currentPosition / length)
											* CONS.PlayerActv.sb.getMax());
//								
						CONS.PlayerActv.sb.setProgress(seekPositon);
						
					} else {//if (CONS.PlayerActv.mp == null)
						
						// Log
						Log.d("Task_Timer.java"
								+ "["
								+ Thread.currentThread()
										.getStackTrace()[2]
										.getLineNumber()
								+ ":"
								+ Thread.currentThread()
										.getStackTrace()[2]
										.getMethodName() + "]",
								"NO");
						
					}//if (CONS.PlayerActv.mp == null)
					
					
					
				}//public void run() // Runnable
				
			});//handler.post()
			
			
		}//if (CONS.P == condition)
		
//		counter += 1;
		
	}//public void run()
			

}
