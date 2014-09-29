package ta2.main;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import ta2.listeners.STL;
import ta2.listeners.button.BO_CL;
import ta2.listeners.button.BO_TL;
import ta2.utils.CONS;
import ta2.utils.DBUtils;
import ta2.utils.Methods;
import ta2.utils.Methods_dlg;
import ta2.utils.Tags;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActv extends Activity {

    @Override
	protected void 
	onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		// Log
		String log_msg = "onStart()";

		Log.d("[" + "MainActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", log_msg);
		
		////////////////////////////////

		// setup: listeners

		////////////////////////////////
		_Setup_Listeners();
		
		////////////////////////////////

		// auto bk

		////////////////////////////////
		this._Setup_AutoBK();
		
		do_test();
		
	}//onStart

	private void 
	_Setup_AutoBK() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// get: pref

		////////////////////////////////
		String auto = Methods.get_Pref_String(
						this, 
						CONS.Pref.pname_MainActv, 
						this.getString(R.string.prefs_db_auto_backup_key),
//						"prefs_tnactv_db_auto_backup_key", 
						null);
		
		if (auto == null) {
			
			// Log
			String msg_Log = "auto bk => null";
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return;
			
		}
		
		//
		int auto_days = Integer.parseInt(auto);
		
		// Log
		String msg_Log = "auto_days => " + auto_days;
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		////////////////////////////////

		// get: last bk-ed

		////////////////////////////////
		String last_bk = DBUtils.find_LastBK(this);
		
		////////////////////////////////

		// now + auto

		////////////////////////////////
//		String now = Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now());
		
		String schedule = Methods.conv_MillSec_to_TimeLabel(
								Methods.conv_TimeLabel_to_MillSec(last_bk)
								+ (1 * 60 * 60 * 24 * auto_days * 1000));

		// Log
		msg_Log = String.format(
							"last = %s ** sch = %s", 
							last_bk, schedule);
		
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		////////////////////////////////

		// comp

		////////////////////////////////
		int res = schedule.compareToIgnoreCase(last_bk);
		
		if (res <= 0) {
//			if (res > 0) {
			
			Methods.backup_DB(this);
			
			// Log
			msg_Log = "auto bk => done";
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} else {
			
			// Log
			msg_Log = "auto bk => not yet";
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}
		
	}//_Setup_AutoBK

    
	private void 
	do_test() {
		// TODO Auto-generated method stub
	
//		_test_PrefVal_SoundEffect();
		
	}


	private void 
	_test_PrefVal_SoundEffect() {
		// TODO Auto-generated method stub
	
		boolean val = Methods.get_Pref_Boolean(
						this, 
						CONS.Pref.pname_MainActv, 
						this.getString(R.string.prefs_sound_effect_key), 
						false);
		
		// Log
		String msg_Log = "pref sound effect => " + val;
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}
	


	private void 
	_Setup_Listeners() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// IB: memo

		////////////////////////////////
		ImageButton bt_Memo = (ImageButton) this.findViewById(R.id.actv_main_ib_memo);
		
		bt_Memo.setTag(Tags.ButtonTags.ACTV_MAIN_MEMO);
		
		bt_Memo.setOnTouchListener(new BO_TL(this));
		
		bt_Memo.setOnClickListener(new BO_CL(this));
		
//		bt_Up.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////
		
		// IB: showlist
		
		////////////////////////////////
		ImageButton bt_ShowList = 
				(ImageButton) this.findViewById(R.id.actv_main_ib_showlist);
		
		bt_ShowList.setTag(Tags.ButtonTags.ACTV_MAIN_SHOWLIST);
		
		bt_ShowList.setOnTouchListener(new BO_TL(this));
		
		bt_ShowList.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////

		// LL: base

		////////////////////////////////
		LinearLayout ll_Base = (LinearLayout) findViewById(R.id.actv_main_ll_base);
		
		ll_Base.setTag(Tags.SwipeTags.ACTV_MAIN);
		
		ll_Base.setOnTouchListener(new STL(this));
		
	}//_Setup_Listeners


	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}


	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}


	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		// Log
		String log_msg = "onResume()";

		Log.d("[" + "MainActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", log_msg);
		
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actv_main);
		
		this.setTitle(this.getClass().getName());
		
		// Log
		String log_msg = "Starting => onCreate()";

		Log.d("[" + "MainActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", log_msg);
		
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
         
        
	}//protected void onCreate(Bundle savedInstanceState)

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_actv_main, menu);
		return true;
		
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		
		case R.id.menu_main_admin://--------------------
			
			case_OPT_Admin();
//			this.logoutFromTwitter();
			
			break;
			
		case R.id.menu_main_settings://--------------------
			
			case_OPT_Settings();
//			this.logoutFromTwitter();
			
			break;
			
		default://-------------------------------------
			break;
	
		}
		
		return super.onOptionsItemSelected(item);
		
	}//public boolean onOptionsItemSelected(MenuItem item)

	private void case_OPT_Settings() {
		// TODO Auto-generated method stub
		
		Methods.start_Activity_PrefActv(this);
		
	}


	private void 
	case_OPT_Admin() {
		// TODO Auto-generated method stub
		
		Methods_dlg.dlg_ACTV_MAIN_Admin(this);
		
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		Methods.confirm_quit(this, keyCode);
		
		return super.onKeyDown(keyCode, event);
	}

}//public class MainActv extends Activity
