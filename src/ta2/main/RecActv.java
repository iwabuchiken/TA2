package ta2.main;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;

import ta2.listeners.button.BO_CL;
import ta2.listeners.button.BO_TL;
import ta2.utils.CONS;
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
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class RecActv extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

		////////////////////////////////

		// vars

		////////////////////////////////
		boolean res;
		
		setContentView(R.layout.actv_rec);

		this.setTitle(this.getClass().getName());

		////////////////////////////////

		// Init: vars

		////////////////////////////////
		_onCreate_InitVars();

		////////////////////////////////

		// Setup: views

		////////////////////////////////
//		res = _onCreate_SetupViews();

//		/******************************
//			validate
//		 ******************************/
//		if (res == false) {
//			
//			// Log
//			msg_Log = "Intent values => cant obtain";
//			Log.e("PlayActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//			// debug
//			Toast.makeText(this, msg_Log, Toast.LENGTH_SHORT).show();
//			
//			return;
//			
//		}

		////////////////////////////////

		// Setup: listeners

		////////////////////////////////
		_onCreate_SetListeners();
		
		
	}//public void onCreate(Bundle savedInstanceState)

	private void _onCreate_InitVars() {
		// TODO Auto-generated method stub
		
	}

	private void 
	_onCreate_SetListeners() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// Get: views

		////////////////////////////////
		////////////////////////////////

		// rec

		////////////////////////////////
		Button bt_Rec = (Button) findViewById(R.id.actv_rec_bt_rec);
		
		bt_Rec.setTag(Tags.ButtonTags.ACTV_REC_REC);
		
		bt_Rec.setOnTouchListener(new BO_TL(this));
		
		bt_Rec.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////
		
		// stop
		
		////////////////////////////////
		Button bt_Stop = (Button) findViewById(R.id.actv_rec_bt_stop);
		
		bt_Stop.setTag(Tags.ButtonTags.ACTV_REC_STOP);
		
		bt_Stop.setOnTouchListener(new BO_TL(this));
		
		bt_Stop.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////
		
		// back
		
		////////////////////////////////
		Button bt_Back = (Button) findViewById(R.id.actv_rec_bt_back);
		
		bt_Back.setTag(Tags.ButtonTags.ACTV_REC_BACK);
		
		bt_Back.setOnTouchListener(new BO_TL(this));
		
		bt_Back.setOnClickListener(new BO_CL(this));
		
		
	}//private void _onCreate_SetListeners()

	private boolean
	_onCreate_SetupViews() {

		return false;
		
	}//_onCreate_SetupViews()

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater mi = getMenuInflater();
//		mi.inflate(R.menu.menu_actv_play, menu);
//		mi.inflate(R.menu.menu_actv_play, menu);

		return super.onCreateOptionsMenu(menu);
		
	}//public boolean onCreateOptionsMenu(Menu menu)

	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		
		
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
	protected void 
	onStop() {
		// TODO ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ黷ｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ?�ｿｽ�ｿｽ\?�ｿｽ�ｿｽb?�ｿｽ�ｿｽh?�ｿｽ�ｿｽE?�ｿｽ�ｿｽX?�ｿｽ�ｿｽ^?�ｿｽ�ｿｽu
		super.onStop();
		
		////////////////////////////////

		// release

		////////////////////////////////
		if (CONS.RecActv.mr != null) {
			
			CONS.RecActv.mr.stop();
			
			CONS.RecActv.mr.release();
			
			// Log
			String msg_Log = "CONS.RecActv.mr => released";
			Log.d("RecActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
	}//onStop

	@Override
	public void onBackPressed() {
		
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
