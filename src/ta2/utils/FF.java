package ta2.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ta2.utils.CONS.Enums.FilterType;
import android.app.Activity;

public class FF implements FilenameFilter {

	
	
	private Activity actv;
	private FilterType type;

	boolean filter_Result;
	private String last_update_Str;
	private String fname_Threshold;
	private String regex;
	private Pattern p;
	private Matcher m;
	
	public FF(Activity actv, FilterType type) {
		// TODO Auto-generated constructor stub
		
		this.actv	= actv;
		this.type	= type;
		
		switch (type) {
		
		case UPLOAD_AUDIO:
			
			this.last_update_Str = "2015/10/05 06:31:53.862";;
//			this.last_update_Str = DBUtils.find_UploadHistory_Audio_Latest(actv);

			this.fname_Threshold = 
					Methods.conv_TimeLabel_2_FileName(actv, last_update_Str);

			this.regex = "^\\d\\d\\d\\d";
			
			this.p = Pattern.compile(regex);
			
			break;

		default:
			break;
		}
		
	}//public FF(Activity actv, FilterType type)

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
