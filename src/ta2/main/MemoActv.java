package ta2.main;

import java.util.List;

import ta2.adapters.Adp_WordPatterns;
import ta2.items.WordPattern;
import ta2.listeners.STL;
import ta2.listeners.button.BO_CL;
import ta2.listeners.button.BO_TL;
import ta2.utils.CONS;
import ta2.utils.DBUtils;
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
		
		this.finish();
		
		overridePendingTransition(0, 0);
		
	}//public void onBackPressed()

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
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
		res = _Setup_List_1();
		
		// If the list can't be built
		//	=> go no further in settingup
		if (res == false) {
			
			String msg = "_Setup_List_1 => false";
			Methods_dlg.dlg_ShowMessage(this, msg, R.color.red);
			
			return;
			
		}
		
		res = _Setup_List_2();
		
		// If the list can't be built
		//	=> go no further in settingup
		if (res == false) {
			
			String msg = "_Setup_List_2 => false";
			Methods_dlg.dlg_ShowMessage(this, msg, R.color.red);
			
			return;
			
		}
		
		
	}//protected void onStart()

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

		
	}

	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		
		super.onStop();
		
	}

	
}
