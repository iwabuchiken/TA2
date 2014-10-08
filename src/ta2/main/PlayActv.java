package ta2.main;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import ta2.adapters.Adp_WordPatterns;
import ta2.comps.Comp_WP;
import ta2.items.Memo;
import ta2.listeners.LOI_CL;
import ta2.listeners.LOI_LCL;
import ta2.listeners.SBL;
import ta2.listeners.button.BO_CL;
import ta2.listeners.button.BO_TL;
import ta2.utils.CONS;
import ta2.utils.DBUtils;
import ta2.utils.Methods;
import ta2.utils.Methods_dlg;
import ta2.utils.Tags;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActv extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		/********************************
		 * 
		 ********************************/

		// Log
		String msg_Log = "onCreate";
		Log.d("PlayActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		super.onCreate(savedInstanceState);

		setContentView(R.layout.actv_play);

		this.setTitle(this.getClass().getName());

		////////////////////////////////

		// Init: vars

		////////////////////////////////
		if (CONS.Admin.vib == null) {
			
			CONS.Admin.vib = 
					(Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
			
		}
		
		////////////////////////////////

		// Get: intent values

		////////////////////////////////
		boolean res = _onCreate_Get_IntentValues();
		
		/******************************
			validate
		 ******************************/
		if (res == false) {
			
			// Log
			msg_Log = "Intent values => cant obtain";
			Log.e("PlayActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			// debug
			Toast.makeText(this, msg_Log, Toast.LENGTH_SHORT).show();
			
			return;
			
		}

		////////////////////////////////

		// Init: vars

		////////////////////////////////
		_onCreate_InitVars();

		/******************************
			validate
		 ******************************/
		if (res == false) {
			
			// Log
			msg_Log = "Intent values => cant obtain";
			Log.e("PlayActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			// debug
			Toast.makeText(this, msg_Log, Toast.LENGTH_SHORT).show();
			
			return;
			
		}

		////////////////////////////////

		// Prefs

		////////////////////////////////
		_onCreate_ManagePrefs();
		
	}//public void onCreate(Bundle savedInstanceState)

	private void _onCreate_ManagePrefs() {
		// TODO Auto-generated method stub
		
	}//private void _onCreate_ManagePrefs()

	private void _onCreate_InitVars() {
		// TODO Auto-generated method stub
		
		
	}

	private boolean _onCreate_Get_IntentValues() {
		
//		// Log
//		String msg_Log = "_onCreate_Get_IntentValues";
//		Log.d("PlayActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		// TODO Auto-generated method stub
		Intent i = this.getIntent();
		

		return true;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater mi = getMenuInflater();
//		mi.inflate(R.menu.menu_actv_play, menu);
//		mi.inflate(R.menu.menu_actv_play, menu);

		return super.onCreateOptionsMenu(menu);
		
	}//public boolean onCreateOptionsMenu(Menu menu)

	@Override
	protected void onDestroy() {
		/*********************************
		 * memo
		 *********************************/
		// Log
		Log.d("PlayActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "onDestroy()");
		
//		Methods.stop_player(this);
		
		/***************************************
		 * Clear prefs
		 ***************************************/
//		boolean res = Methods.clearPref(this, CONS.Pref.pname_PlayActv);
		
		super.onDestroy();
		
		////////////////////////////////

		// release mp

		////////////////////////////////
		if (CONS.PlayActv.mp != null) {
			
			CONS.PlayActv.mp.release();
			
			// Log
			String msg_Log = "player => released";
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
		////////////////////////////////

		// pref => clear

		////////////////////////////////
		boolean res = Methods.setPref_Long(
				this,
				CONS.Pref.pname_PlayActv,
				CONS.Pref.pkey_PlayActv_CurrentPosition,
				CONS.Pref.dflt_LongExtra_value);
		
		// Log
		String msg_Log = "pkey_PlayActv_CurrentPosition => cleared";
		Log.d("PlayActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		/***************************************
		 * Finish activity
		 ***************************************/
		finish();
		
	}//protected void onDestroy()

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
			
//		case R.id.menu_actv_play_admin_pattern://------------------------------------
//			
//			Methods_dlg.dlg_Admin_Patterns_PlayActv_Option(this);
//			
////			Methods_dlg.dlg_patterns(this);
//			
//			break;// case R.id.menu_actv_play_create_folder
			
			
		}//switch (item.getItemId())

		
		return super.onOptionsItemSelected(item);
	}//public boolean onOptionsItemSelected(MenuItem item)

	@Override
	protected void onPause() {
		// TODO ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ黷ｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ\?�ｿｽ�ｿｽb?�ｿｽ�ｿｽh?�ｿｽ�ｿｽE?�ｿｽ�ｿｽX?�ｿｽ�ｿｽ^?�ｿｽ�ｿｽu
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ黷ｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ\?�ｿｽ�ｿｽb?�ｿｽ�ｿｽh?�ｿｽ�ｿｽE?�ｿｽ�ｿｽX?�ｿｽ�ｿｽ^?�ｿｽ�ｿｽu
		super.onResume();
	}

	@Override
	protected void onStart() {
		/*********************************
		 * memo
		 *********************************/
		super.onStart();
		
		boolean res;
		
		////////////////////////////////

		// get: memo

		////////////////////////////////
		res = _Setup_Get_Memo();
		
		if (res == false) {
			
			return;
			
		}
		
		////////////////////////////////

		// audio length

		////////////////////////////////
		_Setup_AudioLen();
		
		////////////////////////////////
		
		// views
		
		////////////////////////////////
		_Setup_Views();

		////////////////////////////////

		// listeners

		////////////////////////////////
		_Setup_Listeners();
		
//		this._Setup_Listeners_Memo();
		
//		////////////////////////////////
//
//		// listviews
//
//		////////////////////////////////
//		this._Setup_Lists();
//		
//		this._Setup_Listeners_LVs();
		
		
	}//protected void onStart()

	private void 
	_Setup_Views() {
		// TODO Auto-generated method stub

		////////////////////////////////

		// memo

		////////////////////////////////
		TextView tv_Memo = (TextView) findViewById(R.id.actv_play_tv);
		
		tv_Memo.setText(CONS.PlayActv.memo.getText());
		
//		et_Memo.setSelection(CONS.PlayActv.memo.getText().length());
		
//		////////////////////////////////
//		
//		// num
//		
//		////////////////////////////////
//		TextView tv_Num = (TextView) findViewById(R.id.actv_play_tv_num);
//		
//		tv_Num.setText(
//				String.valueOf(CONS.PlayActv.memo.getText().length())
//				);
//		
		////////////////////////////////

		// length

		////////////////////////////////
		TextView tv_Length = (TextView) findViewById(R.id.actv_play_tv_length);
		
		if (CONS.PlayActv.len_Audio >= 0) {
			
			tv_Length.setText(Methods.conv_MillSec_to_ClockLabel(CONS.PlayActv.len_Audio));
			
		} else {
			
			tv_Length.setText("xx:xx");
			
		}
		
		////////////////////////////////

		// Current position

		////////////////////////////////
		CONS.PlayActv.tvCurrentPosition = 
				(TextView) findViewById(
						R.id.actv_play_tv_current_position);

		////////////////////////////////

		// IB: stop => disable

		////////////////////////////////
		ImageButton ib_Stop = (ImageButton) findViewById(R.id.actv_play_ib_stop);
		
		ib_Stop.setEnabled(false);
		
	}//_Setup_Views

	private void 
	_Setup_AudioLen() {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// get file name

		////////////////////////////////
		String text = CONS.PlayActv.memo.getText();
		
		Pattern p = Pattern.compile(CONS.RecActv.fmt_FileName_PlayMemo);
		Matcher m = p.matcher(text);

		String file_full_path = null;
		
		if (m.find()) {
			
			file_full_path = StringUtils.join(
					new String[]{
							CONS.DB.dPath_Audio,
							m.group(1)
					},
					File.separator);
			
			// Log
			String msg_Log = "file_full_path => " + file_full_path;
			Log.d("PlayActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} else {
			
			// Log
			String msg_Log = "no match";
			Log.e("PlayActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return;
			
		}
		
		////////////////////////////////

		// set: length

		////////////////////////////////
		long tmp = Methods.get_AudioLength(file_full_path);
		
		if (tmp > 0) {
			
			CONS.PlayActv.len_Audio = Methods.get_AudioLength(file_full_path);
			
		} else {
			
			String msg = null;
			
			switch((int)tmp) {
//			-1 file => exist not
//			-2 IllegalArgumentException
//			-3 IllegalStateException
//			-4 IOException
			case -1:
				msg = "file => exist not"; break;
				
			case -2:
				msg = "IllegalArgumentException"; break;
				
			case -3:
				msg = "IllegalStateException"; break;
				
			case -4:
				msg = "IOException"; break;
				
			}//switch((int)tmp)
			
			// Log
			Log.d("PlayActv.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + "]", msg);
			
			return;
			
		}//if (tmp > 0)
		
		// Log
		String msg_Log = "audio len => " + CONS.PlayActv.len_Audio;
		Log.d("PlayActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}//_Setup_AudioLen

	private void 
	_Setup_Listeners() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// IB: play

		////////////////////////////////
		ImageButton ib_Play = (ImageButton) this.findViewById(R.id.actv_play_ib_play);
		
		ib_Play.setTag(Tags.ButtonTags.ACTV_PLAY_PLAY);
		
		ib_Play.setOnTouchListener(new BO_TL(this));
		
		ib_Play.setOnClickListener(new BO_CL(this));

		////////////////////////////////
		
		// IB: stop
		
		////////////////////////////////
		ImageButton ib_Stop = (ImageButton) this.findViewById(R.id.actv_play_ib_stop);
		
		ib_Stop.setTag(Tags.ButtonTags.ACTV_PLAT_STOP);
		
		ib_Stop.setOnTouchListener(new BO_TL(this));
		
		ib_Stop.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////
		
		// IB: stop
		
		////////////////////////////////
		ImageButton ib_Back = (ImageButton) this.findViewById(R.id.actv_play_ib_back);
		
		ib_Back.setTag(Tags.ButtonTags.ACTV_PLAY_BACK);
		
		ib_Back.setOnTouchListener(new BO_TL(this));
		
		ib_Back.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////

		// seekbar

		////////////////////////////////
		CONS.PlayActv.sb = (SeekBar) findViewById(R.id.actv_play_sb);
		
		CONS.PlayActv.sb.setOnSeekBarChangeListener(
							new SBL(this, CONS.PlayActv.sb));

		////////////////////////////////

		// textview

		////////////////////////////////
		TextView tv_Memo = (TextView) findViewById(R.id.actv_play_tv);
		
		tv_Memo.setTag(Tags.ButtonTags.ACTV_PLAY_TV);
		
		tv_Memo.setOnTouchListener(new BO_TL(this));
		
		tv_Memo.setOnClickListener(new BO_CL(this));
		
	}//_Setup_Listeners
	
//	private void 
//	_Setup_Listeners_Memo() {
//		// TODO Auto-generated method stub
//		////////////////////////////////
//		
//		// IB: play
//		
//		////////////////////////////////
//		ImageButton ib_Save = (ImageButton) this.findViewById(R.id.actv_play_ib_save);
//		
//		ib_Save.setTag(Tags.ButtonTags.ACTV_PLAY_SAVE);
//		
//		ib_Save.setOnTouchListener(new BO_TL(this));
//		
//		ib_Save.setOnClickListener(new BO_CL(this));
//		
//		////////////////////////////////
//		
//		// IB: clear
//		
//		////////////////////////////////
//		ImageButton ib_Stop = (ImageButton) this.findViewById(R.id.actv_play_ib_clear);
//		
//		ib_Stop.setTag(Tags.ButtonTags.ACTV_PLAY_CLEAR);
//		
//		ib_Stop.setOnTouchListener(new BO_TL(this));
//		
//		ib_Stop.setOnClickListener(new BO_CL(this));
//		
//	}//_Setup_Listeners
	

	private boolean 
	_Setup_Get_Memo() {
		// TODO Auto-generated method stub
		
		Intent i = this.getIntent();
		
		long id = i.getLongExtra(CONS.Intent.iKey_Memo_Id, CONS.Intent.dflt_LongExtra_value);
		
		/******************************
			validate
		 ******************************/
		if (id == CONS.Intent.dflt_LongExtra_value) {
			
			// Log
			String msg_Log = "id => CONS.Intent.dflt_LongExtra_value";
			Log.e("PlayActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			String msg = "id => irregular";
			Methods_dlg.dlg_ShowMessage(this, msg, R.color.red);
			
			return false;
			
		}
		
		////////////////////////////////

		// get memo

		////////////////////////////////
		CONS.PlayActv.memo = DBUtils.find_Memo_From_Id(this, id);
		
		/******************************
			validate
		 ******************************/
		if (CONS.PlayActv.memo == null) {
			
			// Log
			String msg_Log = "can't find memo => " + id;
			Log.e("PlayActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			Methods_dlg.dlg_ShowMessage(this, msg_Log, R.color.red);
			
			return false;
			
		}		
		
		////////////////////////////////

		// get: text

		////////////////////////////////
		String text = CONS.PlayActv.memo.getText();
		
		Pattern p = Pattern.compile(CONS.RecActv.fmt_FileName_PlayMemo);
		Matcher m = p.matcher(text);
		
		if (m.find()) {
			
			CONS.PlayActv.fname_Audio = m.group(1);
//			CONS.PlayActv.fname_Audio = m.group(0);
			
			return true;
			
		} else {
			
			// Log
			String msg_Log = "can't get file name => " + text;
			Log.e("PlayActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			Methods_dlg.dlg_ShowMessage(this, msg_Log, R.color.red);
			
			return false;
						
		}
		
	}//public static final 

	@Override
	protected void onStop() {
		// TODO ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ黷ｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ\?�ｿｽ�ｿｽb?�ｿｽ�ｿｽh?�ｿｽ�ｿｽE?�ｿｽ�ｿｽX?�ｿｽ�ｿｽ^?�ｿｽ�ｿｽu
		super.onStop();
		
//		////////////////////////////////
//
//		// release
//
//		////////////////////////////////
//		if (CONS.PlayActv.mp != null) {
//			
//			CONS.PlayActv.mp.release();
//			
//			// Log
//			String msg_Log = "CONS.PlayActv.mp => released";
//			Log.d("PlayActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//		}

	}

	@Override
	public void onBackPressed() {
		/****************************
		 * memo
			****************************/
//		Methods.stop_Player(this);
		
		
		this.finish();
		
		overridePendingTransition(0, 0);
		
	}//public void onBackPressed()

	@Override
	protected void
	onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		
	}//onActivityResult(int requestCode, int resultCode, Intent data)

	
}//public class PlayActv extends Activity
