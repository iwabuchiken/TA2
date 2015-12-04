package ta2.tasks;

import java.util.Locale;
import java.util.TimerTask;

import ta2.utils.CONS;
import ta2.utils.Methods;
import android.os.Handler;
import android.util.Log;

public class Task_Timer extends TimerTask {

	Handler handler;
	
	public Task_Timer(Handler handler) {
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
		
		if (CONS.PlayActv.mp != null) {
			
			handler.post(new Runnable(){

//						@Override
				public void run() {
					/***************************************
					 * Update: Progress label
					 ***************************************/
//							PlayActv.updateProgressLabel(actv);
//					new PlayActv().updateProgressLabel();
					Methods.update_ProgressLable();
					
					/***************************************
					 * Update: Seekbar
					 ***************************************/
					if (CONS.PlayActv.mp != null
//									&& !CONS.PlayActv.sb.isInTouchMode()) {
							&& !CONS.PlayActv.sb.isPressed()) {
						
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
								"CONS.PlayActv.mp != null && " +
//										"!CONS.PlayActv.sb.isInTouchMode()");
								"!CONS.PlayActv.sb.isPressed()");
						
						int currentPosition;
//						int currentPosition = CONS.PlayActv.mp.getCurrentPosition();
						
						try {
							
							currentPosition = CONS.PlayActv.mp.getCurrentPosition();
							
						} catch (Exception e) {
							// TODO Auto-generated catch block

							// Log
							String msg_Log;
							
							msg_Log = String.format(
									Locale.JAPAN,
									"CONS.PlayActv.mp.getCurrentPosition() => Exception"
									);
							
							Log.i("Task_Timer.java" + "["
									+ Thread.currentThread().getStackTrace()[2].getLineNumber()
									+ "]", msg_Log);

							
							e.printStackTrace();
							
							return;
							
						}

						
						long length = CONS.PlayActv.len_Audio;
//						long length = Methods.conv_ClockLabel_to_MillSec(
//								CONS.PlayActv.ai.getLength());
						
						int seekPositon = (int)
//											((currentPosition / length)
									(((float)currentPosition / length)
											* CONS.PlayActv.sb.getMax());
//								
						CONS.PlayActv.sb.setProgress(seekPositon);
						
					} else {//if (CONS.PlayActv.mp == null)
						
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
						
					}//if (CONS.PlayActv.mp == null)
					
					
					
				}//public void run() // Runnable
				
			});//handler.post()
			
			
		}//if (CONS.P == condition)
		
//		counter += 1;
		
	}//public void run()
			

}
