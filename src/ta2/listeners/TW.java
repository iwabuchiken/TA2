package ta2.listeners;

import java.util.Locale;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class TW implements TextWatcher {

	Activity actv;
	EditText et_Content;
	private TextView tv_Num;
	
	public TW(Activity actv, EditText et_Content) {
		// TODO Auto-generated constructor stub
		
		this.actv	= actv;
		this.et_Content		= et_Content;
		
	}

	public TW
	(Activity actv, EditText et_Content, TextView tv_Num) {
		// TODO Auto-generated constructor stub
		
		this.actv	= actv;
		this.et_Content	= et_Content;
		this.tv_Num		= tv_Num;
		
	}

	@Override
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence text, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

		// Log
		String msg_Log = String.format(
							Locale.JAPAN,
							"text changed => %s(%d,%d,%d)", 
							text, arg1, arg2, arg3);
		Log.d("TW.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// prep: length

		////////////////////////////////
		text = text.toString().replaceAll("( |　)", "");
		
		// Log
		msg_Log = "text stripped => " + text.length();
		Log.d("TW.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// set num

		////////////////////////////////
		tv_Num.setText(String.valueOf(text.length()));
		
	}

}
