package ta2.items;

import ta2.main.R;
import ta2.utils.CONS;
import android.content.Context;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;

public class Pref_Dropdown extends Preference {

	Context con;
	
	public Pref_Dropdown(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		this.con	= context;
		
		setWidgetLayoutResource(R.layout.pref_dropdown);
		
	}

	@Override
	protected Object onGetDefaultValue(TypedArray a, int index) {
		// TODO Auto-generated method stub
		
		// Log
		String msg_Log = "onGetDefaultValue";
		Log.d("Pref_Dropdown.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		return a.getInteger(index, CONS.Pref.DEFAULT_PROGRESS);
		
//		return super.onGetDefaultValue(a, index);
	}

	@Override
	protected void onSetInitialValue(boolean restorePersistedValue,
			Object defaultValue) {
		// TODO Auto-generated method stub
		
		// Log
		String msg_Log = "onSetInitialValue";
		Log.d("Pref_Dropdown.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
//		super.onSetInitialValue(restorePersistedValue, defaultValue);
	}

	@Override
	protected void 
	onBindView
	(View view) {
		// TODO Auto-generated method stub
        
		////////////////////////////////

		// get view

		////////////////////////////////
		Spinner sp = (Spinner) view.findViewById(R.id.pref_dropdown_sp);
		
		////////////////////////////////

		// adapter

		////////////////////////////////
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
	              con, R.layout.spinner_item_simple_1);
//		con, android.R.layout.simple_spinner_item);
//		this, android.R.layout.simple_spinner_item);

		adapter.add(String.valueOf(10));
		adapter.add(String.valueOf(20));
		adapter.add(String.valueOf(30));
		
		adapter.setDropDownViewResource(
				R.layout.spinner_item_simple_1);
		
		sp.setAdapter(adapter);
		
		sp.setSelection(0);
		
		// Log
		String msg_Log = "onBindView";
		Log.d("Pref_Dropdown.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", msg_Log);
		
		super.onBindView(view);
		
	}//onBindView

}
