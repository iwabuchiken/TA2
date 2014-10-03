package ta2.main;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;

import ta2.utils.CONS;

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
		_onCreate_SetListeners();
		
		
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

	private void _onCreate_SetListeners() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// Get: views

		////////////////////////////////
		
	}//private void _onCreate_SetListeners()

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
		
		
	}//protected void onStart()

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
