package ta2.main;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

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
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ShowListActv extends ListActivity {

    @Override
	protected void 
	onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		// Log
		String msg_Log = "onStart";
		Log.d("ShowListActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber() + "]",
				msg_Log);		
		
		boolean res;
		
		////////////////////////////////

		// build list

		////////////////////////////////
		res = _Setup_List();

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
		
//		do_test();
		
	}//onStart

    
	private boolean 
	_Setup_Adapter() {
		// TODO Auto-generated method stub
		
		////////////////////////////////

		// get adapter

		////////////////////////////////
		CONS.ShowListActv.adp_List_Memos = new Adp_MemoList(
				
						this,
						R.layout.list_row_showlist,
						CONS.ShowListActv.list_Memos
				
		);
		
		/******************************
			validate
		 ******************************/
		if (CONS.ShowListActv.adp_List_Memos == null) {
			
			// Log
			String msg_Log = "CONS.ShowListActv.adp_List_Memos => null";
			Log.e("ShowListActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			String msg = "adapter => null";
			Methods_dlg.dlg_ShowMessage(this, msg, R.color.red);
			
			return false;
			
		}
		
		////////////////////////////////

		// set

		////////////////////////////////
		this.getListView().setAdapter(CONS.ShowListActv.adp_List_Memos);
		
		return true;
		
	}
	


	private boolean 
	_Setup_List() {
		// TODO Auto-generated method stub
		
		String msg_Log;
		
		////////////////////////////////

		// validate: memo => not yet built

		////////////////////////////////
		if (CONS.ShowListActv.list_Memos != null) {
			
			// Log
			msg_Log = "CONS.ShowListActv.list_Memos => not null. Recyling...";
			Log.d("ShowListActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			return true;
			
		}
		
		////////////////////////////////

		// list size

		////////////////////////////////
		String pref_MemoList_Size = Methods.get_Pref_String(
							this, 
							CONS.Pref.pname_MainActv, 
							this.getString(R.string.prefs_MemoList_Size_key), 
							null);

//		// Log
//		msg_Log = "pref_MemoList_Size => " + pref_MemoList_Size;
//		Log.d("ShowListActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		////////////////////////////////

		// list

		////////////////////////////////
		if (pref_MemoList_Size != null) {
			
			CONS.ShowListActv.list_Memos = 
					DBUtils.find_All_Memos(
								this, 
								CONS.Enums.SortOrder.DESC, 
								Integer.parseInt(pref_MemoList_Size));
			
		} else {

			CONS.ShowListActv.list_Memos = 
					DBUtils.find_All_Memos(this, CONS.Enums.SortOrder.DESC);
			
		}
		
		/******************************
			validate
		 ******************************/
		if (CONS.ShowListActv.list_Memos == null) {
			
			// Log
			msg_Log = "CONS.ShowListActv.list_Memos => null";
			Log.e("ShowListActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
			
			String msg = "Can't get memo list";
			Methods_dlg.dlg_ShowMessage(this, msg, R.color.red);
			
			return false;
			
		} else {

			// Log
			msg_Log = "memo size => " + CONS.ShowListActv.list_Memos.size();
			Log.d("ShowListActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);

			//test
			do_test();
			
			return true;
			
		}
		
	}//_Setup_List


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
		Log.d("ShowListActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		// Log
		msg_Log = "Calendar.DATE => " + c_Today.get(Calendar.DATE);
		Log.d("ShowListActv.java" + "["
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
		Log.d("ShowListActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		int res = comp_Today.compareTo(comp_Target);
				
		// Log
		msg_Log = "comp_Today.compareTo(comp_Target) => " + res;
		Log.d("ShowListActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		res = comp_Target.compareTo(comp_Today);
		
		// Log
		msg_Log = "comp_Target.compareTo(comp_Today) => " + res;
		Log.d("ShowListActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}

	private void _test_Color_Date() {
		// TODO Auto-generated method stub
		
		Memo m = CONS.ShowListActv.list_Memos.get(10);
		
		// Log
		String msg_Log = "m.getCreated_at() => " + m.getCreated_at();
		Log.d("ShowListActv.java" + "["
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
		Log.d("ShowListActv.java" + "["
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
		Log.d("ShowListActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		////////////////////////////////
		
		// compare
		
		////////////////////////////////
		int res = new_DateLabel_today.compareTo(new_DateLabel);
		
		// Log
		msg_Log = "Comp: new_DateLabel_today.compareTo(new_DateLabel) => " + res;
		Log.d("ShowListActv.java" + "["
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
		Log.d("ShowListActv.java" + "["
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
		
		Log.d("ShowListActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		String d_Today_s = Methods.conv_MillSec_to_TimeLabel(d_Today.getTime());
		
		// Log
		msg_Log = "d_Today_s => " + d_Today_s;
		Log.d("ShowListActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}
	

	private void 
	_Setup_Listeners() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// LL: base

		////////////////////////////////
		LinearLayout ll_Base = (LinearLayout) findViewById(R.id.actv_showlist_ll_base);
		
		ll_Base.setTag(Tags.SwipeTags.ACTV_SHOWLIST_BASE);
		
		ll_Base.setOnTouchListener(new STL(this));

		////////////////////////////////

		// long click

		////////////////////////////////
		ListView lv = (ListView) this.getListView();
		
		lv.setTag(Tags.ListTags.ACTV_SHOWLIST_LV);
		
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
		Log.d("ShowListActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

		////////////////////////////////

		// clear: memo list

		////////////////////////////////
		CONS.ShowListActv.list_Memos.clear();
		
		CONS.ShowListActv.list_Memos = null;
		
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		// Log
		String msg_Log = "onPause";
		Log.d("ShowListActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
	}


	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		
		// Log
		String msg_Log = "onRestart";
		Log.d("ShowListActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

	}


	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		// Log
		String msg_Log = "onStop";
		Log.d("ShowListActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);

	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		// Log
		String log_msg = "onResume()";

		Log.d("[" + "ShowListActv.java : "
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

		Log.d("[" + "ShowListActv.java : "
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

	private void case_OPT_Settings() {
		// TODO Auto-generated method stub
		
		Methods.start_Activity_PrefActv(this);
		
	}

	private void 
	case_OPT_Filter() {
		// TODO Auto-generated method stub
	
		Methods_dlg.dlg_filter_ShowList(this);
		
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
