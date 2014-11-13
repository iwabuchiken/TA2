package ta2.main;

import ta2.utils.CONS;
import ta2.utils.Methods;
//import ta2.utils.CONS.Intent;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.content.Intent;

public class PrefActv extends PreferenceActivity 
					implements OnSharedPreferenceChangeListener{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		/*----------------------------
		 * Steps
		 * 1. Super
		 * 2. Set content
		 * 
		 * 3. Set preferences
		----------------------------*/
		super.onCreate(savedInstanceState);

		//
		setContentView(R.layout.main_pref);

		this.setTitle(this.getClass().getName());
		
		/*----------------------------
		 * 3. Set preferences
			----------------------------*/
		getPreferenceManager()
				.setSharedPreferencesName(
						CONS.Pref.pname_MainActv);
		
//		this.getString(R.string.prefs_shared_prefs_name));
		
		addPreferencesFromResource(R.xml.pref_main);
		
	}//public void onCreate(Bundle savedInstanceState)


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
		_Setup_FontSize();
		
		_Setup_Pref_AutoBk();
		
		_Setup_Listeners();
		
		super.onStart();
		
	}//protected void onStart()

	private void _Setup_Pref_AutoBk() {
		// TODO Auto-generated method stub
//		String msg_Log;
		
		////////////////////////////////
		
		// pref: auto backup
		
		////////////////////////////////
		EditTextPreference pref_AutoBk = 
				(EditTextPreference) findPreference(
						this.getString(R.string.prefs_db_auto_backup_key));
		
		pref_AutoBk.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
		
		////////////////////////////////
		
		// show: current
		
		////////////////////////////////
		String current = pref_AutoBk.getText();
		
		String summary = pref_AutoBk.getSummary().toString();
//		
		// Log
		String msg_Log = String.format("summary = %s, current = %s", summary, current);
		Log.d("PrefActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		if (current == null) {
//			if (summary == null || current == null) {
			
			summary = this.getString(R.string.prefs_db_auto_backup_summary);
			
		} else {

			//REF http://stackoverflow.com/questions/12377838/android-replace-with-regex answered Sep 11 '12 at 21:01
			summary = summary.replaceAll("\\d{1,}", current);
//			summary = summary.replaceAll("\\d+?", current);	// every digit gets replaced with "current"
//			summary = summary.replace("\\d+?", current);	// no replacement
//			summary = summary.replaceAll("\\d+?", current);
//			summary = summary.replaceAll("(\\d+?|X)", current);
//			summary = summary.replaceAll("[\\d+?X]", current);
			
			// Log
			msg_Log = "summary(replaced) => " + summary;
			Log.d("PrefActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", msg_Log);
		}
		
		pref_AutoBk.setSummary(summary);
		
	}

	private void _Setup_Listeners() {
		// TODO Auto-generated method stub
		
		getPreferenceScreen().getSharedPreferences()
    	.registerOnSharedPreferenceChangeListener(this);
		
	}


	private void _Setup_FontSize() {
		// TODO Auto-generated method stub

		////////////////////////////////

		// Input type

		////////////////////////////////
		EditTextPreference prefEditText = 
				(EditTextPreference) findPreference(
						this.getString(R.string.prefs_MemoList_Size_key));
//		this.getString(R.string.prefs_history_size_key));
		
		prefEditText.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);

		////////////////////////////////

		// show: current

		////////////////////////////////
		String current = prefEditText.getText();

		prefEditText.setSummary("Current = " + current);

		////////////////////////////////

		// list pref

		////////////////////////////////
		_test();
		
//		ListPreference pref_ListPref = 
//				(ListPreference) findPreference(
//						this.getString(R.string.prefs_MemoList_Size_ListPref_key));
//		
//		// Log
//		String msg_Log = "pref_ListPref.getContext() => "
//					+ pref_ListPref.getContext().getClass().getName();
//		Log.d("PrefActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		
		
//		(pref_ListPref.getContext())
//					.setBackgroundColor(this.getResources().getColor(R.color.gold2));
		
	}


	private void 
	_test() {
		// TODO Auto-generated method stub
	
//		ListPreference pref_ListPref = 
//				(ListPreference) findPreference(
//						this.getString(R.string.prefs_MemoList_Size_ListPref_key));
//		
//		// Log
//		String msg_Log = "pref_ListPref.getContext() => "
//					+ pref_ListPref.getContext().getClass().getName();
//		Log.d("PrefActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", msg_Log);
		
		////////////////////////////////

		// message

		////////////////////////////////
//		pref_ListPref.get
		
		
	}


	@Override
	protected void onStop() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onDestroy();
	}


	@Override
	public void onSharedPreferenceChanged(SharedPreferences arg0, String arg1) {
		// TODO Auto-generated method stub
		
		_Setup_FontSize();
		
		this._Setup_Pref_AutoBk();
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
		
//		Intent i = new Intent();
//		
//		this.setResult(CONS.Intent.RESULT_CODE_PREF_ACTIVE, i);
		this.setResult(CONS.Intent.RESULT_CODE_PREF_ACTIVE);
		
		// Log
		String msg_Log = "result set => " + CONS.Intent.RESULT_CODE_PREF_ACTIVE;
		Log.d("PrefActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		this.finish();
		
		overridePendingTransition(0, 0);
		
	}//public void onBackPressed()

}//public class PrefActv extends ListActivity
