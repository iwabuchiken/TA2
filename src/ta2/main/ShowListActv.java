package ta2.main;

import ta2.listeners.STL;
import ta2.listeners.button.BO_CL;
import ta2.listeners.button.BO_TL;
import ta2.utils.CONS;
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

public class ShowListActv extends ListActivity {

    @Override
	protected void 
	onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		////////////////////////////////

		// listeners

		////////////////////////////////
		this._Setup_Listeners();
		
		
		do_test();
		
	}//onStart

    
	private void 
	do_test() {
		// TODO Auto-generated method stub
	
//		_test_PrefVal_SoundEffect();
		
	}


	private void 
	_Setup_Listeners() {
		// TODO Auto-generated method stub
		////////////////////////////////

		// IB: memo

		////////////////////////////////
		ImageButton bt_Back = 
				(ImageButton) this.findViewById(R.id.actv_showlist_ib_back);
		
		bt_Back.setTag(Tags.ButtonTags.ACTV_SHOWLIST_BACK);
		
		bt_Back.setOnTouchListener(new BO_TL(this));
		
		bt_Back.setOnClickListener(new BO_CL(this));
		
//		bt_Up.setOnClickListener(new BO_CL(this));
		
		////////////////////////////////

		// LL: base

		////////////////////////////////
		LinearLayout ll_Base = (LinearLayout) findViewById(R.id.actv_showlist_ll_base);
		
		ll_Base.setTag(Tags.SwipeTags.ACTV_SHOWLIST_BASE);
		
		ll_Base.setOnTouchListener(new STL(this));

		
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
		getMenuInflater().inflate(R.menu.main_actv, menu);
		return true;
		
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		
//		case R.id.menu_main_admin://--------------------
//			
//			case_OPT_Admin();
////			this.logoutFromTwitter();
//			
//			break;
//			
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

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		////////////////////////////////

		// finish

		////////////////////////////////
		this.finish();
		
		overridePendingTransition(0, 0);
		
		return super.onKeyDown(keyCode, event);
	}

}
