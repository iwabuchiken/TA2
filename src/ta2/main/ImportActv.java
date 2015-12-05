package ta2.main;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import ta2.adapters.Adp_AudioMemoList;
import ta2.adapters.Adp_MemoList;
import ta2.items.Memo;
import ta2.listeners.LOI_CL;
import ta2.listeners.LOI_LCL;
import ta2.listeners.STL;
import ta2.listeners.button.BO_CL;
import ta2.listeners.button.BO_TL;
import ta2.utils.CONS;
import ta2.utils.DBUtils;
import ta2.utils.Methods;
import ta2.utils.Methods_dlg;
import ta2.utils.Tags;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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

public class ImportActv extends ListActivity {

    @Override
	protected void 
	onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		// Log
		String msg_Log = "onStart";
		Log.d("ImportActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber() + "]",
				msg_Log);		
		
		boolean res;

//		///////////////////////////////////
//		//
//		// title
//		//
//		///////////////////////////////////
//		this._Setup_Set_Title();
		
		///////////////////////////////////
		//
		// setup: table
		//
		///////////////////////////////////
		int res_i = DBUtils.createTable_static(
					this, 
					CONS.DB.tname_Audio_Files, 
					CONS.DB.col_names_Audio_Files, 
					CONS.DB.col_types_Audio_Files);
		
//		-1 Table exists
//		 -2 SQLException
//		 1 Table created

		/*******************************
		 * valid: table set
		 *******************************/
		if (res_i == -2) {

			// Log
//			String msg_Log;
			
			msg_Log = String.format(
					Locale.JAPAN,
					"create table => sql exception"
					);
			
			Log.e("ImportActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return;

		}//if (res_i == -2)
		
		////////////////////////////////

		// init vars

		////////////////////////////////
		this._Setup_InitVars();

		///////////////////////////////////
		//
		// insert data: audio files
		//
		///////////////////////////////////
		_Setup_Update_Audio_Files_List();
//		_Setup_Insert_Audio_Files();
		
		////////////////////////////////

		// build list: TA2 list => after: _Setup_Update_Audio_Files_List() 

		////////////////////////////////
		res = _Setup_List();

		if (res == false) {
			
			return;
			
		}
		
		////////////////////////////////

		// adapter

		////////////////////////////////
		res = _Setup_Adapter();

		///////////////////////////////////
		//
		// title
		//
		///////////////////////////////////
		this._Setup_Set_Title();
		
//		////////////////////////////////
//		
//		// selection
//		
//		////////////////////////////////
//		this._Setup_SetSelection();

		////////////////////////////////

		// listeners

		////////////////////////////////
		///////////////////////////////////
		//
		// listeners: main
		//
		///////////////////////////////////
		this._Setup_Listeners();
//		
//		this._Setup_Listeners_IBs();
		
//		do_test();
		
	}//onStart

	private boolean _Setup_Update_Audio_Files_List() {
		// TODO Auto-generated method stub
	
		String msg_Log;
		
		///////////////////////////////////
		//
		// auto-update
		//
		///////////////////////////////////
		///////////////////////////////////
		//
		// get: pref value
		//
		///////////////////////////////////
		boolean pref = Methods.get_Pref_Boolean(
				this, 
				CONS.Pref.pname_MainActv, 
				this.getString(R.string.prefs_AudioFiles_From_RecorderApp_List_Auto_Update_key), 
				false);

//		// Log
//		String msg_Log;
//		
//		msg_Log = String.format(
//				Locale.JAPAN,
//				"audio files, auto-update => %s", pref
//				);
//		
//		Log.i("ImportActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		///////////////////////////////////
		//
		// judge
		//
		///////////////////////////////////
		if (pref == true) {

			// Log
//			String msg_Log;
			
			msg_Log = String.format(
					Locale.JAPAN,
					"audio files => auto-updating..."
					);
			
			Log.i("ImportActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);

		} else {//if (pref == true)
			
			msg_Log = String.format(
					Locale.JAPAN,
					"audio files => NOT auto-updating"
					);
			
			Log.i("ImportActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return false;
			
		}//if (pref == true)
		
		///////////////////////////////////
		//
		// update
		//
		///////////////////////////////////
//		String msg_Log;

		String dpath = "/mnt/sdcard/AllVoiceRecords";
		
		File f = new File(dpath);
		
		/*******************************
		 * validate: dir exists
		 *******************************/
		if (!f.exists()) {

			// Log
//			String msg_Log;
			
			msg_Log = String.format(
					Locale.JAPAN,
					"dir doesn't exist => %s", f.getAbsolutePath()
					);
			
			Log.e("ImportActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);

			return false;
			
		}//if (!f.exists())
		
		///////////////////////////////////
		//
		// get: file name list
		//
		///////////////////////////////////
		String[] listOf_AudioFile_Names = f.list();
		
		// Log
//		String msg_Log;
		
		msg_Log = String.format(
				Locale.JAPAN,
				"audio files => %d", listOf_AudioFile_Names.length
				);
		
		Log.d("ImportActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		/*******************************
		 * valid: any entry
		 *******************************/
		int lenOf_AudioFiles = listOf_AudioFile_Names.length;
		
		if (lenOf_AudioFiles < 1) {

			// Log
//			String msg_Log;
			
			msg_Log = String.format(
					Locale.JAPAN,
					"no audio files in => %s", f.getAbsolutePath()
					);
			
			Log.e("ImportActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);

			return false;
			
		}//if (lenOf_AudioFiles < 1)
		
//		/*******************************
//		 * report
//		 *******************************/
//		for (int i = 0; i < lenOf_AudioFiles; i++) {
//			
//			// Log
////			String msg_Log;
//			
//			msg_Log = String.format(
//					Locale.JAPAN,
//					"audio file => %s", listOf_AudioFile_Names[i]
//					);
//			
//			Log.d("ImportActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);;
//			
//		}//for (int i = 0; i < lenOf_AudioFiles; i++)

		///////////////////////////////////
		//
		// insert data
		//
		///////////////////////////////////
		boolean res_B;
		int res_i;
		
		String tmp_S = null;
		
		// ContentValues
		ContentValues val = new ContentValues();
		
		// Put values
		String time = null;
//		String time = Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now());
				
		for (int i = 0; i < lenOf_AudioFiles; i++) {
			
			tmp_S = listOf_AudioFile_Names[i];
		
			/*******************************
			 * valid: data exists
			 *******************************/
			res_i = DBUtils.isInDB_Audio_File(this, tmp_S);
			
			// if 1 => record exists; next record
			if (res_i == 1) {

				continue;

			}//if (res_i == 1)
			
			time = Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now());

//			android.provider.BaseColumns._ID,		// 0
//			"created_at", "modified_at",			// 1,2
//			
//			"text",									// 3
//			"dir",							// 4

			val.put(CONS.DB.col_names_Audio_Files_full[1], time);
			val.put(CONS.DB.col_names_Audio_Files_full[2], time);
			
			val.put(CONS.DB.col_names_Audio_Files_full[3], tmp_S);
			val.put(CONS.DB.col_names_Audio_Files_full[4], dpath);

			// insert
			res_B = DBUtils.insert_Data_generic(this, CONS.DB.tname_Audio_Files, val);
			
		}//for (int i = 0; i < listOf_AudioFile_Names; i++)
		
//		DBUtils.insert_Data_generic(this, CONS.DB.tname_Audio_Files, val);
		
		///////////////////////////////////
		//
		// return
		//
		///////////////////////////////////
		return true;
		
	}//_Setup_Insert_Audio_Files
	

	private void 
	_Setup_Set_Title() {
		
		///////////////////////////////////
		//
		// update title
		//
		///////////////////////////////////
		int len = 0;
		
		if (CONS.ImportActv.list_Audio_Memos != null) {
//			if (CONS.ShowListActv.list_Memos != null) {

			len = CONS.ImportActv.list_Audio_Memos.size();
//			len = CONS.ShowListActv.list_Memos.size();

		} else {//if (CONS.ShowListActv.list_Memos != null)
			
			len = -1;
			
		}//if (CONS.ShowListActv.list_Memos != null)
		
//		int len_List = list_Memos.size();
		
		
		
		String title = String.format(
				Locale.JAPAN,
				"%s (%d)", this.getClass().getName(), len
//				"ShowListActv (%d)", len
				);

		this.setTitle(title);
		
	}//_Setup_Set_Title

	private void 
	_Setup_SetSelection() {
		// TODO Auto-generated method stub
		
		int target_Position;
		
		// If the current is larger than the previous,
		//	i.e. the position is increasing
		//	i.e. the list is scrolling downward
		//	=> modify the target
		
//		// Log
//		String msg_Log = String.format(
//						"list_Pos_Prev = %d, list_Pos_Current = %d", 
//						CONS.ShowListActv.list_Pos_Prev,
//						CONS.ShowListActv.list_Pos_Current);
//		
//		Log.d("ShowList.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		if (CONS.ShowListActv.list_Pos_Current
				> CONS.ShowListActv.list_Pos_Prev) {
			
			target_Position = CONS.ShowListActv.list_Pos_Current - 5;
			
		} else {
			
			// If the current is smaller than the previous,
			//	i.e. the position is decreasing
			//	=> set the target with the current
			target_Position = CONS.ShowListActv.list_Pos_Current;

		}
		
//		// Log
//		msg_Log = "CONS.ShowListActv.list_Pos_Current = "
//						+ CONS.ShowListActv.list_Pos_Current
//						+ " // "
//						+ "CONS.ShowListActv.list_Pos_Prev = "
//						+ CONS.ShowListActv.list_Pos_Prev
//						+ " // "
//						+ "target_Position = "
//						+ target_Position
//						;
//		Log.d("ImportActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		//REF http://stackoverflow.com/questions/7561353/programmatically-scroll-to-a-specific-position-in-an-android-listview answered Sep 26 '11 at 21:39
		this.getListView().setSelection(target_Position);
		
	}//_Setup_SetSelection()

    
	@Override
	protected void 
	onListItemClick
	(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		CONS.Admin.vib.vibrate(CONS.Admin.vibLength_click);
		
		////////////////////////////////

		// set pref

		////////////////////////////////
		this._ItemClick_SetPref_CurrentPosition(position);
		
		
	}//onListItemClick

	/*******************************
	 * up to => notify the adapter
	 *******************************/
	private void
	_ItemClick_SetPref_CurrentPosition(int position) {
		// TODO Auto-generated method stub
		Methods.set_Pref_Int(
				this,
				CONS.Pref.pname_ImportActv,
				CONS.Pref.pkey_ImportActv_Current_Position,
//				CONS.Pref.pname_ShowListActv,
//				CONS.Pref.pkey_ShowListActv_Current_Position,
//				CONS.Pref.pkey_CurrentPosition,
				position);
		
		// Log
//		String msg_log = "Pref: " + CONS.Pref.pkey_CurrentPosition
		String msg_log = "Pref: " + CONS.Pref.pkey_CurrentPosition_ImportActv
//				String msg_log = "Pref: " + CONS.Pref.pkey_CurrentPosition_MainActv
						+ " => "
						+ "Set to: " + position;
		
		Log.d("ImportActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_log);
		
		CONS.ImportActv.adp_List_AudioMemos.notifyDataSetChanged();
//		CONS.ShowListActv.adp_List_Memos.notifyDataSetChanged();

	}//_ItemClick_SetPref_CurrentPosition


	private boolean 
	_Setup_Adapter() {
		// TODO Auto-generated method stub

		///////////////////////////////////
		//
		// sort list
		//
		///////////////////////////////////
		Methods.sort_List__AudioMemos(
				CONS.ImportActv.list_Audio_Memos, 
				CONS.Enums.SortType.TEXT, 
				CONS.Enums.SortOrder.DESC);

		// Log
		String msg_Log;
		
		msg_Log = String.format(
				Locale.JAPAN,
				"audio memos => sorted: by %s, %s", CONS.Enums.SortType.TEXT, CONS.Enums.SortOrder.DESC
				);
		
		Log.d("ImportActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////

		// get adapter

		////////////////////////////////
		CONS.ImportActv.adp_List_AudioMemos = new Adp_AudioMemoList(
				
						this,
						R.layout.list_row_showlist,
						CONS.ImportActv.list_Audio_Memos
				
		);
		
		/******************************
			validate
		 ******************************/
		if (CONS.ImportActv.adp_List_AudioMemos == null) {
			
			// Log
//			String 
			msg_Log = "CONS.ImportActv.adp_List_Memos => null";
			Log.e("ImportActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			String msg = "adapter => null";
			Methods_dlg.dlg_ShowMessage(this, msg, R.color.red);
			
			return false;
			
		}
		
		////////////////////////////////

		// set

		////////////////////////////////
		this.getListView().setAdapter(CONS.ImportActv.adp_List_AudioMemos);
		
		return true;
		
	}//_Setup_Adapter
	
	private boolean 
	_Setup_List() {
		// TODO Auto-generated method stub
		
		String msg_Log;
		
//		////////////////////////////////
//
//		// keep the existing list
//
//		////////////////////////////////
//		if (CONS.ImportActv.list_Memos != null) {
//			
//			// Log
//			msg_Log = "list already exists => kept";
//			Log.d("ImportActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//			
//			return true;
//			
//		} else {
//			
//			// Log
//			msg_Log = "list doesn't exist => building a new one...";
//			Log.d("ImportActv.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", msg_Log);
//
//		}
//		
		////////////////////////////////

		// list size

		////////////////////////////////
		String pref_MemoList_Size = Methods.get_Pref_String(
							this, 
							CONS.Pref.pname_MainActv, 
							this.getString(R.string.prefs_MemoList_Size_key), 
							null);

		////////////////////////////////

		// list

		////////////////////////////////
		if (pref_MemoList_Size != null) {
			
			CONS.ImportActv.list_Audio_Memos = 
					DBUtils.find_All_Memos__ExternalAudios(
								this, 
								CONS.Enums.SortOrder.DESC, 
								Integer.parseInt(pref_MemoList_Size));
//			DBUtils.find_All_Memos(
//					this, 
//					CONS.Enums.SortOrder.DESC, 
//					Integer.parseInt(pref_MemoList_Size));
			
		} else {

			CONS.ImportActv.list_Audio_Memos = 
					DBUtils.find_All_Memos__ExternalAudios(this, CONS.Enums.SortOrder.DESC);
			
		}
		
		/******************************
			validate
		 ******************************/
		if (CONS.ImportActv.list_Audio_Memos == null) {
			
			// Log
			msg_Log = "CONS.ImportActv.list_Memos => null";
			Log.e("ImportActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			String msg = "Can't get memo list";
			Methods_dlg.dlg_ShowMessage(this, msg, R.color.red);
			
			return false;
			
		} else {

			// Log
			msg_Log = "memo size => " + CONS.ImportActv.list_Audio_Memos.size();
			Log.d("ImportActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);

//			//test
//			do_test();
			
			return true;
			
		}
		
	}//_Setup_List


	/*******************************
	 * Initialize<br>
	 * - CONS.Admin.vib
	 *******************************/
	private void 
	_Setup_InitVars() {
		// TODO Auto-generated method stub
		
		CONS.Admin.vib = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
		
	}//_Setup_InitVars

	private void 
	do_test() {
		// TODO Auto-generated method stub
	
//		_test_Color_Date_v2();
//		_test_Color_Date();
//		_test_PrefVal_SoundEffect();
		
	}


	private void _test_Color_Date_v2() {
		// TODO Auto-generated method stub

		////////////////////////////////

		// target

		////////////////////////////////
		Memo m = CONS.ShowListActv.list_Memos.get(10);
		
		String[] tokens = m.getCreated_at().split(" ");
		
		String[] tokens_Date = tokens[0].split("/");
		
		////////////////////////////////

		// today

		////////////////////////////////
		Calendar c_Today = Calendar.getInstance();
		
		c_Today.setTimeInMillis(Methods.getMillSeconds_now());
		
		Date d = c_Today.getTime();
		
		// Log
		String msg_Log = String.format(
					Locale.JAPAN,
					"Date d => %d/%d/%d",
					d.getYear(), d.getMonth(), d.getDate()
				);
		Log.d("ImportActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		// Log
		msg_Log = "Calendar.DATE => " + c_Today.get(Calendar.DATE);
		Log.d("ImportActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		////////////////////////////////

		// compare

		////////////////////////////////
		String comp_Target = String.format(
					"target => %s/%s/%s", 
					tokens_Date[0], 
					tokens_Date[1], 
					tokens_Date[2]);
		
		String comp_Today = String.format(
				"today => %d/%d/%d", 
				c_Today.get(Calendar.YEAR), 
				c_Today.get(Calendar.MONTH) + 1, 
				c_Today.get(Calendar.DATE)
				);
		
		// Log
		msg_Log = String.format(
						"target => %s // today => %s",
						comp_Target, comp_Today
				);
		Log.d("ImportActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		int res = comp_Today.compareTo(comp_Target);
				
		// Log
		msg_Log = "comp_Today.compareTo(comp_Target) => " + res;
		Log.d("ImportActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		res = comp_Target.compareTo(comp_Today);
		
		// Log
		msg_Log = "comp_Target.compareTo(comp_Today) => " + res;
		Log.d("ImportActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}

	private void _test_Color_Date() {
		// TODO Auto-generated method stub
		
		Memo m = CONS.ShowListActv.list_Memos.get(10);
		
		// Log
		String msg_Log = "m.getCreated_at() => " + m.getCreated_at();
		Log.d("ImportActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		String[] tokens = m.getCreated_at().split(" ");
		
		String[] tokens_Date = tokens[0].split("/");
		
		String new_DateLabel = StringUtils.join(
				new String[]{
						
						tokens_Date[1],
						tokens_Date[2]
								
				}, 
				"/");
		
		// Log
		msg_Log = "new_DateLabel => " + new_DateLabel;
		Log.d("ImportActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		// today
		String today = Methods.conv_MillSec_to_TimeLabel(Methods.getMillSeconds_now());
		
		String[] tokens_Today = today.split(" ");
		
		String[] tokens_Today_Date = tokens_Today[0].split("/");
		
		String new_DateLabel_today = StringUtils.join(
				new String[]{
						
						tokens_Today_Date[1],
						tokens_Today_Date[2]
								
				}, 
				"/");
		
		// Log
		msg_Log = "new_DateLabel_today => " + new_DateLabel_today;
		Log.d("ImportActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// compare
		
		////////////////////////////////
		int res = new_DateLabel_today.compareTo(new_DateLabel);
		
		// Log
		msg_Log = "Comp: new_DateLabel_today.compareTo(new_DateLabel) => " + res;
		Log.d("ImportActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// 
		
		////////////////////////////////
		String[] nums = new_DateLabel.split("/");
		String year		= tokens[0].split("/")[0];
		
		String[] nums_Today = new_DateLabel_today.split("/");
		String year_Today		= tokens_Today[0].split("/")[0];
		
		// Log
		msg_Log = "month => " + Integer.parseInt(nums[0]);
		Log.d("ImportActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
//		Calendar c = Calendar.getInstance();
		
		////////////////////////////////
		
		// Date instances
		
		////////////////////////////////
		Date d_Today = new Date(
				Integer.parseInt(year_Today), 
				Integer.parseInt(nums_Today[0]), 
				Integer.parseInt(nums_Today[1]));
		
		// Log
		msg_Log = String.format(
				"d_Today => %d/%d/%d",
				Integer.parseInt(year_Today), 
				Integer.parseInt(nums_Today[0]), 
				Integer.parseInt(nums_Today[1])
				);
		
		Log.d("ImportActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		String d_Today_s = Methods.conv_MillSec_to_TimeLabel(d_Today.getTime());
		
		// Log
		msg_Log = "d_Today_s => " + d_Today_s;
		Log.d("ImportActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}
	

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

		////////////////////////////////

		// long click

		////////////////////////////////
		ListView lv = (ListView) this.getListView();
		
		lv.setTag(Tags.ListTags.ACTV_IMPORTACTV_LV);
//		lv.setTag(Tags.ListTags.ACTV_SHOWLIST_LV);
		
		lv.setOnItemLongClickListener(new LOI_LCL(this));
		
	}//_Setup_Listeners

	private void 
	_Setup_Listeners_IBs() {
		// TODO Auto-generated method stub
		////////////////////////////////
		
		// IB: memo
		
		////////////////////////////////
		ImageButton bt_Back = 
				(ImageButton) this.findViewById(R.id.actv_showlist_ib_back);
		
		bt_Back.setTag(Tags.ButtonTags.ACTV_SHOWLIST_BACK);
		
		bt_Back.setOnTouchListener(new BO_TL(this));
		
		bt_Back.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////
		
		// IB: Top
		
		////////////////////////////////
		ImageButton bt_Top = 
				(ImageButton) this.findViewById(R.id.actv_showlist_ib_top);
		
		bt_Top.setTag(Tags.ButtonTags.ACTV_SHOWLIST_TOP);
		
		bt_Top.setOnTouchListener(new BO_TL(this));
		
		bt_Top.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////
		
		// IB: Up
		
		////////////////////////////////
		ImageButton bt_Up = 
				(ImageButton) this.findViewById(R.id.actv_showlist_ib_up);
		
		bt_Up.setTag(Tags.ButtonTags.ACTV_SHOWLIST_UP);
		
		bt_Up.setOnTouchListener(new BO_TL(this));
		
		bt_Up.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////
		
		// IB: Down
		
		////////////////////////////////
		ImageButton bt_Down = 
				(ImageButton) this.findViewById(R.id.actv_showlist_ib_down);
		
		bt_Down.setTag(Tags.ButtonTags.ACTV_SHOWLIST_DOWN);
		
		bt_Down.setOnTouchListener(new BO_TL(this));
		
		bt_Down.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////
		
		// IB: Bottom
		
		////////////////////////////////
		ImageButton bt_Bottom = 
				(ImageButton) this.findViewById(R.id.actv_showlist_ib_bottom);
		
		bt_Bottom.setTag(Tags.ButtonTags.ACTV_SHOWLIST_BOTTOM);
		
		bt_Bottom.setOnTouchListener(new BO_TL(this));
		
		bt_Bottom.setOnClickListener(new BO_CL(this));
		
		
	}//_Setup_Listeners
	

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
		
		// Log
		String msg_Log = "onDestroy";
		Log.d("ImportActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

//		////////////////////////////////
//
//		// clear: memo list
//
//		////////////////////////////////
//		CONS.ShowListActv.list_Memos.clear();
//		
//		CONS.ShowListActv.list_Memos = null;
		
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		// Log
		String msg_Log = "onPause";
		Log.d("ImportActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}


	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		
		// Log
		String msg_Log = "onRestart";
		Log.d("ImportActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

	}


	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		// Log
		String msg_Log = "onStop";
		Log.d("ImportActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		// Log
		String log_msg = "onResume()";

		Log.d("[" + "ImportActv.java : "
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

		Log.d("[" + "ImportActv.java : "
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
		
		case R.id.menu_showlist_filter://--------------------
			
			case_OPT_Filter();
//			this.logoutFromTwitter();
			
			break;
			
		case R.id.menu_showlist_filter_history://--------------------
			
			case_OPT_FilterHistory();
//			this.logoutFromTwitter();
			
			break;
			
		case R.id.menu_main_settings://--------------------
			
			case_OPT_Settings();
//			this.logoutFromTwitter();
			
			break;
			
//		case R.id.menu_main_settings://--------------------
//			
//			case_OPT_Settings();
////			this.logoutFromTwitter();
//			
//			break;
			
		default://-------------------------------------
			break;
	
		}
		
		return super.onOptionsItemSelected(item);
		
	}//public boolean onOptionsItemSelected(MenuItem item)

	private void 
	case_OPT_FilterHistory() {
		// TODO Auto-generated method stub
		
		Methods_dlg.dlg_Filter_History(this);
		
	}

	private void case_OPT_Settings() {
		// TODO Auto-generated method stub
		
		Methods.start_Activity_PrefActv__ForResult(this);
//		Methods.start_Activity_PrefActv(this);
		
	}

	private void 
	case_OPT_Filter() {
		// TODO Auto-generated method stub
	
//		Methods_dlg.dlg_filter_ShowList(this);
		Methods_dlg.dlg_filter_ShowList__ImportActv(this);
		
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

	@Override
	protected void 
	onActivityResult
	(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		// Log
		String msg_Log = "onActivityResult";
		Log.i("ImportActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		// Log
		msg_Log = "resultCode => " + resultCode
				+ "/"
				+ "requestCode => " + requestCode;
		Log.d("ImportActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		////////////////////////////////

		// set: result code

		////////////////////////////////
		CONS.ShowListActv.BACK_FROM_X_ACTV = resultCode;
		
	}

}
