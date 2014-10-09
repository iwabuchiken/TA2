package ta2.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import ta2.adapters.Adp_MemoList;
import ta2.items.Memo;
import ta2.items.TI;
import ta2.listeners.LOI_LCL;
import ta2.listeners.STL;
import ta2.listeners.button.BO_CL;
import ta2.listeners.button.BO_TL;
import ta2.utils.CONS;
import ta2.utils.DBUtils;
import ta2.utils.Methods;
import ta2.utils.Methods_dlg;
import ta2.utils.Tags;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

public class PhotoActv extends ListActivity {

    @Override
	protected void 
	onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		boolean res;
		
		////////////////////////////////

		// init vars

		////////////////////////////////
		this._Setup_Init_Vars();

		////////////////////////////////

		// get: content

		////////////////////////////////
		Cursor c = this._Setup_Get_Content();
		
		////////////////////////////////

		// build list

		////////////////////////////////
		res = _Setup_List(c);

		if (res == false) {
			
			return;
			
		}
		
		////////////////////////////////

		// adapter

		////////////////////////////////
		res = _Setup_Adapter();
		
		////////////////////////////////

		// listeners

		////////////////////////////////
		this._Setup_Listeners();
		
		this._Setup_Listeners_IBs();
		
		do_test();
		
	}//onStart

    
	private Cursor 
	_Setup_Get_Content() {
		// TODO Auto-generated method stub
	
		getIntent().setData(Uri.parse(CONS.PhotoActv.content_Uri));
		
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
			
			return null;
			
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
			
			return null;
			
		} else if (c.getCount() < 1) {//if (c == null)
			
			// Log
			Log.d("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "No entry in the table");
			
			return null;
			
		}//if (c == null)
		
		////////////////////////////////

		// get info

		////////////////////////////////
		// Log
		msg_Log = "c.getCount() => " + c.getCount();
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		return c;
		

		
		
//		////////////////////////////////
//
//		// test: get values
//
//		////////////////////////////////
//		int count = 0;
//		
//		while(c.moveToNext()) {
//			
//			// Log
//			msg_Log = "c.getString(5) => " + c.getString(5);
//			Log.d("PhotoActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//			count += 1;
//			
//			if (count > 20) {
//				
//				break;
//				
//			}
//			
//		}//while(c.moveToNext())
		
		
	}//_Setup_Get_Content
	

	private void 
	_Setup_SetSelection() {
		// TODO Auto-generated method stub
		
		int target_Position;
		
//		// If the current is larger than the previous,
//		//	i.e. the position is increasing
//		//	i.e. the list is scrolling downward
//		//	=> modify the target
//		
//		if (CONS.ShowListActv.list_Pos_Current
//				> CONS.ShowListActv.list_Pos_Prev) {
//			
//			target_Position = CONS.ShowListActv.list_Pos_Current - 5;
//			
//		} else {
//			
//			// If the current is smaller than the previous,
//			//	i.e. the position is decreasing
//			//	=> set the target with the current
//			target_Position = CONS.ShowListActv.list_Pos_Current;
//
//		}
//		
//		//REF http://stackoverflow.com/questions/7561353/programmatically-scroll-to-a-specific-position-in-an-android-listview answered Sep 26 '11 at 21:39
//		this.getListView().setSelection(target_Position);
		
	}//_Setup_SetSelection()

	private void 
	_Setup_Init_Vars() {
		// TODO Auto-generated method stub
		
		CONS.Admin.vib = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
		
	}//_Setup_InitVars

    
	private boolean 
	_Setup_Adapter() {
		// TODO Auto-generated method stub
		
//		////////////////////////////////
//
//		// get adapter
//
//		////////////////////////////////
//		CONS.ShowListActv.adp_List_Memos = new Adp_MemoList(
//				
//						this,
//						R.layout.list_row_showlist,
//						CONS.ShowListActv.list_Memos
//				
//		);
//		
//		/******************************
//			validate
//		 ******************************/
//		if (CONS.ShowListActv.adp_List_Memos == null) {
//			
//			// Log
//			String msg_Log = "CONS.ShowListActv.adp_List_Memos => null";
//			Log.e("ShowListActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//			String msg = "adapter => null";
//			Methods_dlg.dlg_ShowMessage(this, msg, R.color.red);
//			
//			return false;
//			
//		}
//		
//		////////////////////////////////
//
//		// set
//
//		////////////////////////////////
//		this.getListView().setAdapter(CONS.ShowListActv.adp_List_Memos);
		
		return true;
		
	}
	


	private boolean 
	_Setup_List(Cursor c) {
		// TODO Auto-generated method stub
		
		String msg_Log;

		////////////////////////////////

		// build: list

		////////////////////////////////
		/***************************************
		 * Build list
		 ***************************************/
//		android.provider.BaseColumns._ID,		// 0
//		"created_at", "modified_at",			// 1,2
//		"file_id", "file_path", "file_name",	// 3,4,5
//		"date_added", "date_modified",			// 6,7
//		"memos", "tags",						// 8,9
//		"last_viewed_at",						// 10
//		"table_name"							// 11
//		"uploaded_at",							// 12
		
		CONS.PhotoActv.ti_List = new ArrayList<TI>();
//		List<TI> ti_List = new ArrayList<TI>();
		
		while(c.moveToNext()) {
			
			TI ti = new TI.Builder()

					.setDb_Id(c.getLong(0))
					.setCreated_at(c.getString(1))
					.setModified_at(c.getString(2))
					
					.setFileId(c.getLong(3))
					.setFile_path(c.getString(4))
					.setFile_name(c.getString(5))
					
					.setDate_added(c.getString(6))
					.setDate_modified(c.getString(7))
					
					.setMemo(c.getString(8))
					.setTags(c.getString(9))
					
					.setLast_viewed_at(c.getString(10))
					.setTable_name(c.getString(11))

					.setUploaded_at(c.getString(12))
					
					.build();
			
			CONS.PhotoActv.ti_List.add(ti);
			
		}

		// Log
		msg_Log = "ti_List.size() => " + CONS.PhotoActv.ti_List.size();
		Log.d("PhotoActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		return true;
		
//		////////////////////////////////
//
//		// list size
//
//		////////////////////////////////
//		String pref_MemoList_Size = Methods.get_Pref_String(
//							this, 
//							CONS.Pref.pname_MainActv, 
//							this.getString(R.string.prefs_MemoList_Size_key), 
//							null);
//
////		// Log
////		msg_Log = "pref_MemoList_Size => " + pref_MemoList_Size;
////		Log.d("ShowListActv.java" + "["
////				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
////				+ "]", msg_Log);
//		
//		////////////////////////////////
//
//		// list
//
//		////////////////////////////////
//		if (pref_MemoList_Size != null) {
//			
//			CONS.ShowListActv.list_Memos = 
//					DBUtils.find_All_Memos(
//								this, 
//								CONS.Enums.SortOrder.DESC, 
//								Integer.parseInt(pref_MemoList_Size));
//			
//		} else {
//
//			CONS.ShowListActv.list_Memos = 
//					DBUtils.find_All_Memos(this, CONS.Enums.SortOrder.DESC);
//			
//		}
//		
//		/******************************
//			validate
//		 ******************************/
//		if (CONS.ShowListActv.list_Memos == null) {
//			
//			// Log
//			msg_Log = "CONS.ShowListActv.list_Memos => null";
//			Log.e("ShowListActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//			String msg = "Can't get memo list";
//			Methods_dlg.dlg_ShowMessage(this, msg, R.color.red);
//			
//			return false;
//			
//		} else {
//
//			// Log
//			msg_Log = "memo size => " + CONS.ShowListActv.list_Memos.size();
//			Log.d("ShowListActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//
//			//test
//			do_test();
//			
//			return true;
//			
//		}
		
	}//_Setup_List


	private void 
	do_test() {
		// TODO Auto-generated method stub
	
		this._test_Start_ImageActv();
		
//		_test_Color_Date_v2();
//		_test_Color_Date();
//		_test_PrefVal_SoundEffect();
		
	}

	private void 
	_test_Start_ImageActv() {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// validate

		////////////////////////////////
		if (CONS.PhotoActv.ti_List == null) {
			
			// Log
			String msg_Log = "CONS.PhotoActv.ti_List => null";
			Log.e("PhotoActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return;
			
		}
		
		////////////////////////////////

		// intent

		////////////////////////////////
		TI ti = CONS.PhotoActv.ti_List.get(0);
		
		Intent i = new Intent();
		
		i.setClass(this, ImageActv.class);
		
		i.putExtra("file_id", ti.getFileId());
		i.putExtra("db_id", ti.getDb_Id());
		i.putExtra("file_path", ti.getFile_path());
		i.putExtra("file_name", ti.getFile_name());
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

		startActivity(i);
		
	}//_test_Start_ImageActv

	private void 
	_Setup_Listeners() {
		// TODO Auto-generated method stub
//		////////////////////////////////
//
//		// LL: base
//
//		////////////////////////////////
//		LinearLayout ll_Base = (LinearLayout) findViewById(R.id.actv_showlist_ll_base);
//		
//		ll_Base.setTag(Tags.SwipeTags.ACTV_SHOWLIST_BASE);
//		
//		ll_Base.setOnTouchListener(new STL(this));
//
//		////////////////////////////////
//
//		// long click
//
//		////////////////////////////////
//		ListView lv = (ListView) this.getListView();
//		
//		lv.setTag(Tags.ListTags.ACTV_SHOWLIST_LV);
//		
//		lv.setOnItemLongClickListener(new LOI_LCL(this));
		
	}//_Setup_Listeners

	private void 
	_Setup_Listeners_IBs() {
		// TODO Auto-generated method stub
//		////////////////////////////////
//		
//		// IB: memo
//		
//		////////////////////////////////
//		ImageButton bt_Back = 
//				(ImageButton) this.findViewById(R.id.actv_showlist_ib_back);
//		
//		bt_Back.setTag(Tags.ButtonTags.ACTV_SHOWLIST_BACK);
//		
//		bt_Back.setOnTouchListener(new BO_TL(this));
//		
//		bt_Back.setOnClickListener(new BO_CL(this));
		
	}//_Setup_Listeners

	@Override
	protected void 
	onListItemClick
	(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		CONS.Admin.vib.vibrate(CONS.Admin.vibLength_click);
		
//		////////////////////////////////
//
//		// set pref
//
//		////////////////////////////////
//		this._ItemClick_SetPref_CurrentPosition(position);
		
		
	}//onListItemClick

	private void
	_ItemClick_SetPref_CurrentPosition(int position) {
		// TODO Auto-generated method stub
//		Methods.set_Pref_Int(
//				this,
//				CONS.Pref.pname_ShowListActv,
//				CONS.Pref.pkey_ShowListActv_Current_Position,
////				CONS.Pref.pkey_CurrentPosition,
//				position);
//		
//		// Log
////		String msg_log = "Pref: " + CONS.Pref.pkey_CurrentPosition
//		String msg_log = "Pref: " + CONS.Pref.pkey_CurrentPosition_MainActv
//						+ " => "
//						+ "Set to: " + position;
//		
//		Log.d("MainActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_log);
//		
//		CONS.ShowListActv.adp_List_Memos.notifyDataSetChanged();

	}


	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		////////////////////////////////

		// finish

		////////////////////////////////
		this.finish();
		
		overridePendingTransition(0, 0);
		
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
		setContentView(R.layout.actv_showlist);
		
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
		getMenuInflater().inflate(R.menu.menu_actv_showlist, menu);
		return true;
		
	}

	
	@Override
	public boolean 
	onOptionsItemSelected
	(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		
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

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
//		////////////////////////////////
//
//		// finish
//
//		////////////////////////////////
//		this.finish();
//		
//		overridePendingTransition(0, 0);
		
		return super.onKeyDown(keyCode, event);
	}

}
