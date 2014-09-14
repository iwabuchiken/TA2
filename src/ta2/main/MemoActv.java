package ta2.main;

import java.util.Collections;
import java.util.List;

import ta2.adapters.Adp_WordPatterns;
import ta2.comps.Comp_WP;
import ta2.items.WordPattern;
import ta2.listeners.LOI_CL;
import ta2.listeners.LOI_LCL;
import ta2.listeners.STL;
import ta2.listeners.TW;
import ta2.listeners.button.BO_CL;
import ta2.listeners.button.BO_TL;
import ta2.utils.CONS;
import ta2.utils.DBUtils;
import ta2.utils.Methods;
import ta2.utils.Methods_dlg;
import ta2.utils.Tags;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MemoActv extends Activity {

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.actv_memo);
		
		this.setTitle(this.getClass().getName());
		
	}

	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		////////////////////////////////

		// save text

		////////////////////////////////
		Methods.save_Memo_Temporary(this);
		
		////////////////////////////////

		// finish

		////////////////////////////////
		this.finish();
		
		overridePendingTransition(0, 0);
		

//		////////////////////////////////
//
//		// bgm => stop
//
//		////////////////////////////////
//		if (CONS.Audio.task_Audio != null) {
//			
//			CONS.Audio.task_Audio.cancel(true);
//			
//			// Log
//			String msg_Log = "task audio => cancelled";
//			Log.d("MemoActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//			// End the task
//			CONS.Audio.task_Audio = null;
//			
//			if (CONS.Audio.audioTrack != null) {
//				
//				CONS.Audio.audioTrack.release();
//				
//				// Log
//				msg_Log = "audio => released";
//				Log.d("MemoActv.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber() + "]", msg_Log);
//				
//			}
//			
//			//test
//			CONS.Audio.audioTrack = null;
//			
//		}
		
	}//public void onBackPressed()

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		getMenuInflater().inflate(R.menu.menu_actv_memo, menu);
		return true;
		
//		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		
		case R.id.menu_memo_admin_patterns://--------------------
			
			case_OPT_Admin_Patterns();
//			this.logoutFromTwitter();
			
			break;
			
		default://-------------------------------------
			break;
	
		}

		return super.onOptionsItemSelected(item);
	}

	private void 
	case_OPT_Admin_Patterns() {
		// TODO Auto-generated method stub
		
		Methods_dlg.dlg_Admin_Patterns(this);
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		// Log
		String log_msg = "onPause()";

		Log.d("[" + "MemoActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", log_msg);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		
		// Log
		String log_msg = "onRestart()";

		Log.d("[" + "MemoActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", log_msg);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		// Log
		String log_msg = "onResume()";

		Log.d("[" + "MemoActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", log_msg);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		////////////////////////////////

		// vars

		////////////////////////////////
		boolean res;
		
		// Log
		String log_msg = "onStart()";

		Log.d("[" + "MemoActv.java : "
				+ +Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ " : "
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", log_msg);
		
		////////////////////////////////

		// listener

		////////////////////////////////
		this._Setup_Listeners();
		
		////////////////////////////////

		// layout

		////////////////////////////////
		_Setup_Layout();
		
		////////////////////////////////

		// build list

		////////////////////////////////
		res = _Setup_Lists();

		if (res == false) {
			
			return;
			
		}

		////////////////////////////////

		// listeners

		////////////////////////////////
		res = _Setup_Listeners_LVs();
		
		////////////////////////////////

		// set text (if pref set)

		////////////////////////////////
		_Setup_Set_PrefText();
		
	}//protected void onStart()

	private void 
	_Setup_Set_PrefText() {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// get pref

		////////////////////////////////
		String saved_Text = Methods.get_Pref_String(
								this, 
								CONS.Pref.pname_MainActv, 
								CONS.Pref.pkey_Saved_Memo,
								null);
		
		////////////////////////////////

		// set

		////////////////////////////////
		if (saved_Text != null) {
			
			EditText et = (EditText) this.findViewById(R.id.actv_memo_et);		
			
			et.setText(saved_Text);
			
			if (saved_Text.length() > 0) {
				
				et.setSelection(saved_Text.length());
				
			}

		}
		
		
	}//pkey_Saved_Memo

	/******************************
		Use this method AFTER executing "_Setup_Lists()"
	 ******************************/
	private boolean 
	_Setup_Listeners_LVs() {
		// TODO Auto-generated method stub
		boolean res;
		
		////////////////////////////////

		// list 1

		////////////////////////////////
		res = _Setup_Listeners_LVs_1();
		
		
		return false;
	}
	

	private boolean 
	_Setup_Listeners_LVs_1() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// view: list 1

		////////////////////////////////
		ListView lv_1 = (ListView) findViewById(R.id.actv_memo_lv_1);
		
		lv_1.setTag(Tags.ListTags.ACTV_MEMO_LV_1);
		
		lv_1.setOnItemClickListener(new LOI_CL(this));
		
		lv_1.setOnItemLongClickListener(new LOI_LCL(this));
		
		////////////////////////////////
		
		// view: list 2
		
		////////////////////////////////
		ListView lv_2 = (ListView) findViewById(R.id.actv_memo_lv_2);
		
		lv_2.setTag(Tags.ListTags.ACTV_MEMO_LV_2);
		
		lv_2.setOnItemClickListener(new LOI_CL(this));
		
		lv_2.setOnItemLongClickListener(new LOI_LCL(this));
		
		////////////////////////////////
		
		// view: list 3
		
		////////////////////////////////
		ListView lv_3 = (ListView) findViewById(R.id.actv_memo_lv_3);
		
		lv_3.setTag(Tags.ListTags.ACTV_MEMO_LV_3);
		
		lv_3.setOnItemClickListener(new LOI_CL(this));
		
		lv_3.setOnItemLongClickListener(new LOI_LCL(this));

		
		return false;
	}
	

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
//				CONS.MemoActv.adp_WPList_1 = new ArrayAdapter<WordPattern>(
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
		ListView lv_1 = (ListView) findViewById(R.id.actv_memo_lv_1);
		
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
//				CONS.MemoActv.adp_WPList_2 = new ArrayAdapter<WordPattern>(
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
		ListView lv_2 = (ListView) findViewById(R.id.actv_memo_lv_2);
		
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
//						CONS.Enums.SortOrder.ASC
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
//				CONS.MemoActv.adp_WPList_3 = new ArrayAdapter<WordPattern>(
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
		ListView lv_3 = (ListView) findViewById(R.id.actv_memo_lv_3);
		
		// Log
		msg_Log = "setting the adapter to the listview";
		Log.d("MemoActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		lv_3.setAdapter(CONS.MemoActv.adp_WPList_3);
		
		return true;
		
	}//_Setup_List_3
	
	
	
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
		LinearLayout ll_LV = (LinearLayout) findViewById(R.id.actv_memo_ll_listview);
		
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
		
		////////////////////////////////

		// EditText

		////////////////////////////////
		EditText et = (EditText) this.findViewById(R.id.actv_memo_et);

		String text = et.getText().toString();
		
		if (text != null && text.length() > 0) {
			
			et.setSelection(text.length());
			
		}
		
	}//_Setup_Layout

	private void 
	_Setup_Listeners() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// IB: back

		////////////////////////////////
		ImageButton bt_Back = (ImageButton) this.findViewById(R.id.actv_memo_ib_back);
		
		bt_Back.setTag(Tags.ButtonTags.ACTV_MEMO_BACK);
		
		bt_Back.setOnTouchListener(new BO_TL(this));
		
		bt_Back.setOnClickListener(new BO_CL(this));
		
//		bt_Up.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////

		// LL: base

		////////////////////////////////
		LinearLayout ll_Base = (LinearLayout) findViewById(R.id.actv_memo_ll_base);
		
		ll_Base.setTag(Tags.SwipeTags.ACTV_MEMO);
		
		ll_Base.setOnTouchListener(new STL(this));

		////////////////////////////////

		// button: all clear

		////////////////////////////////
		ImageButton bt_Clear = (ImageButton) this.findViewById(R.id.actv_memo_ib_clear);
		
		bt_Clear.setTag(Tags.ButtonTags.ACTV_MEMO_CLEAR);
		
		bt_Clear.setOnTouchListener(new BO_TL(this));
		
		bt_Clear.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////
		
		// button: save
		
		////////////////////////////////
		ImageButton bt_Save = (ImageButton) this.findViewById(R.id.actv_memo_ib_save);
		
		bt_Save.setTag(Tags.ButtonTags.ACTV_MEMO_SAVE);
		
		bt_Save.setOnTouchListener(new BO_TL(this));
		
		bt_Save.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////

		// edittext

		////////////////////////////////
		EditText et_Content = (EditText) this.findViewById(R.id.actv_memo_et);
		TextView tv_Num = (TextView) this.findViewById(R.id.actv_memo_tv_num);
		
		et_Content.addTextChangedListener(new TW(this, et_Content, tv_Num));
		
		
	}//_Setup_Listeners

	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		
		super.onStop();
		
	}

	
}
