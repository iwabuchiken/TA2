package ta2.main;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import ta2.items.Memo;
import ta2.listeners.button.BO_CL;
import ta2.listeners.button.BO_TL;
import ta2.utils.CONS;
import ta2.utils.DBUtils;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;
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

		////////////////////////////////

		// Setup: views

		////////////////////////////////
		res = _onCreate_SetupViews();

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

		// Setup: listeners

		////////////////////////////////
//		_onCreate_SetListeners();
		
		
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

	private boolean
	_onCreate_SetupViews() {
		// - Get values from the AI instance
		// - Set the values to the views
		

		return true;
		
	}//_onCreate_SetupViews()

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
		
		_Setup_Listeners();
		
	}//protected void onStart()

	private void 
	_Setup_Listeners() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// IB: play

		////////////////////////////////
		ImageButton ib_Play = (ImageButton) this.findViewById(R.id.actv_play_bt_play);
		
		ib_Play.setTag(Tags.ButtonTags.ACTV_PLAY_PLAY);
		
		ib_Play.setOnTouchListener(new BO_TL(this));
		
		ib_Play.setOnClickListener(new BO_CL(this));

		////////////////////////////////
		
		// IB: stop
		
		////////////////////////////////
		ImageButton ib_Stop = (ImageButton) this.findViewById(R.id.actv_play_bt_stop);
		
		ib_Stop.setTag(Tags.ButtonTags.ACTV_PLAT_STOP);
		
		ib_Stop.setOnTouchListener(new BO_TL(this));
		
		ib_Stop.setOnClickListener(new BO_CL(this));
		
	}//_Setup_Listeners
	

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
