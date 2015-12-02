package ta2.comps;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import ta2.items.AudioMemo;
import ta2.utils.CONS.Enums.SortOrder;
import ta2.utils.CONS.Enums.SortType;
import ta2.utils.Methods;
import android.util.Log;

public class Comp_AudioMemos implements Comparator<AudioMemo> {

	List<AudioMemo> list_SearchHistories;
	SortType sortType;
	SortOrder sortOrder;
	
	String msg_Log;
	
	public Comp_AudioMemos
	(List<AudioMemo>
		list_SearchHistories, SortType sortType,
		SortOrder sortOrder) {
		
		this.list_SearchHistories	= list_SearchHistories;
		
		this.sortType	= sortType;
		
		this.sortOrder	= sortOrder;
		
		// Log
		String msg_Log = "Comp_AudioMemo => created";
		Log.d("Comp_AudioMemo.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}

	@Override
	public int compare(AudioMemo a1, AudioMemo a2) {
		// TODO Auto-generated method stub
		int res;
		
		switch(sortType) {
		
//		case FileName:
//			
//			res = _compare_FileName(a1, a2);
//			
//			break;
			
		case CREATED_AT:
			
			res = this._compare_CREATED_AT__tmp(a1, a2);
//			res = _compare_CREATED_AT(a1, a2);
			
			break;
			
		case TEXT:
			
			res = this._compare_TEXT__tmp(a1, a2);
//			res = _compare_CREATED_AT(a1, a2);
			
			break;
			
		default:
			
			res = 1;
		
		}
		
		return res;
		
	}//public int compare(AI a1, AI a2)

	private int 
	_compare_CREATED_AT__tmp
	(AudioMemo t1, AudioMemo t2) {
		// TODO Auto-generated method stub
		
		int res;
		
		String time1 = t1.getCreated_at();
		String time2 = t2.getCreated_at();

		switch (sortOrder) {
		
		case ASC:
			
			res = (time1.compareToIgnoreCase(time2) < 0) ? 1 : 0;
//			res = (time1 < time2) ? 1 : 0;

			break;
			
		case DESC:

//			// Log
////			String msg_Log;
//			
////			msg_Log = String.format(
////					Locale.JAPAN,
////					"comparing... => t1 = %s, t2 = %s", t1.getCreated_at(), t2.getCreated_at()
////					);
////			
////			Log.i("Comp_AudioMemo.java" + "["
////					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
////					+ "]", msg_Log);
//			
//			if (time1.compareToIgnoreCase(time2) > 0) {
////				if (time1 > time2) {
//
//				msg_Log = String.format(
//							Locale.JAPAN,
//							"comparing... => t1 = %s, t2 = %s [time1.compareToIgnoreCase(time2) > 0]", 
//							t1.getCreated_at(), t2.getCreated_at()
//				);
//		
//				Log.i("Comp_AudioMemo.java" + "["
//						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//						+ "]", msg_Log);
//
//				res = 1;
//				
//			} else if (time1.compareToIgnoreCase(time2) == 0) {
////			} else if (time1 == time2) {
//
//				msg_Log = String.format(
//						Locale.JAPAN,
//						"comparing... => t1 = %s, t2 = %s [time1.compareToIgnoreCase(time2) == 0]", 
//						t1.getCreated_at(), t2.getCreated_at()
//				);
//		
//				Log.i("Comp_AudioMemo.java" + "["
//						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//						+ "]", msg_Log);
//
//				res = 0;
//				
//			} else {
//
//				msg_Log = String.format(
//						Locale.JAPAN,
//						"comparing... => t1 = %s, t2 = %s [else]", 
//						t1.getCreated_at(), t2.getCreated_at()
//				);
//		
//				Log.i("Comp_AudioMemo.java" + "["
//						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//						+ "]", msg_Log);
//
//				res = -1;
//			}
			
			try {
				
				res = t1.getCreated_at().compareTo(t2.getCreated_at());
//				res = t1.getFile_name().compareTo(t2.getFile_name());
				
				res = -1 * res;
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				
				// Log
				String msg_Log;
				
				msg_Log = String.format(
						Locale.JAPAN,
						"Exception => %s", e1.getMessage()
						);
				
				Log.e("Comp_AudioMemos.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", msg_Log);
				
				e1.printStackTrace();
				
				res = 1;
				
			}

			
			break;
			
		default:
			
			res = 1;
			
			break;
		}
		
		return res;
		
	}//private int _compare_FileName(AI a1, AI a2)

	private int 
	_compare_TEXT__tmp
	(AudioMemo t1, AudioMemo t2) {
		// TODO Auto-generated method stub
		int res;
		
		String text_1 = t1.getText();
		String text_2 = t2.getText();
//		String time1 = t1.getCreated_at();
//		String time2 = t2.getCreated_at();
		
		switch (sortOrder) {
		
		case ASC:
			
			res = (text_1.compareToIgnoreCase(text_2) < 0) ? 1 : 0;
//			res = (time1 < time2) ? 1 : 0;
			
			break;
			
		case DESC:
			
			try {
				
				res = t1.getText().compareTo(t2.getText());
//				res = t1.getCreated_at().compareTo(t2.getCreated_at());
//				res = t1.getFile_name().compareTo(t2.getFile_name());
				
				res = -1 * res;
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				
				// Log
				String msg_Log;
				
				msg_Log = String.format(
						Locale.JAPAN,
						"Exception => %s", e1.getMessage()
						);
				
				Log.e("Comp_AudioMemos.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", msg_Log);
				
				e1.printStackTrace();
				
				res = 1;
				
			}
			
			
			break;
			
		default:
			
			res = 1;
			
			break;
		}
		
		return res;
		
	}//_compare_TEXT__tmp
	
	private int _compare_CREATED_AT(AudioMemo t1, AudioMemo t2) {
		// TODO Auto-generated method stub
		
		int res;
		
		long time1 = Methods.conv_TimeLabel_to_MillSec(t1.getCreated_at());
		long time2 = Methods.conv_TimeLabel_to_MillSec(t2.getCreated_at());
		
		switch (sortOrder) {
		
		case ASC:
			
			res = (time1 < time2) ? 1 : 0;
			
			break;
			
		case DESC:
			
			// Log
//			String msg_Log;
			
//			msg_Log = String.format(
//					Locale.JAPAN,
//					"comparing... => t1 = %s, t2 = %s", t1.getCreated_at(), t2.getCreated_at()
//					);
//			
//			Log.i("Comp_AudioMemo.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
			
			if (time1 > time2) {
				
				res = 1;
				
			} else if (time1 == time2) {
				
				res = 0;
				
			} else {
				
				res = -1;
			}
			
			break;
			
		default:
			
			res = 1;
			
			break;
		}
		
		return res;
		
	}//private int _compare_FileName(AI a1, AI a2)
	
}//public class AIComparator implements Comparator<AI>
