package ta2.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import ta2.adapters.Adp_WordPatterns;
import ta2.comps.Comp_WP;
import ta2.items.ListItem;
import ta2.items.MI;
import ta2.items.Memo;
import ta2.listeners.LOI_CL;
import ta2.listeners.LOI_LCL;
import ta2.listeners.SBL;
import ta2.listeners.SBL__PlayerActv;
import ta2.listeners.button.BO_CL;
import ta2.listeners.button.BO_TL;
import ta2.services.Service_ShowProgress__PlayerActv;
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

public class PlayerActv extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		/********************************
		 * 
		 ********************************/

		// Log
		String msg_Log = "onCreate";
		Log.d("PlayerActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		super.onCreate(savedInstanceState);

		setContentView(R.layout.actv_play);

		this.setTitle(this.getClass().getName());

//		////////////////////////////////
//
//		// Init: vars
//
//		////////////////////////////////
//		if (CONS.Admin.vib == null) {
//			
//			CONS.Admin.vib = 
//					(Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
//			
//		}
//		
//		////////////////////////////////
//
//		// Get: intent values
//
//		////////////////////////////////
//		boolean res = _onCreate_Get_IntentValues();
//		
//		/******************************
//			validate
//		 ******************************/
//		if (res == false) {
//			
//			// Log
//			msg_Log = "Intent values => cant obtain";
//			Log.e("PlayerActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//			// debug
//			Toast.makeText(this, msg_Log, Toast.LENGTH_SHORT).show();
//			
//			return;
//			
//		}
//
//		////////////////////////////////
//
//		// Init: vars
//
//		////////////////////////////////
//		_onCreate_InitVars();
//
//		/******************************
//			validate
//		 ******************************/
//		if (res == false) {
//			
//			// Log
//			msg_Log = "Intent values => cant obtain";
//			Log.e("PlayerActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//			// debug
//			Toast.makeText(this, msg_Log, Toast.LENGTH_SHORT).show();
//			
//			return;
//			
//		}
//
//		////////////////////////////////
//
//		// Prefs
//
//		////////////////////////////////
//		_onCreate_ManagePrefs();
		
	}//public void onCreate(Bundle savedInstanceState)

	private void _onCreate_ManagePrefs() {
		// TODO Auto-generated method stub
		
	}//private void _onCreate_ManagePrefs()

//	private void _onCreate_InitVars() {
//		// TODO Auto-generated method stub
//		
//		
//	}

//	private boolean _onCreate_Get_IntentValues() {
//		
////		// Log
////		String msg_Log = "_onCreate_Get_IntentValues";
////		Log.d("PlayerActv.java" + "["
////				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
////				+ "]", msg_Log);
//		
//		// TODO Auto-generated method stub
//		Intent i = this.getIntent();
//		
//		
//
//		return true;
//
//	}

	@Override
	public boolean 
	onCreateOptionsMenu(Menu menu) {

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

//		MenuInflater mi = getMenuInflater();
////		mi.inflate(R.menu.menu_actv_play, menu);
////		mi.inflate(R.menu.menu_actv_play, menu);
//
		return super.onCreateOptionsMenu(menu);
		
	}//public boolean onCreateOptionsMenu(Menu menu)

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
		
		list_MIs.add(new MI.Builder()
				.setId_Item(R.id.menu_main_import_actv)
				.setId_Title(R.string.menu_main_Start_ImportActv)
				.setId_Icon(R.drawable.general_ib_ball_purple_48x48)
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
	protected void onDestroy() {
		/*********************************
		 * memo
		 *********************************/
		// Log
		Log.d("PlayerActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "onDestroy()");
		
//		Methods.stop_player(this);
		
		/***************************************
		 * Clear prefs
		 ***************************************/
//		boolean res = Methods.clearPref(this, CONS.Pref.pname_PlayerActv);
		
		super.onDestroy();
		
		////////////////////////////////

		// release mp

		////////////////////////////////
		if (CONS.PlayerActv.mp != null) {
			
			CONS.PlayerActv.mp.release();
			
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
				CONS.Pref.pname_PlayerActv,
				CONS.Pref.pkey_PlayerActv_CurrentPosition,
//				CONS.Pref.pname_PlayerActv,
//				CONS.Pref.pkey_PlayerActv_CurrentPosition,
				CONS.Pref.dflt_LongExtra_value);
		
		// Log
		String msg_Log = "pkey_PlayerActv_CurrentPosition => cleared";
		Log.d("PlayerActv.java" + "["
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

		case R.id.menu_main_admin://--------------------
			
			case_OPT_Admin();
//			this.logoutFromTwitter();
			
			break;
			
		case R.id.menu_main_settings://--------------------
			
			case_OPT_Settings();
//			this.logoutFromTwitter();
			
			break;
			
		case R.id.menu_main_import_actv://--------------------
			
			case_OPT_Import_Actv();
//			this.logoutFromTwitter();
			
			break;
			
		default://-------------------------------------
			break;

			
		}//switch (item.getItemId())

		
		return super.onOptionsItemSelected(item);
	}//public boolean onOptionsItemSelected(MenuItem item)

	private void case_OPT_Import_Actv() {
		// TODO Auto-generated method stub
		
		Methods.start_Activity_ImportActv(this);
		
	}//case_OPT_Import_Actv

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
		
		String msg_Log;
		
		////////////////////////////////

		// Init: vars

		////////////////////////////////
		if (CONS.Admin.vib == null) {
			
			CONS.Admin.vib = 
					(Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
			
		}
		
//		////////////////////////////////
//
//		// Get: intent values
//
//		////////////////////////////////
//		res = _onCreate_Get_IntentValues();
//		
//		/******************************
//			validate
//		 ******************************/
//		if (res == false) {
//			
//			// Log
//			msg_Log = "Intent values => cant obtain";
//			Log.e("PlayerActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//			// debug
//			Toast.makeText(this, msg_Log, Toast.LENGTH_SHORT).show();
//			
//			return;
//			
//		}

//		////////////////////////////////
//
//		// Prefs
//
//		////////////////////////////////
//		_onCreate_ManagePrefs();
//
//		////////////////////////////////
//
//		// Init: vars
//
//		////////////////////////////////
//		_onCreate_InitVars();

//		/******************************
//			validate
//		 ******************************/
//		if (res == false) {
//			
//			// Log
//			msg_Log = "Intent values => cant obtain";
//			Log.e("PlayerActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//			// debug
//			Toast.makeText(this, msg_Log, Toast.LENGTH_SHORT).show();
//			
//			return;
//			
//		}
//
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
		res = _Setup_AudioLen();

		if (res == false) {
			
			return;
			
		}

		////////////////////////////////
		
		// views
		
		////////////////////////////////
		_Setup_Views();

		////////////////////////////////

		// listeners

		////////////////////////////////
		_Setup_Listeners();
		
	}//protected void onStart()

	private void 
	_Setup_Views() {
		// TODO Auto-generated method stub

		////////////////////////////////

		// memo

		////////////////////////////////
		TextView tv_Memo = (TextView) findViewById(R.id.actv_play_tv);
		
		tv_Memo.setText(CONS.PlayerActv.audio_memo.getText());
//		tv_Memo.setText(CONS.PlayActv.memo.getText());
		
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
		
		if (CONS.PlayerActv.len_Audio >= 0) {
//			if (CONS.PlayActv.len_Audio >= 0) {
			
			tv_Length.setText(Methods.conv_MillSec_to_ClockLabel(CONS.PlayerActv.len_Audio));
			
		} else {
			
			tv_Length.setText("xx:xx");
			
		}
		
		////////////////////////////////

		// get: Current position

		////////////////////////////////
		CONS.PlayerActv.tv_CurrentPosition = 
				(TextView) findViewById(
						R.id.actv_play_tv_current_position);

		////////////////////////////////

		// IB: stop => disable

		////////////////////////////////
		ImageButton ib_Stop = (ImageButton) findViewById(R.id.actv_play_ib_stop);
		
		ib_Stop.setEnabled(false);
		
	}//_Setup_Views

	private boolean 
	_Setup_AudioLen() {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// get file name

		////////////////////////////////
		String text = CONS.PlayerActv.audio_memo.getText();
		
		Pattern p = Pattern.compile(CONS.RecActv.fmt_FileName_PlayerMemo);
//		Pattern p = Pattern.compile(CONS.RecActv.fmt_FileName_PlayMemo);
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
			Log.d("PlayerActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
		} else {
			
				// Log
				String msg_Log = "no match";
				Log.e("PlayerActv.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", msg_Log);
				
				return false;
			
		}
		
		////////////////////////////////

		// set: length

		////////////////////////////////
		long tmp = Methods.get_AudioLength(file_full_path);
		
		if (tmp > 0) {
			
			CONS.PlayerActv.len_Audio = tmp;
//			CONS.PlayerActv.len_Audio = Methods.get_AudioLength(file_full_path);
			
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
			Log.d("PlayerActv.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + "]", msg);
			
			return false;
			
		}//if (tmp > 0)
		
		// Log
		String msg_Log;
		
		msg_Log = String.format(
				Locale.JAPAN,
				"audio len => %s (%s)", 
				CONS.PlayerActv.len_Audio,
				Methods.conv_MillSec_to_ClockLabel(CONS.PlayerActv.len_Audio)
//				Methods.conv_MillSec_to_ClockLabel(CONS.PlayActv.len_Audio)
				);
		
		Log.i("PlayerActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

////		String msg_Log = "audio len => " + CONS.PlayerActv.len_Audio;
//		Log.d("PlayerActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		return true;
		
	}//_Setup_AudioLen

	private void 
	_Setup_Listeners() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// IB: play

		////////////////////////////////
		ImageButton ib_Play = (ImageButton) this.findViewById(R.id.actv_play_ib_play);
		
		ib_Play.setTag(Tags.ButtonTags.ACTV_PLAYER_PLAY);
//		ib_Play.setTag(Tags.ButtonTags.ACTV_PLAY_PLAY);
		
		ib_Play.setOnTouchListener(new BO_TL(this));
		
		ib_Play.setOnClickListener(new BO_CL(this));

		////////////////////////////////
		
		// IB: stop
		
		////////////////////////////////
		ImageButton ib_Stop = (ImageButton) this.findViewById(R.id.actv_play_ib_stop);
		
		ib_Stop.setTag(Tags.ButtonTags.ACTV_PLAYER_STOP);
		
		ib_Stop.setOnTouchListener(new BO_TL(this));
		
		ib_Stop.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////
		
		// IB: back
		
		////////////////////////////////
		ImageButton ib_Back = (ImageButton) this.findViewById(R.id.actv_play_ib_back);
		
		ib_Back.setTag(Tags.ButtonTags.ACTV_PLAYER_BACK);
		
		ib_Back.setOnTouchListener(new BO_TL(this));
		
		ib_Back.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////

		// seekbar

		////////////////////////////////
		CONS.PlayerActv.sb = (SeekBar) findViewById(R.id.actv_play_sb);
		
		CONS.PlayerActv.sb.setOnSeekBarChangeListener(
							new SBL__PlayerActv(this, CONS.PlayerActv.sb));
//		new SBL(this, CONS.PlayerActv.sb));

		////////////////////////////////

		// textview

		////////////////////////////////
		TextView tv_Memo = (TextView) findViewById(R.id.actv_play_tv);
		
		tv_Memo.setTag(Tags.ButtonTags.ACTV_PLAYER_TV);
		
		tv_Memo.setOnTouchListener(new BO_TL(this));
		
		tv_Memo.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////

		// "See bookmarks"

		////////////////////////////////
		Button btSeeBM = (Button) findViewById(R.id.actv_play_bt_see_bm);
		
		btSeeBM.setTag(Tags.ButtonTags.ACTV_PLAYER_BT_SEE_BM);
//		btSeeBM.setTag(Tags.ButtonTags.actv_play_bt_see_bm);
		
		btSeeBM.setOnTouchListener(new BO_TL(this));
		btSeeBM.setOnClickListener(new BO_CL(this, CONS.PlayerActv.audio_memo));
//		btSeeBM.setOnClickListener(new BO_CL(this, CONS.PlayActv.ai));
		
		////////////////////////////////

		// "Add bookmark"

		////////////////////////////////
		Button btAddBM = (Button) findViewById(R.id.actv_play_bt_add_bm);
		
		btAddBM.setTag(Tags.ButtonTags.ACTV_PLAYER_BT_ADD_BM);
//		btAddBM.setTag(Tags.ButtonTags.actv_play_bt_add_bm);
		
		// Log
		String msg_Log;
		
		msg_Log = String.format(
				Locale.JAPAN,
				"CONS.PlayerActv.audio_memo => %s", CONS.PlayerActv.audio_memo.getText()
				);
		
		Log.i("PlayerActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		btAddBM.setOnTouchListener(new BO_TL(this));
		btAddBM.setOnClickListener(new BO_CL(this, CONS.PlayerActv.audio_memo));
//		btAddBM.setOnClickListener(new BO_CL(this, CONS.PlayActv.ai));
		

		
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
			Log.e("PlayerActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			String msg = "id => irregular";
			Methods_dlg.dlg_ShowMessage(this, msg, R.color.red);
			
			return false;
			
		}
		
		////////////////////////////////

		// get memo

		////////////////////////////////
		CONS.PlayerActv.audio_memo = DBUtils.find_AudioMemo_From_Id(this, id);
//		CONS.PlayActv.memo = DBUtils.find_Memo_From_Id(this, id);
		
		/******************************
			validate
		 ******************************/
		if (CONS.PlayerActv.audio_memo == null) {
//			if (CONS.PlayActv.memo == null) {
			
			// Log
			String msg_Log = "can't find memo => " + id;
			Log.e("PlayerActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			Methods_dlg.dlg_ShowMessage(this, msg_Log, R.color.red);
			
			return false;
			
		}		

//		//debug
//		return false;

		///////////////////////////////////
		//
		// set file path
		//
		///////////////////////////////////
		
		String text = CONS.PlayerActv.audio_memo.getText();
		
		Pattern p = Pattern.compile(CONS.RecActv.fmt_FileName_PlayerMemo);
//		Pattern p = Pattern.compile(CONS.RecActv.fmt_FileName_PlayMemo);
		Matcher m = p.matcher(text);
		
		if (m.find()) {
			
			CONS.PlayerActv.fname_Audio = m.group(1);
//			CONS.PlayActv.fname_Audio = m.group(1);
//			CONS.PlayActv.fname_Audio = m.group(0);
			
			// reset audio path value
			CONS.DB.dPath_Audio = "/mnt/sdcard/AllVoiceRecords";
//			CONS.DB.dPath_Audio = CONS.DB.dPath_Data_Root + "/audio";
			
			// Log
			String msg_Log;
			
			msg_Log = String.format(
					Locale.JAPAN,
					"CONS.PlayerActv.fname_Audio => %s", CONS.PlayerActv.fname_Audio
					);
			
			Log.i("PlayerActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			//test
			File f = new File(CONS.DB.dPath_Audio, CONS.PlayerActv.fname_Audio);
			
			if (f.exists()) {
				
				// Log
//				String msg_Log;
			
				msg_Log = String.format(
						Locale.JAPAN,
						"file exists => %s", f.getAbsolutePath()
						);
				
				Log.i("PlayerActv.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", msg_Log);
				
			} else {//if (f.exists())
				
				msg_Log = String.format(
						Locale.JAPAN,
						"file doesn't exist => %s", f.getAbsolutePath()
						);
				
				Log.i("PlayerActv.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", msg_Log);
				
			}//if (f.exists())
			
			
			return true;
			
		} else {
			
			// Log
			String msg_Log = "can't get file name => " + text;
			Log.e("PlayerActv.java" + "["
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
//			Log.d("PlayerActv.java" + "["
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

		/***************************************
		 * Stop: Service
		 ***************************************/
		if (CONS.PlayerActv.i_Service_Progress__PlayerActv == null) {

			CONS.PlayerActv.i_Service_Progress__PlayerActv = 
					new Intent((Context) this, Service_ShowProgress__PlayerActv.class);

		}//if (CONS.PlayerActv.i_Service_Progress == null)
		
//		Intent i = new Intent((Context) actv, Service_ShowProgress__PlayerActv.class);
//		Intent i = new Intent((Context) actv, Service_ShowProgress.class);
		
		//
//		i.putExtra("counter", timeLeft);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Stopping service...");
		
		//
//		this.startService(i);
		this.stopService(CONS.PlayerActv.i_Service_Progress__PlayerActv);
//		actv.stopService(i);

//		Intent i = new Intent((Context) this, Service_ShowProgress__PlayerActv.class);
////		Intent i = new Intent((Context) actv, Service_ShowProgress.class);
//		
//		//
////		i.putExtra("counter", timeLeft);
//		
//		// Log
//		String msg_Log;
//		
//		msg_Log = String.format(
//				Locale.JAPAN,
//				"service => stopping..."
//				);
//		
//		Log.i("PlayerActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
//		
//		//
////		actv.startService(i);
//		this.stopService(i);
		
		///////////////////////////////////
		//
		// finish
		//
		///////////////////////////////////
		this.finish();
		
		overridePendingTransition(0, 0);
		
	}//public void onBackPressed()

	@Override
	protected void
	onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == CONS.Intent.REQUEST_CODE_SEE_BOOKMARKS) {

			if (resultCode == CONS.Intent.RESULT_CODE_SEE_BOOKMARKS_OK) {
				
				onActivityResult_BM_OK(data);
				
			} else if (resultCode == CONS.Intent.RESULT_CODE_SEE_BOOKMARKS_CANCEL) {//if (resultCode == CONS.Intent.RESULT_CODE_SEE_BOOKMARKS_OK)
				
				onActivityResult_BM_Cancel();
				
			}//if (resultCode == CONS.Intent.RESULT_CODE_SEE_BOOKMARKS_OK)
			
			
		} else {//if (requestCode == CONS.Intent.REQUEST_CODE_SEE_BOOKMARKS)
			
			// Log
			Log.d("PlayActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "request code => " + requestCode);
			
		}//if (requestCode == CONS.Intent.REQUEST_CODE_SEE_BOOKMARKS)

	}//onActivityResult(int requestCode, int resultCode, Intent data)

	private void 
	onActivityResult_BM_Cancel() {
		// TODO Auto-generated method stub
		// Log
		Log.d("PlayActv.java"
				+ "["
				+ Thread.currentThread().getStackTrace()[2]
						.getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2]
						.getMethodName() + "]", "Cancelled");
		
		////////////////////////////////

		// Reset: SeekBar

		////////////////////////////////
		
		
//		long currentPosition = Methods.conv_ClockLabel_to_MillSec(position);
		long currentPosition = Methods.get_Pref_Long(this, 
							CONS.Pref.pname_PlayActv, 
							CONS.Pref.pkey_PlayActv_CurrentPosition, 
							CONS.Pref.dflt_LongExtra_value);
		
		long length =
				Methods.conv_ClockLabel_to_MillSec(CONS.PlayerActv.audio_memo.getLength());
//		Methods.conv_ClockLabel_to_MillSec(CONS.PlayActv.ai.getLength());
		
		int seekPositon = (int)
//					((currentPosition / length)
					(((float)currentPosition / length)
							* CONS.PlayActv.sb.getMax());
//		
		CONS.PlayActv.sb.setProgress(seekPositon);


	}

	private void
	onActivityResult_BM_OK(Intent data) {
		// TODO Auto-generated method stub
		String position = data.getStringExtra(CONS.Intent.iKey_BMActv_Position);
//		long position = data.getLongExtra(CONS.Intent.iKey_BMActv_Position, -1);
		
		long aiDbId = data.getLongExtra(
							CONS.Intent.iKey_BMActv_AI_Id,
							CONS.Intent.dflt_LongExtra_value);
		
		String aiTableName = data.getStringExtra(
						CONS.Intent.iKey_BMActv_TableName);

		/***************************************
		 * Set: Preference
		 ***************************************/
		boolean res = 
				Methods.setPref_Long(
						this,
						CONS.Pref.pname_PlayActv,
//						CONS.Pref.pkey_PlayActv_position,
						CONS.Pref.pkey_PlayActv_CurrentPosition,
//						CONS.Pref.pkey_CurrentPosition,
						Methods.conv_ClockLabel_to_MillSec(position));
		// Log
		Log.d("PlayActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Position => Stored in a preference");

		/***************************************
		 * Set: Seekbar
		 ***************************************/
		long currentPosition = Methods.conv_ClockLabel_to_MillSec(position);
		long length =
				Methods.conv_ClockLabel_to_MillSec(CONS.PlayerActv.audio_memo.getLength());
//		Methods.conv_ClockLabel_to_MillSec(CONS.PlayerActv.ai.getLength());
//		Methods.conv_ClockLabel_to_MillSec(CONS.PlayerActv.ai.getLength());
		
		int seekPositon = (int)
//					((currentPosition / length)
					(((float)currentPosition / length)
							* CONS.PlayerActv.sb.getMax());
//		
		CONS.PlayerActv.sb.setProgress(seekPositon);
		
		// Log
		String msg_Log = "currentPosition = " + currentPosition
						+ " // "
						+ "seekPositon = " + seekPositon
						+ " // "
						+ "length = " + length
						;
		
		Log.d("PlayActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		
	}//onActivityResult_BM_OK(Intent data)

	
}//public class PlayActv extends Activity
