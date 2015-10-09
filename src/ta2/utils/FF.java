package ta2.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ta2.utils.CONS.Enums.FilterType;
import android.app.Activity;
import android.util.Log;

public class FF implements FilenameFilter {

	
	
	private Activity actv;
	private FilterType type;

	boolean filter_Result;
	
	private String last_update_Str = "2015/10/07 06:31:53.862";
	
	private String fname_Threshold;
	private String regex;
	private Pattern p;
	private Matcher m;

	private List<String> listOf_Uploaded_Audio_FileNames;
	private List<String> list_TMP_Str;

	private String msg_Log;
	
	public FF(Activity actv, FilterType type) {
		// TODO Auto-generated constructor stub
		
		this.actv	= actv;
		this.type	= type;
		
		switch (type) {
		
		case UPLOAD_AUDIO:
			
			case_UPLOAD_AUDIO__Setup();
////			this.last_update_Str = "2015/10/05 06:31:53.862";;
////			this.last_update_Str = DBUtils.find_UploadHistory_Audio_Latest(actv);
//
//			this.fname_Threshold = 
//					Methods.conv_TimeLabel_2_FileName(actv, last_update_Str);
//
//			this.regex = "^\\d\\d\\d\\d";
//			
//			this.p = Pattern.compile(regex);
			
			break;

		default:
			break;
		}
		
	}//public FF(Activity actv, FilterType type)

	private void 
	case_UPLOAD_AUDIO__Setup() {
		// TODO Auto-generated method stub
		
		///////////////////////////////////
		//
		// regex
		//
		///////////////////////////////////
//		this.last_update_Str = "2015/10/05 06:31:53.862";;
//		this.last_update_Str = DBUtils.find_UploadHistory_Audio_Latest(actv);
		this.last_update_Str = CONS.DB.last_update_Str;

		this.fname_Threshold = 
				Methods.conv_TimeLabel_2_FileName(actv, last_update_Str);

		this.regex = "^\\d\\d\\d\\d";
		
		this.p = Pattern.compile(regex);

		///////////////////////////////////
		//
		// history data
		//
		///////////////////////////////////
//		// list
//		this.listOf_Uploaded_Audio_FileNames = new ArrayList<String>();

		this.listOf_Uploaded_Audio_FileNames = 
						DBUtils.find_All_UploadHistory_Audio__FileNames(this.actv);
//		this.list_TMP_Str = DBUtils.find_All_UploadHistory_Audio__FileNames(this.actv);
//		this.listOf_Uploaded_Audio_Files = DBUtils.find_All_UploadHistory_Audio(this.actv);

		// validate
		if (this.listOf_Uploaded_Audio_FileNames == null) {
//			if (this.list_TMP_Str == null) {
			
			// initialize
			this.listOf_Uploaded_Audio_FileNames = new ArrayList<String>();
			
			// Log
			String msg_Log;
			
			msg_Log = String.format(
					Locale.JAPAN,
					"upload history list => null"
					);
			
			Log.e("FF.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return;
			
		} else if (this.listOf_Uploaded_Audio_FileNames.size() < 1) {
//		} else if (this.list_TMP_Str.size() < 1) {

			// Log
			String msg_Log;
			
			msg_Log = String.format(
					Locale.JAPAN,
					"no upload history for audio"
					);
			
			Log.e("FF.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return;

		}//if (this.list_TMP_Str.size() < 1)
		
//		String tmp_Str;
//		
//		for (String string : list_TMP_Str) {
//			
//			tmp_Str = Methods.conv_TimeLabel_2_FileName(actv, string);
//			
////			// Log
////			String msg_Log;
//			
//			this.msg_Log = String.format(
//					Locale.JAPAN,
//					"converted => %s", tmp_Str
//					);
//			
//			Log.i("FF.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//			this.listOf_Uploaded_Audio_FileNames.add(tmp_Str);
////			Methods.conv_TimeLabel_2_FileName(actv, string));
//			
//		}//for (String string : list_TMP_Str)
		
	}//case_UPLOAD_AUDIO__Setup
	

	@Override
	public boolean 
	accept(File dir, String filename) {
		// TODO Auto-generated method stub
		
		switch (type) {
		
		case UPLOAD_AUDIO:
			
			filter_Result = case_UPLOAD_AUDIO(filename);
			
			break;

		default:
			break;
			
		}//switch (type)
		
		return filter_Result;
		
	}//accept(File dir, String filename)

	private boolean 
	case_UPLOAD_AUDIO(String filename) {

		// Log
//		String msg_Log;
		
		msg_Log = String.format(
				Locale.JAPAN,
				"filtering => %s", filename
				);
		
		Log.i("FF.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		///////////////////////////////////
		//
		// threshold
		//
		///////////////////////////////////
//		String last_update_Str = CONS.DB.uploadAudio_Threshold;
//		String last_update_Str = "2015/08/08 06:31:53.862";
//		String last_update_Str = "2014/10/08 06:31:53.862";
//		String last_update_Str = DBUtils.find_UploadHistory_Audio_Latest(actv);
		
//		String fname_Threshold = 
//					Methods.conv_TimeLabel_2_FileName(actv, last_update_Str);
		

		///////////////////////////////////
		//
		// filter: non-date file name
		//
		///////////////////////////////////
		this.m = p.matcher(filename);
		
		if (!m.find()) {
			
			return false;
			
		}

		///////////////////////////////////
		//
		// filter: already uploaded
		//
		///////////////////////////////////
		if (this.listOf_Uploaded_Audio_FileNames.contains(filename)) {

			// Log
//			String msg_Log;
			
			msg_Log = String.format(
					Locale.JAPAN,
					"already uploaded: %s", filename
					);
			
			Log.i("FF.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return false;

		}//if (this.listOf_Uploaded_Audio_Files.contains(filename))
		
		///////////////////////////////////
		//
		// compare
		//
		///////////////////////////////////
		int tmp_Int = filename.compareToIgnoreCase(fname_Threshold);
		
		///////////////////////////////////
		//
		// return
		//
		///////////////////////////////////
		if (tmp_Int > 0) {
			
			return true;
			
		} else {//if (tmp_Int > 0)
			
			return false;
			
		}//if (tmp_Int > 0)
		
	}//case_UPLOAD_AUDIO

}
