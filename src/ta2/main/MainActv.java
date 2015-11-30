package ta2.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import ta2.items.MI;
import ta2.listeners.STL;
import ta2.listeners.button.BO_CL;
import ta2.listeners.button.BO_TL;
import ta2.utils.CONS;
import ta2.utils.DBUtils;
import ta2.utils.Methods;
import ta2.utils.Methods_dlg;
import ta2.utils.Tags;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.LinearLayout;

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
		
		////////////////////////////////

		// tests

		////////////////////////////////
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
//		int res = last_bk.compareToIgnoreCase(schedule);
		String now = Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now());
		int res = schedule.compareToIgnoreCase(now);
//		int res = schedule.compareToIgnoreCase(last_bk);

		// Log
		msg_Log = String.format(
						Locale.JAPAN,
						"schedule = %s / now = %s / res => %d", 
						schedule, now, res
						);
		
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
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
	
		this._test_D_30_V_1_0__PlayOtherFile();
//		this._test_D_24_V_2_1_PreviousDate();
//		this._test_CV_update();
//		_test_Content_Provider();
//		_test_PrefVal_SoundEffect();
		
	}

	private void _test_D_30_V_1_0__PlayOtherFile() {
		// TODO Auto-generated method stub
		
		String dpath = "/mnt/sdcard/AllVoiceRecords";
		
		String fname = "レコード_2015-11-29_16-29-18.mp3";
		
		String file_full_path = StringUtils.join(
						new String[]{
								dpath,
								fname
//								CONS.DB.dPath_Audio,
//								CONS.PlayActv.fname_Audio
						},
						File.separator);

		Methods.play_File(this, file_full_path);
		
		
	}


//	private void 
//	_test_CV_update() {
//		// TODO Auto-generated method stub
//		
//		// Log
//		String msg_Log = "_test_CV_update";
//		Log.d("MainActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//		
////		Uri contentUri = Uri.parse(CONS.PhotoActv.content_Uri + CONS.IFM11.tname_IFM11);
//		Uri contentUri = Uri.parse(CONS.PhotoActv.content_Uri);
//
////		// testテーブルの_idが1のレコードを取得する
////		Cursor cursor = getContentResolver().query(ContentUris.withAppendedId(contentUri, 1), null, null, null, null);
////
////		// testテーブルの全レコードを取得する
////		Cursor cursor2 = getContentResolver().query(contentUri, null, null, null, null);
////
////		// testテーブルにレコードを投入する
////		// FIXME 本当はContentValuesに投入データを入れるが省略
////		Uri newUri = getContentResolver().insert(contentUri, new ContentValues());
//
//		// testテーブルの_idが1のレコードを更新する
//		// FIXME 本当はContentValuesに投入データを入れるが省略
//		ContentValues cv = new ContentValues();
//		
//		cv.put("memos", ":m Σ　積分　MATH AAA");
//		
//		String where = android.provider.BaseColumns._ID
//				+ " = ?";
//
//		String[] args = new String[]{
//				
//				""
//				
//		};
//
//		int updatedCount = getContentResolver()
//					.update(
//							contentUri, 
////							ContentUris.withAppendedId(contentUri, 1), 
//							cv, 
////							new ContentValues(), 
//							null, null);
//		
//		// Log
//		msg_Log = "updatedCount => " + updatedCount;
//		Log.d("MainActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//		
//	}//_test_CV_update


	private void 
	_test_D_24_V_2_1_PreviousDate() {
		// TODO Auto-generated method stub

		Methods.auto_Uplad_DB(this);
		
//		Calendar cal = Calendar.getInstance();
////	    cal.setTime(date);
//	    cal.add(Calendar.DAY_OF_MONTH, -1);
//		
//	    String now = Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now());
//	    
//	    String prev = Methods.conv_MillSec_to_TimeLabel(cal.getTime().getTime());
//	    
//	    // Log
//		String msg_Log;
//		
//		msg_Log = String.format(
//				Locale.JAPAN,
//				"now = %s | prev = %s", now, prev
//				);
//		
//		Log.i("MainActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
	    
	}//_test_D_24_V_2_1_PreviousDate

	private void 
	_test_Content_Provider() {
		// TODO Auto-generated method stub
		
		String CONTENT =
		        "content://ifm11.main.CV/";
//		"content://ifm11.main.CV";
//		"content://ifm11.provider.db";
//		"content://ifm11.provider.db/";
		
		getIntent().setData(Uri.parse(CONTENT));
		
		Cursor c = null;
		
		try {
			
			// Log
			String msg_Log = "calling query...";
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			c = managedQuery(getIntent().getData(), null, null, null, null);
			
			// Log
			msg_Log = "query => done";
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} catch (Exception e) {
			// TODO: handle exception
			
			// Log
			String msg_Log = "Exception";
			Log.e("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			e.printStackTrace();
			
			return;
			
		}
		
		// Log
		String msg_Log = "cursor => obtained";
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		/***************************************
		 * Validate
		 * 	Cursor => Null?
		 * 	Entry => 0?
		 ***************************************/
		if (c == null) {
			
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Query failed");
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "No entry in the table");
			
		}//if (c == null)
		
		////////////////////////////////

		// get info

		////////////////////////////////
		// Log
		msg_Log = "c.getCount() => " + c.getCount();
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		
	}//_test_Content_Provider

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
		
		// IB: voice memo
		
		////////////////////////////////
		ImageButton bt_Voice = 
				(ImageButton) this.findViewById(R.id.actv_main_ib_voice);
		
		bt_Voice.setTag(Tags.ButtonTags.ACTV_MAIN_VOICE);
		
		bt_Voice.setOnTouchListener(new BO_TL(this));
		
		bt_Voice.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////
		
		// IB: photo memo
		
		////////////////////////////////
		ImageButton bt_Photo = 
				(ImageButton) this.findViewById(R.id.actv_main_ib_photo);
		
		bt_Photo.setTag(Tags.ButtonTags.ACTV_MAIN_PHOTO);
		
		bt_Photo.setOnTouchListener(new BO_TL(this));
		
		bt_Photo.setOnClickListener(new BO_CL(this));
		
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
		
		////////////////////////////////////////////////////
		//
		// reset variables
		//
		////////////////////////////////////////////////////
		///////////////////////////////////
		//
		// ShowListActv: filtering string
		//
		///////////////////////////////////
		boolean res_B = Methods.set_Pref_String(
							this, 
							CONS.Pref.pname_MainActv, 
							CONS.Pref.pkey_ShowListActv_Filter_String, 
							null);
		
		if (res_B == true) {
			
			// Log
			String msg_Log;
			
			msg_Log = String.format(
					Locale.JAPAN,
					"filtering string => set null (ShowListActv)"
					);
			
			Log.i("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} else {//if (res_B == true)
			
			// Log
			String msg_Log;
			
			msg_Log = String.format(
					Locale.JAPAN,
					"filtering string => set null (ShowListActv) ~~> returned false"
					);
			
			Log.e("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}//if (res_B == true)
		
		
	}//protected void onDestroy()


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
        
        ///////////////////////////////////
		//
		// test
		//
		///////////////////////////////////
        // Log
		String msg_Log;
		
		//REF model name http://stackoverflow.com/questions/7071281/get-android-device-name answered Aug 15 '11 at 22:07
		msg_Log = String.format(
				Locale.JAPAN,
				"model name => %s", android.os.Build.MODEL
				);
		
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
        
		///////////////////////////////////
		//
		// adjust: adjust paths by model names
		//
		///////////////////////////////////
		// data root path
		if (android.os.Build.MODEL.equals(CONS.DB.modelname_IS13SH)) {
//			if (android.os.Build.MODEL == CONS.DB.modelname_IS13SH) {

			CONS.DB.dPath_Data_Root = Environment.getExternalStorageDirectory().getPath()
										+ "/ta2_data";
			
			CONS.DB.dPath_dbFile_Backup = CONS.DB.dPath_Data_Root + "/ta2_backup";
			
			CONS.DB.dPath_Data = CONS.DB.dPath_Data_Root + "/data";
			
			CONS.DB.dPath_Log = CONS.DB.dPath_Data_Root + "/log";
			
			CONS.DB.dPath_Audio = CONS.DB.dPath_Data_Root + "/audio";
 
					
//			CONS.DB.dPath_Data_Root = "/mnt/sdcard/ta2_data";

			// Log
			//String msg_Log;
			
			msg_Log = String.format(
					Locale.JAPAN,
					"data root path => modified: %s", CONS.DB.dPath_Data_Root
					);
			
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			// Log
			//String msg_Log;
			
			msg_Log = String.format(
					Locale.JAPAN,
					"Environment.getExternalStorageDirectory().getPath()  => %s", Environment.getExternalStorageDirectory().getPath() 
					);
			
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		}//if (android.os.Build.MODEL == "")
		
	}//protected void onCreate(Bundle savedInstanceState)

	@Override
	public boolean 
	onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_actv_main, menu);
		
		///////////////////////////////////
		//
		// init
		//
		///////////////////////////////////
		CONS.MainActv.menu = menu;
		
		////////////////////////////////

		// prep: data

		////////////////////////////////
		_onCreateOptionsMenu__Build_Data(menu);
		
//		List<MI> list_MIs = new ArrayList<MI>();
//		
//		list_MIs.add(new MI.Builder()
//					.setId_Item(R.id.menu_main_logout)
//					.setId_Title(R.string.menu_main_logout)
//					.setId_Icon(R.drawable.general_ib_ball_blue_48x48)
//					.build()
//				);
		

		return true;
		
	}//onCreateOptionsMenu

	
	private void 
	_onCreateOptionsMenu__Build_Data(Menu menu) {
		// TODO Auto-generated method stub
		List<MI> list_MIs = new ArrayList<MI>();
		
		///////////////////////////////////
		//
		// add items
		//
		///////////////////////////////////
//		list_MIs.add(new MI.Builder()
//					.setId_Item(R.id.menu_main_logout)
//					.setId_Title(R.string.menu_main_logout)
//					.setId_Icon(R.drawable.general_ib_ball_blue_48x48)
//					.build()
//		);

		list_MIs.add(new MI.Builder()
				.setId_Item(R.id.menu_main_admin)
				.setId_Title(R.string.menu_main_admin)
				.setId_Icon(R.drawable.general_ib_ball_green_48x48)
				.build()
				);
		
		list_MIs.add(new MI.Builder()
				.setId_Item(R.id.menu_main_settings)
				.setId_Title(R.string.action_settings)
				.setId_Icon(R.drawable.general_ib_ball_yellow_48x48)
				.build()
				);
		
		///////////////////////////////////
		//
		// clear menu
		//
		///////////////////////////////////
		CONS.MainActv.menu.clear();
		
		for (int i = 0; i < list_MIs.size(); i++) {
//			for (int i = 0; i < list_Icons.size(); i++) {
			
			CONS.MainActv.menu
				.add(
					0, 
					list_MIs.get(i).getId_Item(), 
					2, 
					list_MIs.get(i).getId_Title())
				.setIcon(list_MIs.get(i).getId_Icon());
			
		}

	}//_onCreateOptionsMenu__Build_Data

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
