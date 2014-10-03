package ta2.main;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.apache.commons.lang.StringUtils;

import ta2.adapters.Adp_WordPatterns;
import ta2.comps.Comp_WP;
import ta2.listeners.LOI_CL;
import ta2.listeners.LOI_LCL;
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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
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

//		////////////////////////////////
//
//		// Setup: listeners
//
//		////////////////////////////////
//		_Setup_SetListeners();
		
		
	}//public void onCreate(Bundle savedInstanceState)

	private void _onCreate_InitVars() {
		// TODO Auto-generated method stub
		
	}

	private void 
	_Setup_Listeners() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// Get: views

		////////////////////////////////
		////////////////////////////////

		// rec

		////////////////////////////////
		ImageButton ib_Rec = (ImageButton) findViewById(R.id.actv_rec_bt_rec);
		
		ib_Rec.setTag(Tags.ButtonTags.ACTV_REC_REC);
		
		ib_Rec.setOnTouchListener(new BO_TL(this));
		
		ib_Rec.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////
		
		// stop
		
		////////////////////////////////
		ImageButton ib_Stop = (ImageButton) findViewById(R.id.actv_rec_bt_stop);
		
		ib_Stop.setTag(Tags.ButtonTags.ACTV_REC_STOP);
		
		ib_Stop.setOnTouchListener(new BO_TL(this));
		
		ib_Stop.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////
		
		// back
		
		////////////////////////////////
		ImageButton ib_Back = (ImageButton) findViewById(R.id.actv_rec_bt_back);
		
		ib_Back.setTag(Tags.ButtonTags.ACTV_REC_BACK);
		
		ib_Back.setOnTouchListener(new BO_TL(this));
		
		ib_Back.setOnClickListener(new BO_CL(this));
		
		
	}//private void _onCreate_SetListeners()

	private boolean
	_Setup_SetupViews() {

		////////////////////////////////

		// ib: stop

		////////////////////////////////
		ImageButton ib_Stop = (ImageButton) findViewById(R.id.actv_rec_bt_stop);
		
		ib_Stop.setEnabled(false);
		
		////////////////////////////////

		// listviews

		////////////////////////////////
		
		
		
		return false;
		
	}//_onCreate_SetupViews()

	private void 
	_Setup_Layout() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// vars

		////////////////////////////////
//		final int layout_MemoActv_LV_Height = 50;	// X out of 100
		
		////////////////////////////////

		// listviews

		////////////////////////////////
		LinearLayout ll_LV = (LinearLayout) findViewById(R.id.actv_rec_ll_listview);
//		LinearLayout ll_LV = (LinearLayout) findViewById(R.id.actv_memo_ll_listview);
		
		////////////////////////////////

		// get: screen size

		////////////////////////////////
		//REF size http://stackoverflow.com/questions/19155559/how-to-get-android-device-screen-size answered Oct 3 '13 at 10:00
		DisplayMetrics displayMetrics = this.getResources()
                			.getDisplayMetrics();
		
		int w = displayMetrics.widthPixels;
		
		// Log
		String msg_Log = "w => " + w;
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		int layout_Width = w * CONS.MemoActv.layout_MemoActv_LV_Height / 100;
//		int layout_Width = w * layout_MemoActv_LV_Height / 100;

		
		LinearLayout.LayoutParams params =
				new LinearLayout.LayoutParams(
								LayoutParams.WRAP_CONTENT,
								layout_Width
				);
//		LayoutParams.WRAP_CONTENT);
		
		params.setMargins(20, 10, 20, 30);

		ll_LV.setLayoutParams(params);
		
//		////////////////////////////////
//
//		// EditText
//
//		////////////////////////////////
//		EditText et = (EditText) this.findViewById(R.id.actv_memo_et);
//
//		String text = et.getText().toString();
//		
//		if (text != null && text.length() > 0) {
//			
//			et.setSelection(text.length());
//			
//		}
		
	}//_Setup_Layout

	private boolean 
	_Setup_Lists() {
		// TODO Auto-generated method stub
		boolean res;
		
		res = _Setup_List_1();
		
		// If the list can't be built
		//	=> go no further in settingup
		if (res == false) {
			
			String msg = "_Setup_List_1 => false";
			Methods_dlg.dlg_ShowMessage(this, msg, R.color.red);
			
			return false;
			
		}
		
		// List 2
		res = _Setup_List_2();
		
		// If the list can't be built
		//	=> go no further in settingup
		if (res == false) {
			
			String msg = "_Setup_List_2 => false";
			Methods_dlg.dlg_ShowMessage(this, msg, R.color.red);
			
			return false;
			
		}
		
		// List 3
		res = _Setup_List_3();
		
		// If the list can't be built
		//	=> go no further in settingup
		if (res == false) {
			
			String msg = "_Setup_List_3 => false";
			Methods_dlg.dlg_ShowMessage(this, msg, R.color.red);
			
			return false;
			
		}
		
		return true;
		
	}//_Setup_Lists

	/******************************
		List: Symbols
	 ******************************/
	private boolean 
	_Setup_List_1() {
		// TODO Auto-generated method stub
		////////////////////////////////
	
		// vars
	
		////////////////////////////////
		String msg_Log;
		
		////////////////////////////////
	
		// build list
	
		////////////////////////////////
		CONS.MemoActv.list_WP_1 = DBUtils.find_All_WP_symbols(this);
		
		/******************************
			validate: null
		 ******************************/
		if (CONS.MemoActv.list_WP_1 == null) {
			
			return false;
			
		}
		
		// Log
		msg_Log = "CONS.MemoActv.list_WP_1.size() => " 
						+ CONS.MemoActv.list_WP_1.size();
		Log.d("MemoActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
	
		// sort
	
		////////////////////////////////
		Collections.sort(
						CONS.MemoActv.list_WP_1, 
						new Comp_WP(
								
								CONS.Enums.SortType.WORD,
								CONS.Enums.SortOrder.ASC
						));
	
		Collections.sort(
				CONS.MemoActv.list_WP_1, 
				new Comp_WP(
						CONS.Enums.SortType.USED,
						CONS.Enums.SortOrder.DESC
						));
	
		////////////////////////////////
	
		// adapter
	
		////////////////////////////////
		// Log
		msg_Log = "Constructing an adapter...";
		Log.d("MemoActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		CONS.MemoActv.adp_WPList_1 = new Adp_WordPatterns(
	//			CONS.MemoActv.adp_WPList_1 = new ArrayAdapter<WordPattern>(
				this,
				R.layout.list_row_gv,
				CONS.MemoActv.list_WP_1
				);
	
		/******************************
			validate
		 ******************************/
		if (CONS.MemoActv.adp_WPList_1 == null) {
			
			// Log
			msg_Log = "CONS.MemoActv.adp_WPList_1 => null";
			Log.e("MemoActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			String msg = "adapter 1 => null";
			Methods_dlg.dlg_ShowMessage(this, msg, R.color.red);
			
			return false;
			
		}
		
		////////////////////////////////
	
		// set adapter
	
		////////////////////////////////
		ListView lv_1 = (ListView) findViewById(R.id.actv_rec_lv_1);
		
		// Log
		msg_Log = "setting the adapter to the listview";
		Log.d("MemoActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		lv_1.setAdapter(CONS.MemoActv.adp_WPList_1);
		
		return true;
		
	}//_Setup_List

	/******************************
		List: Tags
	 ******************************/
	private boolean 
	_Setup_List_2() {
		// TODO Auto-generated method stub
		////////////////////////////////
		
		// vars
		
		////////////////////////////////
		String msg_Log;
		
		////////////////////////////////
		
		// build list
		
		////////////////////////////////
		CONS.MemoActv.list_WP_2 = DBUtils.find_All_WP_tags(this);
		
		/******************************
			validate: null
		 ******************************/
		if (CONS.MemoActv.list_WP_2 == null) {
			
			return false;
			
		}
		
		// Log
		msg_Log = "CONS.MemoActv.list_WP_2.size() => " 
				+ CONS.MemoActv.list_WP_2.size();
		Log.d("MemoActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
	
		// sort
	
		////////////////////////////////
		Collections.sort(
						CONS.MemoActv.list_WP_2, 
						new Comp_WP(
								CONS.Enums.SortType.WORD,
								CONS.Enums.SortOrder.ASC
						));
		
		Collections.sort(
				CONS.MemoActv.list_WP_2, 
				new Comp_WP(
						CONS.Enums.SortType.USED,
						CONS.Enums.SortOrder.DESC
						));
		
		////////////////////////////////
		
		// adapter
		
		////////////////////////////////
		// Log
		msg_Log = "Constructing an adapter...";
		Log.d("MemoActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		CONS.MemoActv.adp_WPList_2 = new Adp_WordPatterns(
	//			CONS.MemoActv.adp_WPList_2 = new ArrayAdapter<WordPattern>(
				this,
				R.layout.list_row_gv,
				CONS.MemoActv.list_WP_2
				);
		
		/******************************
			validate
		 ******************************/
		if (CONS.MemoActv.adp_WPList_2 == null) {
			
			// Log
			msg_Log = "CONS.MemoActv.adp_WPList_2 => null";
			Log.e("MemoActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			String msg = "adapter 1 => null";
			Methods_dlg.dlg_ShowMessage(this, msg, R.color.red);
			
			return false;
			
		}
		
		////////////////////////////////
		
		// set adapter
		
		////////////////////////////////
		ListView lv_2 = (ListView) findViewById(R.id.actv_rec_lv_2);
		
		// Log
		msg_Log = "setting the adapter to the listview";
		Log.d("MemoActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		lv_2.setAdapter(CONS.MemoActv.adp_WPList_2);
		
		return true;
		
	}//_Setup_List_2

	/******************************
		List: Literals
	 ******************************/
	private boolean 
	_Setup_List_3() {
		// TODO Auto-generated method stub
		////////////////////////////////
		
		// vars
		
		////////////////////////////////
		String msg_Log;
		
		////////////////////////////////
		
		// build list
		
		////////////////////////////////
		CONS.MemoActv.list_WP_3 = DBUtils.find_All_WP_literals(this);
		
		/******************************
			validate: null
		 ******************************/
		if (CONS.MemoActv.list_WP_3 == null) {
			
			return false;
			
		}
		
		// Log
		msg_Log = "CONS.MemoActv.list_WP_3.size() => " 
				+ CONS.MemoActv.list_WP_3.size();
		Log.d("MemoActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
	
		// sort
	
		////////////////////////////////
		Collections.sort(
						CONS.MemoActv.list_WP_3, 
						new Comp_WP(
								
								CONS.Enums.SortType.WORD,
								CONS.Enums.SortOrder.ASC
						));
		
		Collections.sort(
				CONS.MemoActv.list_WP_3, 
				new Comp_WP(
						CONS.Enums.SortType.USED,
						CONS.Enums.SortOrder.DESC
	//					CONS.Enums.SortOrder.ASC
						));
	
		////////////////////////////////
		
		// adapter
		
		////////////////////////////////
		// Log
		msg_Log = "Constructing an adapter...";
		Log.d("MemoActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		CONS.MemoActv.adp_WPList_3 = new Adp_WordPatterns(
	//			CONS.MemoActv.adp_WPList_3 = new ArrayAdapter<WordPattern>(
				this,
				R.layout.list_row_gv,
				CONS.MemoActv.list_WP_3
				);
		
		/******************************
			validate
		 ******************************/
		if (CONS.MemoActv.adp_WPList_3 == null) {
			
			// Log
			msg_Log = "CONS.MemoActv.adp_WPList_3 => null";
			Log.e("MemoActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			String msg = "adapter 1 => null";
			Methods_dlg.dlg_ShowMessage(this, msg, R.color.red);
			
			return false;
			
		}
		
		////////////////////////////////
		
		// set adapter
		
		////////////////////////////////
		ListView lv_3 = (ListView) findViewById(R.id.actv_rec_lv_3);
		
		// Log
		msg_Log = "setting the adapter to the listview";
		Log.d("MemoActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		lv_3.setAdapter(CONS.MemoActv.adp_WPList_3);
		
		return true;
		
	}//_Setup_List_3

	private boolean 
	_Setup_Listeners_LVs() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// view: list 1

		////////////////////////////////
		ListView lv_1 = (ListView) findViewById(R.id.actv_rec_lv_1);
		
		lv_1.setTag(Tags.ListTags.ACTV_REC_LV_1);
		
		lv_1.setOnItemClickListener(new LOI_CL(this));
		
		lv_1.setOnItemLongClickListener(new LOI_LCL(this));
		
		////////////////////////////////
		
		// view: list 2
		
		////////////////////////////////
		ListView lv_2 = (ListView) findViewById(R.id.actv_rec_lv_2);
		
		lv_2.setTag(Tags.ListTags.ACTV_REC_LV_2);
		
		lv_2.setOnItemClickListener(new LOI_CL(this));
		
		lv_2.setOnItemLongClickListener(new LOI_LCL(this));
		
		////////////////////////////////
		
		// view: list 3
		
		////////////////////////////////
		ListView lv_3 = (ListView) findViewById(R.id.actv_rec_lv_3);
		
		lv_3.setTag(Tags.ListTags.ACTV_REC_LV_3);
		
		lv_3.setOnItemClickListener(new LOI_CL(this));
		
		lv_3.setOnItemLongClickListener(new LOI_LCL(this));

		
		return false;
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
		
		////////////////////////////////

		// Setup: listeners

		////////////////////////////////
		_Setup_Listeners();

		////////////////////////////////

		// layout

		////////////////////////////////
		this._Setup_Layout();
		
		////////////////////////////////

		// views

		////////////////////////////////
		this._Setup_SetupViews();
		
		////////////////////////////////

		// listviews

		////////////////////////////////
		this._Setup_Lists();
		
		////////////////////////////////

		// listeners: listviews

		////////////////////////////////
		this._Setup_Listeners_LVs();
		
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
